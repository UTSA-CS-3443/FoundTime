package edu.utsa.cs3443.foundtime.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int userID;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String type;

    public Event(int userID, String name, String description, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String location, String type) {
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
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
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isAllDayEvent(){
        if(startDate.isEqual(LocalDate.MIN) && endDate.isEqual(LocalDate.MAX)){
            return true;
        }
        else if(startTime.equals(LocalTime.of(0, 0)) && endTime.equals(LocalTime.of(23, 59))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
