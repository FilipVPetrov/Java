package GUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Core.History;



public class HistoryTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	public static final int DATE_TIME_COL = 0;
	private static final int ACTION_COL = 1;

	private String[] columnNames = { "Date/Time", "Action"};
	
	private List<History> auditHistoryList;

	public HistoryTableModel(List<History> theAuditHistoryList) {
		auditHistoryList = theAuditHistoryList;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return auditHistoryList.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {

		History tempHistory = auditHistoryList.get(row);

		switch (col) {
		case DATE_TIME_COL:			
			return tempHistory.getActionDateTime();
		case ACTION_COL:
			return tempHistory.getAction();
		case OBJECT_COL:
			return tempHistory;
		default:
			return tempHistory.getAction();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
