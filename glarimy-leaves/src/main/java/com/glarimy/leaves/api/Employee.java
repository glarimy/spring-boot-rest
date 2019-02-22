package com.glarimy.leaves.api;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String eid;
	private int leavebalance;
	private List<Integer> leaves;

	public Employee(String eid) {
		this.eid = eid;
		this.leavebalance = 20;
		this.leaves = new ArrayList<>();
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public int getLeavebalance() {
		return leavebalance;
	}

	public void setLeavebalance(int leavebalance) {
		this.leavebalance = leavebalance;
	}

	public List<Integer> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Integer> leaves) {
		this.leaves = leaves;
	}

}
