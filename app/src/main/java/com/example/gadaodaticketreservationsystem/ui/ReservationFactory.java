package com.example.gadaodaticketreservationsystem.ui;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ReservationFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;

    public ReservationFactory(@NonNull Application application) {
        this.application = application;
        this.application=application;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ScheduleViewModel.class)
            return (T) new ScheduleViewModel(application);
        return null;
    }
}
