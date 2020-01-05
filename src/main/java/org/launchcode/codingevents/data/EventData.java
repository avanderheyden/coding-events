package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//Notes: this is a "static class" because all of the members of the class are static.
//the only public elements are the methods
//this is an example of encapsulation. We are storing the behavior of storing event objects
//so no one has to know how they're stored.
//if we were to change something, it would not affect most classes, which is a good thing

public class EventData {

    //need a place to put events
    //a Map is an interface that stores key/value pairs
    //integer is the ID, event is the event object
    //make it final, once this field is initialized, the Map can't be changed
    private static final Map<Integer, Event> events= new HashMap<>();
    //get all events
    //Use a Collection because we are going to return a collection(list) of events
    //A Collection is an interface. It extends the iterable interface

    public static Collection<Event> getAll() {
        return events.values();
    }
    //get a single event
    public static Event getById(int id) {
        return events.get(id);

    }
    //add an event. Key is the id, event is the value
    public static void add(Event event){
        events.put(event.getId(), event);
    }
    //remove an event
    public static void remove(int id) {

        events.remove(id);
    }
}
//we need to refactor our controller class now