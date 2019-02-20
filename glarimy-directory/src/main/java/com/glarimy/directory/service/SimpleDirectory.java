package com.glarimy.directory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.glarimy.directory.api.Directory;
import com.glarimy.directory.api.DirectoryException;
import com.glarimy.directory.api.DuplicateEmployeeException;
import com.glarimy.directory.api.Employee;
import com.glarimy.directory.api.EmployeeNotFoundException;
import com.glarimy.directory.api.InvalidEmployeeException;

@Service
public class SimpleDirectory implements Directory {
	private Map<String, Employee> employees = new HashMap<>();

	@Override
	public Employee add(Employee employee)
			throws InvalidEmployeeException, DuplicateEmployeeException, DirectoryException {
		if (employee == null || employee.getEid() == null || employee.getName() == null
				|| employee.getEid().trim().length() == 0 || employee.getName().trim().length() == 0)
			throw new InvalidEmployeeException();
		if (employees.get(employee.getEid()) != null)
			throw new DuplicateEmployeeException();
		employees.put(employee.getEid(), employee);
		return employee;
	}

	@Override
	public Employee find(String eid) throws EmployeeNotFoundException, DirectoryException {
		Employee employee = employees.get(eid);
		if (employee == null)
			throw new EmployeeNotFoundException();
		return employee;
	}

	@Override
	public List<Employee> search(String name) throws DirectoryException {
		List<Employee> results = new ArrayList<>();
		for (Employee employee : employees.values())
			if (employee.getName().contains(name))
				results.add(employee);
		return results;
	}

}
