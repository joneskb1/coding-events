package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {


    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title", "All Events");
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    // spring creates newEvent obj for us given name and des in request
    // must match form name values to event fields
    // use this instead of model model add attribute 
    public String createEvent(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
        //redirect to root path
    }

    @GetMapping("delete")
    public String displayDelete(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestParam(required=false) int[] eventIds) {
        if(eventIds != null) {
            for(int id : eventIds) {
            EventData.remove(id);
            }
        }
    return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event event = EventData.getById(eventId);
        model.addAttribute("event", "event");
        String name = event.getName();
        model.addAttribute("title", "Edit Event "+ name + " (id="+ eventId +")");
        return "events/edit";
    }


    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event event = EventData.getById(eventId);
        event.setDescription(description);
        event.setName(name);
        return "redirect:";
    }
}
