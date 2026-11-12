package edu.utsa.cs3443.foundtime.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventManager {
    private ArrayList<Event> events;
    private User currentUser;
    private String eventsFilePath;

    public EventManager(User currentUser, String eventsFilePath) {
        this.currentUser = currentUser;
        this.eventsFilePath = eventsFilePath;
    }

    public void addEvent(Event event){
        events.add(event);
    }
    public boolean removeEvent(Event event){
        if(events.contains(event)){
            events.remove(event);
            return true;
        }
        return false;
    }

    public ArrayList<Event> getEventsByDate(LocalDate date){
        ArrayList<Event> list = new ArrayList<>();
        for(Event event : events){
               if(event.getStartDate().equals(date)){
                    list.add(event);
               }
        }

        return list;
    }

    //TODO: Implement and print a formated event.
    public void displayEvent(){
        System.out.println("PLACEHOLDER");
    }

    //TODO: This is gonna save to the events file??
    public void saveAllEvents(){
        /*I actually don't know how this would work since we only grabbed the users
          events, so we can't overwrite the current events file without the others events?
        */
        //Should call the eventToLine method
    }

    //TODO: Reads and grabs the users events from the events file
    public void loadEvents(){
        //Should call the lineToEvent method
    }

    @Override
    public String toString(){
        return "PLACEHOLDER";
    }
}
