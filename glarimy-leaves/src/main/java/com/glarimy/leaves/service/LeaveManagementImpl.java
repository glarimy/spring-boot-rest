package com.glarimy.leaves.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.glarimy.leaves.api.Employee;
import com.glarimy.leaves.api.EmployeeNotFoundException;
import com.glarimy.leaves.api.InsufficientLeaveBalanceException;
import com.glarimy.leaves.api.InvalidLeaveRequestException;
import com.glarimy.leaves.api.LeaveManagement;
import com.glarimy.leaves.api.LeaveManagementException;
import com.glarimy.leaves.api.LeaveRequest;
import com.glarimy.leaves.api.LeaveRequestNotFoundExcpetion;
import com.glarimy.leaves.api.LeaveStatusException;

@Service
public class LeaveManagementImpl implements LeaveManagement {
	private Map<Integer, LeaveRequest> leaves;
	private Map<String, Employee> employees;

	public LeaveManagementImpl() {
		leaves = new HashMap<>();
		employees = new HashMap<>();
		employees.put("G0001", new Employee("G001"));
		employees.put("G0002", new Employee("G002"));
		employees.put("G0003", new Employee("G003"));
		employees.put("G0004", new Employee("G004"));
	}

	@Override
	public LeaveRequest apply(LeaveRequest leave) throws InvalidLeaveRequestException, EmployeeNotFoundException,
			InsufficientLeaveBalanceException, LeaveManagementException {
		if (leave == null)
			throw new InvalidLeaveRequestException();
		if (leave.getEid() == null || leave.getEid().trim().length() == 0)
			throw new InvalidLeaveRequestException();
		Employee e = employees.get(leave.getEid());
		if (e == null)
			throw new EmployeeNotFoundException();
		if (e.getLeavebalance() < leave.getDates().size())
			throw new InsufficientLeaveBalanceException();
		// Date today = new Date();
		// for (Date date : leave.getDates())
		// if (date.getTime() < today.getTime())
		// throw new InvalidLeaveRequestException();
		leave.setId(leaves.size() + 1);
		leaves.put(leave.getId(), leave);
		e.getLeaves().add(leave.getId());
		return leave;
	}

	@Override
	public void approve(int rid, String comment)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException {
		LeaveRequest leave = leaves.get(rid);
		if (leave == null)
			throw new LeaveRequestNotFoundExcpetion();
		Employee e = employees.get(leave.getEid());
		if (e == null)
			throw new EmployeeNotFoundException();
		if (e.getLeavebalance() < leave.getDates().size())
			throw new InsufficientLeaveBalanceException();
		if (leave.getStatus() == "Cancelled")
			throw new LeaveStatusException();
		leave.setStatus("Approved");
		e.setLeavebalance(e.getLeavebalance() - leave.getDates().size());
		leave.setComment(comment);
	}

	@Override
	public void reject(int rid, String comment)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException {
		LeaveRequest leave = leaves.get(rid);
		if (leave == null)
			throw new LeaveRequestNotFoundExcpetion();
		Employee e = employees.get(leave.getEid());
		if (e == null)
			throw new EmployeeNotFoundException();

		if (leave.getStatus() == "Cancelled")
			throw new LeaveStatusException();

		leave.setStatus("Rejected");
		e.setLeavebalance(e.getLeavebalance() - leave.getDates().size());
		leave.setComment(comment);

	}

	@Override
	public void cancel(int rid, String comment)
			throws LeaveRequestNotFoundExcpetion, LeaveStatusException, LeaveManagementException {
		LeaveRequest leave = leaves.get(rid);
		if (leave == null)
			throw new LeaveRequestNotFoundExcpetion();

		Employee e = employees.get(leave.getEid());
		if (e == null)
			throw new EmployeeNotFoundException();

		leave.setStatus("Cancelled");
		e.setLeavebalance(e.getLeavebalance() - leave.getDates().size());
		leave.setComment(comment);

	}

	@Override
	public LeaveRequest get(int rid) throws LeaveRequestNotFoundExcpetion, LeaveManagementException {
		LeaveRequest leave = leaves.get(rid);
		if (leave == null)
			throw new LeaveRequestNotFoundExcpetion();
		return leave;
	}

	@Override
	public List<LeaveRequest> list(String eid) throws EmployeeNotFoundException, LeaveManagementException {
		List<LeaveRequest> requests = new ArrayList<>();
		Employee e = employees.get(eid);
		if (e == null)
			throw new EmployeeNotFoundException();
		for (Integer id : e.getLeaves())
			requests.add(leaves.get(id));
		return requests;
	}

}
