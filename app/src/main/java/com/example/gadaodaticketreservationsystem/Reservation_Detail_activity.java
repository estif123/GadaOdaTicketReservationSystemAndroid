package com.example.gadaodaticketreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reservation_Detail_activity extends AppCompatActivity {
    TextView reservation_Id;
    TextView schedule_Id;
    TextView bus_no;
    TextView service;
    TextView payment_status;
    TextView reservation_status;
    Button btn_make_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);
        reservation_Id=(TextView)findViewById(R.id.tv_reservation_Id);
        schedule_Id=(TextView)findViewById(R.id.tv_rservation_schedule_Id);
        bus_no=(TextView)findViewById(R.id.tv_bus_no);
        service=(TextView)findViewById(R.id.service);
        reservation_status=(TextView)findViewById(R.id.tv_reservation_status);
        payment_status=(TextView)findViewById(R.id.tv_payment_status);

        btn_make_payment=(Button)findViewById(R.id.btn_pay);

        btn_make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),capture_recieipt_image.class);

                v.getContext().startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String sch_Id=bundle.getString("schedule_Id");
        String reserv_Id=bundle.getString("reservation_Id");
        String b_no=bundle.getString("bus_no","0");
        String serv=bundle.getString("service","oda");
        String pay_status=bundle.getString("payment_status","Unknown");
        String serv_status=bundle.getString("service_status","approved");

        if (sch_Id!=null){
            System.out.println(sch_Id);
            schedule_Id.setText(sch_Id);
        }
        if(reserv_Id!=null){
            reservation_Id.setText(reserv_Id);
        }
        if(b_no!=null){
            bus_no.setText(b_no);
        }
        if(serv!=null){
            System.out.println(serv);
//            service.setText(serv);
        }
        if(pay_status!=null){
            payment_status.setText(pay_status);
        }
        if(serv_status!=null){
            payment_status.setText(serv_status);
        }

    }
//        String status=intent.getStringExtra("status");

}