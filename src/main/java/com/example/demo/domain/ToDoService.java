package com.example.demo.domain;

import com.example.demo.ToDoRepository;
import com.example.demo.domain.dto.ToDoData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Timestamp;
import java.util.NoSuchElementException;

/**
 * The ToDoService class handles the business logic for ToDo items.
 * This class provides operations such as creating, updating, deleting, and searching for ToDo items.
 * These operations communicate with the database through the ToDoRepository interface.
 */
@Service
public class ToDoService {

    /**
     * An instance of the ToDoRepository interface.
     * This instance is injected by the ToDoService constructor.
     */
    private final ToDoRepository repository;

    /**
     * Creates a new instance of the ToDoService class.
     *
     * @param repository An instance of the ToDoRepository interface that provides database operations for ToDo items.
     */
    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all ToDo items.
     *
     * @return A list of ToDo items.
     */
    public List<ToDoData> getToDos() {
        return repository.selectAll();
    }

    /**
     * Saves a new ToDo item.
     * The createdAt and updatedAt timestamps are set to the current time.
     *
     * @param newToDo The new ToDo item to save.
     */
    public void saveToDos(ToDoData newToDo) {
        newToDo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newToDo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        repository.save(newToDo);
    }

    /**
     * Deletes a ToDo item with the specified ID.
     *
     * @param id The ID of the ToDo item to delete.
     * @return The number of ToDo items deleted.
     */
    public int deleteToDos(String id) {
        return repository.delete(id);
    }

    /**
     * Updates a ToDo item with the specified ID.
     * The updatedAt timestamp is set to the current time.
     *
     * @param id The ID of the ToDo item to update.
     * @param updatedToDo The new data for the ToDo item.
     * @return The updated ToDo item.
     * @throws NoSuchElementException If no ToDo item with the specified ID exists.
     */
    public ToDoData updateToDos(String id, ToDoData updatedToDo) {
        ToDoData existingToDo = repository.select(id);
        if (existingToDo == null) {
            throw new NoSuchElementException("Test with id " + id + " not found");
        }
        updatedToDo.setId(id);
        updatedToDo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        repository.update(updatedToDo);
        return repository.select(id);
    }

    /**
     * Marks a ToDo item with the specified ID as finished.
     * The finishedAt and updatedAt timestamps are set to the current time.
     *
     * @param id The ID of the ToDo item to mark as finished.
     * @return The finished ToDo item.
     * @throws NoSuchElementException If no ToDo item with the specified ID exists.
     */
    public ToDoData finishToDos(String id) {
        ToDoData ToDo = repository.select(id);
        if (ToDo == null) {
            throw new NoSuchElementException("Test with id " + id + " not found");
        }
        ToDo.setFinishedAt(new Timestamp(System.currentTimeMillis()));
        ToDo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        repository.update(ToDo);
        return ToDo;
    }

    /**
     * Retrieves a ToDo item with the specified ID.
     *
     * @param id The ID of the ToDo item to retrieve.
     * @return The ToDo item with the specified ID.
     */
    public ToDoData getToDosByID(String id) {
        return repository.select(id);
    }

    /**
     * Searches for ToDo items that match the specified query.
     *
     * @param query The query to match ToDo items against.
     * @return A list of ToDo items that match the query.
     */
    public List<ToDoData> searchToDos(String query) {
        String lowercaseQuery = query.toLowerCase();
        return repository.search("%" + lowercaseQuery + "%");
    }
}
