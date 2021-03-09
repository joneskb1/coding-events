package org.launchcode.codingevents.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * Created by Chris Bay
 */

public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank
    @Size(min = 3, max = 50, message = "name is between 3-500 characters")
    private String name;
    @Size(max = 500, message = "description too long")
    private String description;

    @NotBlank
    @Email(message = "invalid email")
    private String contactEmail;

    @NotBlank
    private String address;

    @AssertTrue
    private boolean register;

    @Positive
    private int numAttendees;

    private  EventType type;


    public Event() {
        this.id = nextId;
        nextId++;
    }

    public Event(String name, String description, String contactEmail, String address, boolean register, int numAttendees, EventType type) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.address = address;
        this.register = register;
        this.numAttendees = numAttendees;
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getNumAttendees() {
        return numAttendees;
    }

    public void setNumAttendees(int numAttendees) {
        this.numAttendees = numAttendees;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
