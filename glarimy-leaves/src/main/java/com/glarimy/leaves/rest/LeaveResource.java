package com.glarimy.leaves.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.glarimy.leaves.api.EmployeeNotFoundException;
import com.glarimy.leaves.api.InsufficientLeaveBalanceException;
import com.glarimy.leaves.api.InvalidLeaveRequestException;
import com.glarimy.leaves.api.LeaveManagement;
import com.glarimy.leaves.api.LeaveManagementException;
import com.glarimy.leaves.api.LeaveRequest;
import com.glarimy.leaves.api.LeaveRequestNotFoundExcpetion;
import com.glarimy.leaves.api.LeaveStatusException;
import com.glarimy.leaves.api.StatusUpdate;

@RestController
public class LeaveResource {
	@Autowired
	private LeaveManagement leaves;

	@RequestMapping(value = "/leave", method = RequestMethod.POST)
	public ResponseEntity<LeaveRequest> add(@RequestBody LeaveRequest leave, UriComponentsBuilder builder) {
		try {
			leave = leaves.apply(leave);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/leave/{id}").buildAndExpand(leave.getId()).toUri());
			return new ResponseEntity<LeaveRequest>(leave, headers, HttpStatus.CREATED);
		} catch (EmployeeNotFoundException enfe) {
			return new ResponseEntity<LeaveRequest>(leave, HttpStatus.NOT_FOUND);
		} catch (InsufficientLeaveBalanceException ibe) {
			return new ResponseEntity<LeaveRequest>(leave, HttpStatus.CONFLICT);
		} catch (InvalidLeaveRequestException ivlr) {
			return new ResponseEntity<LeaveRequest>(leave, HttpStatus.BAD_REQUEST);
		} catch (LeaveManagementException lme) {
			return new ResponseEntity<LeaveRequest>(leave, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/leave/{id}/status", method = RequestMethod.PUT)
	public ResponseEntity<StatusUpdate> update(@RequestBody StatusUpdate update, @PathVariable("id") int id) {
		try {
			if (update.getStatus() == "Cancel")
				leaves.cancel(id, update.getComment());
			if (update.getStatus() == "Approve")
				leaves.approve(id, update.getComment());
			if (update.getStatus() == "Reject")
				leaves.reject(id, update.getComment());
			return new ResponseEntity<StatusUpdate>(update, HttpStatus.ACCEPTED);
		} catch (LeaveRequestNotFoundExcpetion lrnfe) {
			return new ResponseEntity<StatusUpdate>(HttpStatus.NOT_FOUND);
		} catch (LeaveStatusException lse) {
			return new ResponseEntity<StatusUpdate>(HttpStatus.CONFLICT);
		} catch (LeaveManagementException lme) {
			return new ResponseEntity<StatusUpdate>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/leave/{id}", method = RequestMethod.GET)
	public ResponseEntity<LeaveRequest> find(@PathVariable("id") int id) {
		try {
			LeaveRequest leave = leaves.get(id);
			return new ResponseEntity<LeaveRequest>(leave, HttpStatus.OK);
		} catch (LeaveRequestNotFoundExcpetion lrnfe) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (LeaveManagementException lme) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/employee/{eid}/leaves", method = RequestMethod.GET)
	public ResponseEntity<List<LeaveRequest>> search(@PathVariable("eid") String eid) {
		try {
			List<LeaveRequest> requests = leaves.list(eid);
			return new ResponseEntity<List<LeaveRequest>>(requests, HttpStatus.OK);
		} catch (EmployeeNotFoundException enfe) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (LeaveManagementException lme) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
