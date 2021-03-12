package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Entity
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "description too long")
    private String description;

    @NotBlank
    @Email(message = "invalid email")
    private String contactEmail;

    @NotBlank
    private String address;

//    @OneToOne(mappedBy="eventDetails")
//    private Event event; inverse relationship

    public EventDetails () {};

    public EventDetails(@Size(max = 500, message = "description too long") String description, @NotBlank @Email(message = "invalid email") String contactEmail, @NotBlank String address) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
