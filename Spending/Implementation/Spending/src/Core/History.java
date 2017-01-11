package Core;

import java.util.Date;

public class History {

	private int spendingId;
	private String action;
	private Date actionDateTime;

	public History(){
		
	}	
	public History(int spendingId, String action, Date actionDateTime) {
		// super();
		this.spendingId = spendingId;
		this.action = action;
		this.actionDateTime = actionDateTime;
	}
	public int getSpendingId() {
		return spendingId;
	}
	public void setSpendingId(int spendingId) {
		this.spendingId = spendingId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getActionDateTime() {
		return actionDateTime;
	}
	public void setActionDateTime(Date actionDateTime) {
		this.actionDateTime = actionDateTime;
	}
	@Override
	public String toString() {
		return "History [spendingId=" + spendingId + ", action=" + action
				+ ", actionDateTime=" + actionDateTime + "]";
	}

}
