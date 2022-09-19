package com.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.Project;

//A data repository, which extends one of Spring's default repository classes.
//Stores Projects, fetches Projects, and gets the estimated time for Projects

@Repository
@Component
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    
    //JPQL query to select unique projects associated with a user
    @Query(
    value = "SELECT DISTINCT p "
    + "FROM Task t INNER JOIN Project p ON t.projectId = p.id INNER JOIN Employee e on t.userId = e.id "
    + "WHERE e.id = :userId")
    public List<Project> getProject(@Param("userId") int userId);

    //JPQL query to get the total estimated time for a project
    @Query(
    value = "SELECT SUM(t.time) "
    + "FROM Task t INNER JOIN Project p ON t.projectId = p.id "
    + "WHERE p.id = :projectId")
    public int getEstimate(@Param("projectId") int projectId);

    

}
