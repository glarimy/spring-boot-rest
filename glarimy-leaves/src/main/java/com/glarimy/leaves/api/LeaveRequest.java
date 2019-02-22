package com.glarimy.leaves.api;

import java.util.Date;
import java.util.List;

public class LeaveRequest {
	private int id;
	private List<Date> dates;
	private String reason;
	private String eid;
	private String status;
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeaveRequest [id=" + id + ", dates=" + dates + ", reason=" + reason + ", eid=" + eid + ", status="
				+ status + "]";
	}

}
