package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

//    private static List<Event> events = new ArrayList<>();
    //this is unnecessary once we created our EventData

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
//            model.addAttribute("errorMsg", "Bad data!");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
            model.addAttribute("title", "Edit Events");
            model.addAttribute("events", eventRepository.findById(eventId));
        return "events/edit";
//
//}
//    @PostMapping("events/edit")
//    public String processEditForm(int eventId, String name, String description) {
//
//    }
    }
}

//    @GetMapping("events/edit{id}")
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//
//        EventData.getById(int eventId);
//        model.addAttribute("title", "Edit Events");
//        model.addAttribute("events", EventData.getAll(id));
//        return "events/edit";
//
//    }