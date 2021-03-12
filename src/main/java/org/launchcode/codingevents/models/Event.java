package org.launchcode.codingevents.models;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Created by Chris Bay
 */

@Entity
public class Event extends AbstractEntity{


    @NotBlank
    @Size(min = 3, max = 50, message = "name is between 3-500 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid // checks fields of event details
    @NotNull
    private EventDetails eventDetails;

    @ManyToOne
    @NotNull(message = "category is required")
    private  EventCategory eventCategory;


    public Event() {
    }

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
