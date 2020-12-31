package com.example.gadaodaticketreservationsystem.ui;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class ScheduleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    int schedule_Id;
    String departure_city, destination_city;
    int route;
    String date;

    public ScheduleViewModel(int schedule_Id, String departure_city, String destination_city, int route, String date) {

        this.schedule_Id = schedule_Id;
        this.departure_city = departure_city;
        this.destination_city = destination_city;
        this.route = route;
        this.date = date;
    }



    public int getSchedule_Id() {
        return schedule_Id;
    }

    public void setSchedule_Id(int schedule_Id) {
        this.schedule_Id = schedule_Id;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}