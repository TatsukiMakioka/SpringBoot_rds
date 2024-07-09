package com.example.demo.api;

import com.example.demo.domain.dto.ToDoData;
import com.example.demo.domain.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;

/**
 * ToDoController is a REST controller that handles HTTP requests related to ToDo items.
 */
@RestController
@RequestMapping("/api")
public class ToDoController {

    private final ToDoService service;

    /**
     * Constructs a new ToDoController with the specified ToDoService.
     *
     * @param service the ToDoService to be used by this controller
     */
    @Autowired
    public ToDoController(ToDoService service) {
        this.service = service;
    }

    /**
     * Returns a list of all ToDo items.
     *
     * @return a list of all ToDo items
     */
    @GetMapping
    public List<ToDoData> getToDos() {
        return service.getToDos();
    }

    /**
     * Creates a new ToDo item with the specified data.
     *
     * @param input the data of the new ToDo item
     * @return the ID of the created ToDo item
     */
    @PostMapping
    public String createTest(@RequestBody @Valid ToDoData input) {
        service.saveToDos(input);
        return input.getId();
    }

    /**
     * Deletes the ToDo item with the specified ID.
     *
     * @param id the ID of the ToDo item to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDos(@PathVariable("id") String id) {
        int deletedCount = service.deleteToDos(id);
        if (deletedCount == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates the ToDo item with the specified ID with the specified data.
     *
     * @param id the ID of the ToDo item to update
     * @param input the new data for the ToDo item
     * @return a ResponseEntity containing the updated ToDo item, or a not found status if no ToDo item with the specified ID exists
     */
    @PutMapping("/{id}")
    public ResponseEntity<ToDoData> updateToDos(@PathVariable("id") String id, @RequestBody @Valid ToDoData input) {
        try {
            return ResponseEntity.ok(service.updateToDos(id, input));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Marks the ToDo item with the specified ID as finished.
     *
     * @param id the ID of the ToDo item to mark as finished
     * @return a ResponseEntity containing the finished ToDo item, or a not found status if no ToDo item with the specified ID exists
     */
    @PostMapping("/{id}/finish")
    public ResponseEntity<ToDoData> finishTest(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(service.finishToDos(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Returns the ToDo item with the specified ID.
     *
     * @param id the ID of the ToDo item to return
     * @return a ResponseEntity containing the ToDo item, or a not found status if no ToDo item with the specified ID exists
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDoData> getToDosByID(@PathVariable("id") String id) {
        ToDoData toDoData = service.getToDosByID(id);
        if (toDoData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDoData);
    }

    /**
     * Returns a list of ToDo items that match the specified query.
     *
     * @param query the query to match ToDo items against
     * @return a ResponseEntity containing a list of matching ToDo items, or a bad request status if the query is null or empty
     */
    @GetMapping("/search")
    public ResponseEntity<List<ToDoData>> searchToDos(@RequestParam("keyword") String query) {
        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(service.searchToDos(query));
    }
}