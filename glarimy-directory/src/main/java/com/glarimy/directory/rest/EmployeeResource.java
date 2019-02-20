package com.glarimy.directory.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glarimy.directory.api.Directory;
import com.glarimy.directory.api.Employee;

@RestController
public class EmployeeResource {
	@Autowired
	private Directory directory;

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Employee> add(@RequestBody Employee employee) {
		employee = directory.add(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{eid}", method = RequestMethod.GET)
	public ResponseEntity<Employee> find(@PathVariable("eid") String eid) {
		Employee employee = directory.find(eid);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> search(@RequestParam("name") String name) {
		List<Employee> employees = directory.search(name);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
}
