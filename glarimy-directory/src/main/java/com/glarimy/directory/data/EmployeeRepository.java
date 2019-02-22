package com.glarimy.directory.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glarimy.directory.api.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	public List<Employee> findByName(String key);
}
