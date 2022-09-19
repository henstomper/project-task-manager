package com.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectmanager.entity.Task;

//A data repository, which extends one of Spring's default repository classes.
//Stores Tasks, and fetches them.

@Repository
@Component
public interface TaskRepository extends CrudRepository<Task, Integer> {

    //JPQL query to select tasks associated with a project
    @Query(
    value = "SELECT new com.projectmanager.entity.Task(t.id, t.userId, t.projectId, t.time, t.name) "
    + "FROM Task t "
    + "WHERE t.projectId=:id")
    public List<Task> getTasks(@Param("id") int id);
    
}
