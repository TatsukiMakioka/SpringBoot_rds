package com.example.demo;

import com.example.demo.domain.dto.ToDoData;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * ToDoRepository is an interface that defines methods for interacting with the database.
 * It uses MyBatis annotations to map these methods to SQL queries.
 * The methods defined in this interface are used by the ToDoService class to perform CRUD operations on ToDo items.
 */
@Mapper
public interface ToDoRepository {

    /**
     * Retrieves all ToDo items from the database.
     *
     * @return a list of all ToDo items
     */
    List<ToDoData> selectAll();

    /**
     * Saves a new ToDo item to the database.
     *
     * @param newTest the new ToDo item to save
     * @return the number of rows affected by the operation
     */
    int save(ToDoData newTest);

    /**
     * Deletes a ToDo item with the specified ID from the database.
     *
     * @param id the ID of the ToDo item to delete
     * @return the number of rows affected by the operation
     */
    int delete(String id);

    /**
     * Updates a ToDo item in the database.
     *
     * @param updatedTest the updated ToDo item
     */
    void update(ToDoData updatedTest);

    /**
     * Retrieves a ToDo item with the specified ID from the database.
     *
     * @param id the ID of the ToDo item to retrieve
     * @return the ToDo item with the specified ID
     */
    ToDoData select(String id);

    /**
     * Searches for ToDo items that match the specified value in the database.
     *
     * @param value the value to search for
     * @return a list of ToDo items that match the specified value
     */
    List<ToDoData> search(String value);
}