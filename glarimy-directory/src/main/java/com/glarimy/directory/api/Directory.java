package com.glarimy.directory.api;

import java.util.List;

public interface Directory {
	public Employee add(Employee employee)
			throws InvalidEmployeeException, DuplicateEmployeeException, DirectoryException;

	public Employee find(String eid) throws EmployeeNotFoundException, DirectoryException;

	public List<Employee> search(String name) throws DirectoryException;
}
