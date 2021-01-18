package com.example.gadaodaticketreservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gadaodaticketreservationsystem.ui.ReservationViewModel;

public class ScheduleDetialActivity extends AppCompatActivity {
    TextView schedul_Id;
    TextView departure_city;
    TextView destination_city;
    TextView date;
    TextView status;
    Button btn_reserve;
    private ReservationViewModel reservationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detial);
        String TAG="tag message";

        schedul_Id=(TextView)findViewById(R.id.tv_schedule_Id);
        departure_city=findViewById(R.id.tv_departure_city);
        destination_city=(TextView)findViewById(R.id.tv_destination_city);
        date=(TextView)findViewById(R.id.tv_date);
        status=(TextView)findViewById(R.id.tv_status);
        btn_reserve=(Button)findViewById(R.id.btn_reserve);
        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent intent = new Intent(ScheduleDetialActivity.this,capture_recieipt_image.class);
//                startActivity(intent);
            }


        });

        Bundle bundle = getIntent().getExtras();
        String sch_Id=bundle.getString("schedule_Id");
        String dep_city=bundle.getString("departure_city","addis Ababa");
        String dest_city=bundle.getString("destination_city","wokite");
        String dat=bundle.getString("date","13/01/2021");
//        String status=intent.getStringExtra("status");
        if (sch_Id!=null){
            schedul_Id.setText(sch_Id);
        }
        if(dep_city!=null){
            Log.i(TAG, "onCreate: Sch_Detail_Activity:Departure City "+dep_city);
            departure_city.setText(dep_city);
        }
        if(dest_city!=null){
            destination_city.setText(dest_city);
        }
        if(dat!=null){
            date.setText(dat);
        }
    }




}