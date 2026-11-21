package edu.utsa.cs3443.foundtime.model;

import edu.utsa.cs3443.foundtime.LoginController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManager {
    private ArrayList<Event> allEvents = new ArrayList<>();
    //private String eventsFilePath;

    public EventManager() {
        loadEvents();
    }

    public void addEvent(Event event) throws IOException {
        User currentUser = LoginController.currentUser;
        if(currentUser == null){
            throw new IllegalStateException("User is not logged in");
        }
        event.setUserID(currentUser.getUserID());
        allEvents.add(event);
        saveAllEvents();
    }
    public boolean removeEvent(Event event){
        User currentUser = LoginController.currentUser;
        if(allEvents.contains(event) && event.getUserID() == currentUser.getUserID()){
            allEvents.remove(event);
            saveAllEvents();
            return true;
        }
        saveAllEvents();
        return false;
    }

    public ArrayList<Event> getCurrentUserEvents(){
        User currentUser = LoginController.currentUser;
        ArrayList<Event> userEvents = new ArrayList<>();

        for(Event event : allEvents){
            if(event.getUserID() == currentUser.getUserID()){
                userEvents.add(event);
            }
        }

        return userEvents;
    }


    public ArrayList<Event> getEventsByDate(LocalDate date){
        User currentUser = LoginController.currentUser;
        ArrayList<Event> list = new ArrayList<>();
        for(Event event : allEvents){
               if(event.getStartDate().equals(date) && event.getUserID()==currentUser.getUserID()){
                    list.add(event);
               }
        }

        return list;
    }

    //TODO: Implement and print a formated event.
    public void displayEvent(){
        System.out.println("PLACEHOLDER: just print all the events in the csv to test");
        for(Event event : allEvents){
            System.out.println(event.toString());
        }
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
        String filePath = "data/events.csv";

        try {
            //userID,Name,Description,Date Start,Date End,Time Start,Time end,Location,Type
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); //Throws out the first row
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
                DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

                int userId = Integer.parseInt(data[0]);
                String eventName = data[1].trim();
                String eventDescription = data[2].trim();
                LocalDate startDate = LocalDate.parse(data[3].trim(), DATE_FORMATTER);
                LocalDate endDate = LocalDate.parse(data[4].trim(), DATE_FORMATTER);

                String rawStartTime = data[5].trim();
                String rawEndTime   = data[6].trim();
                LocalTime startTime = rawStartTime.equals("LocalTime.MIN") ? LocalTime.MIN : LocalTime.parse(rawStartTime, TIME_FORMATTER);
                LocalTime endTime   = rawEndTime.equals("LocalTime.MAX")   ? LocalTime.MAX : LocalTime.parse(rawEndTime, TIME_FORMATTER);

                String eventLocation = data[7].trim();
                String eventLabel = data[8].trim();

                allEvents.add(new Event(userId, eventName, eventDescription, startDate, endDate, startTime, endTime, eventLocation, eventLabel));
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Details: " + e.getMessage());
        }

        //Should call the lineToEvent method
    }

    @Override
    public String toString(){
        return "PLACEHOLDER";
    }
}
