package com.glarimy.leaves.api;

public class StatusUpdate {
	private String status;
	private String comment;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "StatusUpdate [status=" + status + ", comment=" + comment + "]";
	}

}
