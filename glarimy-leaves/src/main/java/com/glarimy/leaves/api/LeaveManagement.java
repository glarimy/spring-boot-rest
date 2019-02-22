package com.glarimy.leaves.api;

import java.util.List;

public interface LeaveManagement {
	public LeaveRequest apply(LeaveRequest leave) throws InvalidLeaveRequestException, EmployeeNotFoundException,
			InsufficientLeaveBalanceException, LeaveManagementException;

	public void approve(int rid, String comments)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException;

	public void reject(int rid, String comments)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException;

	public void cancel(int rid, String comments)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException;

	public LeaveRequest get(int rid) throws LeaveRequestNotFoundExcpetion, LeaveManagementException;

	public List<LeaveRequest> list(String eid) throws EmployeeNotFoundException, LeaveManagementException;
}
