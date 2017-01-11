package GUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Core.Spending;

public class SpendingTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int YEAR_COL = 0;
	private static final int MONTH_COL = 1;
	private static final int DAY_COL = 2;
	private static final int SPEND_COL = 3;

	private String[] columnNames = { "Year", "Month", "Day",
			"Spending" };
	private List<Spending> spendings;

	public SpendingTableModel(List<Spending> theSpendings) {
		spendings = theSpendings;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return spendings.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {

		Spending tempSpending = spendings.get(row);

		switch (col) {
		case YEAR_COL:
			return tempSpending.getYear();
		case MONTH_COL:
			return tempSpending.getMonth();
		case DAY_COL:
			return tempSpending.getDay();
		case SPEND_COL:
			return tempSpending.getSpend();
		case OBJECT_COL:
			return tempSpending;
		default:
			return tempSpending.getYear();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
