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

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public List<ToDoData> getToDos() {
        return repository.selectAll();
    }

    public void saveToDos(ToDoData newToDo) {
        newToDo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newToDo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        repository.save(newToDo);
    }

    public int deleteToDos(String id) {
        return repository.delete(id);
    }

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

    public ToDoData getToDosByID(String id) {
        return repository.select(id);
    }

    public List<ToDoData> searchToDos(String query) {
        return repository.search("%" + query + "%");
    }
}
