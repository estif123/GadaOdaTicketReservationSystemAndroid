package com.example.gadaodaticketreservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gadaodaticketreservationsystem.ui.ReservationFragment;
import com.example.gadaodaticketreservationsystem.ui.ScheduleFragment;
import com.example.gadaodaticketreservationsystem.ui.PagerAdapter;
import com.example.gadaodaticketreservationsystem.ui.ScheduleViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TextView id,userName,userEmail,gender;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TabLayout tabLayout;
        ViewPager viewPager;
        TabItem tabSchedule;
        TabItem tabReservation;
        PagerAdapter pageAdapter;
        ScheduleFragment schedule;
        ScheduleViewModel scheduleViewModel;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            tabLayout = findViewById(R.id.tablayout);
            viewPager= findViewById(R.id.viewPager);
            tabSchedule= findViewById(R.id.tab_schedule);
            tabReservation= findViewById(R.id.tab_reservation);

        pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(2);

//      ScheduleFragment scheduleFragment = new ScheduleFragment();
//        ReservationFragment reservationFragment = new ReservationFragment();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


        //        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            id = findViewById(R.id.textViewId);
//            userName = findViewById(R.id.textViewUsername);
//            userEmail = findViewById(R.id.textViewEmail);
//            gender = findViewById(R.id.textViewGender);
//            btnLogout = findViewById(R.id.buttonLogout);
//            User user = SharedPrefManager.getInstance(this).getUser();
//
//            id.setText(String.valueOf(user.getId()));
//            userEmail.setText(user.getEmail());
//            gender.setText(user.getGender());
//            userName.setText(user.getUsername());
//            btnLogout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(v.equals(btnLogout)){
//                        SharedPrefManager.getInstance(getApplicationContext()).logout();
//                    }
//                }
//            });
//        }
//        else{
//            Intent intent = new Intent(MainActivity.this,loginActivity.class);
//            startActivity(intent);
//            finish();
//        }

}