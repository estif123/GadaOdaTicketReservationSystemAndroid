package com.example.gadaodaticketreservationsystem.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReservationViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    int reservation_Id;
    int schedule,bus;
    String status,service;


    public ReservationViewModel(int reservation_Id, int schedule, int bus, String service, String status) {
        this.reservation_Id = reservation_Id;
        this.schedule = schedule;
        this.status = status;
        this.bus = bus;
        this.service = service;
    }

    public int getReservation_Id() {
        return reservation_Id;
    }

    public void setReservation_Id(int reservation_Id) {
        this.reservation_Id = reservation_Id;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}