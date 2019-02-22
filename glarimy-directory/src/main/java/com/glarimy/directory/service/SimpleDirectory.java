package com.glarimy.directory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glarimy.directory.api.Directory;
import com.glarimy.directory.api.DirectoryException;
import com.glarimy.directory.api.DuplicateEmployeeException;
import com.glarimy.directory.api.Employee;
import com.glarimy.directory.api.EmployeeNotFoundException;
import com.glarimy.directory.api.InvalidEmployeeException;
import com.glarimy.directory.data.EmployeeRepository;

@Service
public class SimpleDirectory implements Directory {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee add(Employee employee)
			throws InvalidEmployeeException, DuplicateEmployeeException, DirectoryException {
		if (employee == null || employee.getEid() == null || employee.getName() == null
				|| employee.getEid().trim().length() == 0 || employee.getName().trim().length() == 0)
			throw new InvalidEmployeeException();
		repo.save(employee);
		return employee;
	}

	@Override
	public Employee find(String eid) throws EmployeeNotFoundException, DirectoryException {
		Employee employee = repo.findById(eid).orElseThrow(() -> new EmployeeNotFoundException());
		return employee;
	}

	@Override
	public List<Employee> search(String name) throws DirectoryException {
		return repo.findByName(name);
	}

}
