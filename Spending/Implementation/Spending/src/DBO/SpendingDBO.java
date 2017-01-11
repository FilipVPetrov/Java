package DBO;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import Core.History;
import Core.Spending;
import Core.Statistic;

public class SpendingDBO {

	private Connection myConn;

	public SpendingDBO() throws Exception {

		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("info.properties"));

		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");

		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);

		System.out.println("Employee DAO - DB connection successful to: "
				+ dburl);
	}

	public void updateSpending(Spending theSpending) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update storeday"
					+ " set year=?, month=?, day=?, spend=?" + " where id=?");

			// set params
			myStmt.setInt(1, theSpending.getYear());
			myStmt.setInt(2, theSpending.getMonth());
			myStmt.setInt(3, theSpending.getDay());
			myStmt.setFloat(4, theSpending.getSpend());
			myStmt.setInt(5, theSpending.getId());

			// execute SQL
			myStmt.executeUpdate();

			int year= theSpending.getYear();
			int month= theSpending.getMonth();
			Statistic statistic=new Statistic(year,month,getTotalMonth(year, month),getAverageMonth(year, month));
			insertStatistic(statistic);
			
			myStmt = myConn.prepareStatement("insert into history"
					+ " (spending_id, action, action_date_time)"
					+ "values(?, ?, ?)");
			myStmt.setInt(1, theSpending.getId());
			myStmt.setString(2,
					"Updated sprending. New values " + theSpending.toString());
			myStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			myStmt.executeUpdate();
			myStmt.close();
		} finally {
			DBOUtils.close(myStmt);
		}

	}

	public void addSpending(Spending theSpending) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into storeday"
					+ " (year, month, day, spend)" + " values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setInt(1, theSpending.getYear());
			myStmt.setInt(2, theSpending.getMonth());
			myStmt.setInt(3, theSpending.getDay());
			myStmt.setFloat(4, theSpending.getSpend());
			// execute SQL
			myStmt.executeUpdate();

			int year= theSpending.getYear();
			int month= theSpending.getMonth();
			Statistic statistic=new Statistic(year,month,getTotalMonth(year, month),getAverageMonth(year, month));
			insertStatistic(statistic);
			
			// get the generated employee id
			ResultSet generatedKeys = myStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				theSpending.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Error generating key for storeday");
			}

			myStmt = myConn.prepareStatement("insert into history"
					+ " (spending_id, action, action_date_time)"
					+ "values(?, ?, ?)");
			myStmt.setInt(1, theSpending.getId());
			myStmt.setString(2,
					"Added sprending. New values " + theSpending.toString());
			myStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// execute SQL
			myStmt.executeUpdate();
		} catch (Exception exe) {
			exe.printStackTrace();
		} finally {
			DBOUtils.close(myStmt);
		}

	}

	public void deleteSpending(int SpendingId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			myStmt = myConn
					.prepareStatement("delete from history where spending_id=?");
			myStmt.setInt(1, SpendingId);
			myStmt.executeUpdate();

			myStmt = myConn.prepareStatement("delete from storeday where id=?");
			myStmt.setInt(1, SpendingId);
			myStmt.executeUpdate();
		} finally {
			DBOUtils.close(myStmt);
		}
	}

	public List<Spending> getAllSpendings() throws Exception {
		List<Spending> list = new ArrayList<Spending>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from storeday ");

			while (myRs.next()) {
				Spending tempSpending = convertRowToSpending(myRs);
				list.add(tempSpending);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}
	}

	public List<Spending> searchSpending(int year, String type)
			throws Exception {
		List<Spending> list = new ArrayList<Spending>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String toExecute = null;
		if (type.equals("year"))
			toExecute = "select * from storeday where year= ?";
		else if (type.equals("month"))
			toExecute = "select * from storeday where month= ?";
		else if (type.equals("day"))
			toExecute = "select * from storeday where day= ?";
		try {
			myStmt = myConn.prepareStatement(toExecute);

			myStmt.setInt(1, year);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Spending tempEmployee = convertRowToSpending(myRs);
				list.add(tempEmployee);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}

	}

	public List<Spending> searchSpending(int first, String type_first,
			int second, String type_second) throws Exception {
		List<Spending> list = new ArrayList<Spending>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String toExecute = null;
		if (type_first.equals("year") && type_second.equals("month"))
			toExecute = "select * from storeday where year= ? and month= ?";
		else if (type_first.equals("year") && type_second.equals("day"))
			toExecute = "select * from storeday where year= ? and day= ?";
		else if (type_first.equals("month") && type_second.equals("day"))
			toExecute = "select * from storeday where month= ? and day= ?";

		try {
			myStmt = myConn.prepareStatement(toExecute);

			myStmt.setInt(1, first);
			myStmt.setInt(2, second);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Spending tempEmployee = convertRowToSpending(myRs);
				list.add(tempEmployee);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}

	}

	public List<Spending> searchSpending(int year, int month, int day)
			throws Exception {
		List<Spending> list = new ArrayList<Spending>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("select * from storeday where year= ? and month=? and day= ?");

			myStmt.setInt(1, year);
			myStmt.setInt(2, month);
			myStmt.setInt(3, day);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Spending tempEmployee = convertRowToSpending(myRs);
				list.add(tempEmployee);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}

	}

	private Spending convertRowToSpending(ResultSet myRs) throws SQLException {

		int id = myRs.getInt("id");
		int year = myRs.getInt("year");
		int month = myRs.getInt("month");
		int day = myRs.getInt("day");
		float spend = myRs.getFloat("spend");

		Spending tempSpending = new Spending(id, year, month, day, spend);

		return tempSpending;
	}

	public boolean isEnteredToday() {
		List<Spending> list = new ArrayList<Spending>();
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		try {
			list = searchSpending(year, month, day);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
		if (list.isEmpty())
			return false;
		else
			return true;
	}

	public boolean isEntered(int year, int month, int day) {
		List<Spending> list = new ArrayList<Spending>();
		try {
			list = searchSpending(year, month, day);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		if (list.isEmpty())
			return false;
		else
			return true;
	}

	public List<History> getHistory(int spendingId) throws SQLException {
		List<History> list = new ArrayList<History>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();

			String sql = "SELECT spending_id, action, action_date_time  "
					+ "FROM history " + "WHERE history.spending_id="
					+ spendingId;

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {

				String action = myRs.getString("action");
				Timestamp timestamp = myRs.getTimestamp("action_date_time");
				java.util.Date actionDateTime = new java.util.Date(
						timestamp.getTime());

				History temp = new History(spendingId, action, actionDateTime);

				list.add(temp);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}

	}

	public List<Statistic> getAllStatistic() throws SQLException {
		List<Statistic> list = new ArrayList<Statistic>();
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from statistic ");

			while (myRs.next()) {
				Statistic tempStatistic = convertRowToStatistic(myRs);
				list.add(tempStatistic);
			}

			return list;
		} finally {
			DBOUtils.close(myStmt, myRs);
		}

	}

	private Statistic convertRowToStatistic(ResultSet myRs) throws SQLException {

		int year = myRs.getInt("year");
		int month = myRs.getInt("month");
		float total = myRs.getFloat("total");
		float average = myRs.getFloat("average");

		Statistic tempStatistic = new Statistic(year, month, total, average);

		return tempStatistic;
	}

	public List<Statistic> searchStatistic(int year, int month)
			throws SQLException {
		List<Statistic> list = new ArrayList<Statistic>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn
					.prepareStatement("select * from statistic where year=? and month= ? ");
			myStmt.setInt(1, year);
			myStmt.setInt(2, month);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				Statistic tempStatistic = convertRowToStatistic(myRs);
				list.add(tempStatistic);
			}

		} catch (Exception exe) {
			exe.printStackTrace();
		} finally {
			DBOUtils.close(myStmt, myRs);
			return list;
		}

	}

	public void insertStatistic(Statistic theStatistic) {
		try {
			int year=theStatistic.getYear();
			int month=theStatistic.getMonth();
			if (!isStatisticInserted(year,month)) {
				prepareStatisticStorage(theStatistic.getYear());
			}
			updateStatistic(theStatistic);
			Statistic tempStatistic=new Statistic(year,0,getTotalYear(year),getAverageYear(year));
			updateStatistic(tempStatistic);

		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	public void updateStatistic(Statistic theStatistic) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			int year = theStatistic.getYear();
			int month = theStatistic.getMonth();
			myStmt = myConn
					.prepareStatement("update statistic set total= ? , average= ? where year= ? and month=? ");
			myStmt.setFloat(1, theStatistic.getTotal());
			myStmt.setFloat(2, theStatistic.getAverage());
			myStmt.setFloat(3, year);
			myStmt.setFloat(4, month);
			myStmt.executeUpdate();
		} catch (Exception exe) {
			exe.printStackTrace();
		} finally {
			DBOUtils.close(myStmt);
		}
	}

	public void prepareStatisticStorage(int year) {
		Statistic theStatistic = null;
		try {
			for (int i = 0; i <= 12; i++) {
				theStatistic = new Statistic(year, i, 0, 0);
				addStatistic(theStatistic);
			}

		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	public float getTotalMonth(int year, int month) throws SQLException {
		float value = 0;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("select sum(spend) from storeday where year= ? and month= ? ");
			myStmt.setInt(1, year);
			myStmt.setInt(2, month);
			myRs = myStmt.executeQuery();
			myRs.next();
			value = myRs.getFloat(1);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		} finally {
			DBOUtils.close(myStmt, myRs);
		}
		return value;

	}

	public float getTotalYear(int year) throws SQLException {
		float value = 0;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("select sum(spend) from storeday where year= ? ");
			myStmt.setInt(1, year);
			myRs = myStmt.executeQuery();
			myRs.next();
			value = myRs.getFloat(1);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		} finally {
			DBOUtils.close(myStmt, myRs);
		}
		return value;

	}

	public float getAverageMonth(int year, int month) throws SQLException {
		float value = 0;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn
					.prepareStatement("select avg(spend) from storeday where year= ? and month= ? ");
			myStmt.setInt(1, year);
			myStmt.setInt(2, month);
			myRs = myStmt.executeQuery();
			myRs.next();
			value = myRs.getFloat(1);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		} finally {
			DBOUtils.close(myStmt, myRs);
		}
		return value;

	}

	public float getAverageYear(int year) throws SQLException {
		float value = 0;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn
					.prepareStatement("select avg(spend) from storeday where year= ? ");
			myStmt.setInt(1, year);
			myRs = myStmt.executeQuery();
			myRs.next();
			value = myRs.getFloat(1);
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		} finally {
			DBOUtils.close(myStmt, myRs);
			return value;
		}
	}

	public void addStatistic(Statistic theStatistic) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement(
					"insert into statistic" + " (year, month, total, average)"
							+ " values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			// set params
			myStmt.setInt(1, theStatistic.getYear());
			myStmt.setInt(2, theStatistic.getMonth());
			myStmt.setFloat(3, theStatistic.getTotal());
			myStmt.setFloat(4, theStatistic.getAverage());
			// execute SQL
			myStmt.executeUpdate();
		} catch (Exception exe) {
			exe.printStackTrace();
		} finally {
			DBOUtils.close(myStmt);
		}

	}

	public boolean isStatisticInserted(int year, int month) throws SQLException {
		List<Statistic> list = new ArrayList<Statistic>();
		list = searchStatistic(year, month);
		if (list.isEmpty())
			return false;
		else
			return true;
	}

	public static void main(String[] args) throws Exception {
		SpendingDBO dbo = new SpendingDBO();
		System.out.println(dbo.getAverageYear(2015));
		System.out.println(dbo.isStatisticInserted(2015, 8));
		// Statistic statistic =new
		// Statistic(2015,8,dbo.getTotalMonth(2015,8),dbo.getAverageMonth(2015,8));
		// dbo.addStatistic(statistic);
		// dbo.prepareStatisticStorage(2014);
		Statistic statistic = new Statistic(2016, 8, 600, 30);
//		dbo.updateStatistic(statistic);
		dbo.insertStatistic(statistic);
	}

}
