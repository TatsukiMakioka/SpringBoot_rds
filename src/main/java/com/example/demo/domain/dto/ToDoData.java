package com.example.demo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * ToDoData is a data transfer object (DTO) class that represents a ToDo item.
 * It includes fields for the item's ID, title, description, and timestamps for when it was created, updated, and finished.
 * It uses Lombok annotations for automatic generation of getters, setters, and a no-argument constructor.
 */
@Data
@NoArgsConstructor
public class ToDoData {

    /**
     * The unique ID of the ToDo item, automatically generated as a random UUID string.
     */
    private String id = UUID.randomUUID().toString();

    /**
     * The title of the ToDo item, which must not be null and must be between 1 and 255 characters in length.
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    /**
     * The description of the ToDo item, which must not be null.
     */
    @NotNull
    private String description;

    /**
     * The timestamp for when the ToDo item was created, automatically set to the current time when the object is instantiated.
     */
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    /**
     * The timestamp for when the ToDo item was last updated, automatically set to the current time when the object is instantiated.
     */
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

    /**
     * The timestamp for when the ToDo item was marked as finished.
     */
    private Timestamp finishedAt;
}