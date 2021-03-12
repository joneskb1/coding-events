package org.launchcode.codingevents.controllers;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventsRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventsRepository eventsRepository;
    // find all, save, findByID

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryID, Model model) {
        if (categoryID == null) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventsRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryID);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid category ID: " + categoryID);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in Category" + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }




    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventsRepository.save(newEvent);
        return "redirect:";
    }





    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventsRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
            eventsRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
