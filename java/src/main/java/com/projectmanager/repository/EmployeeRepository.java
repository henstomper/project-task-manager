package com.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.Employee;

//A data repository, which extends one of Spring's default repository classes.
//Stores Employees, and handles operations related to Employees.

@Repository
@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


    //JPQL query to retrieve unique employee IDs associated with a project
    @Query(
    value = "SELECT DISTINCT e.id "
    + "FROM Task t INNER JOIN Project p ON t.projectId = p.id INNER JOIN Employee e on t.userId = e.id "
    + "WHERE p.id=:id")
    public List<Integer> getMemberIDs(@Param("id") int id);
    
}
