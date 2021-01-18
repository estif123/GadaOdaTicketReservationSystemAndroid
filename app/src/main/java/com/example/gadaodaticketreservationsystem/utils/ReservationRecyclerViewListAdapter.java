package com.example.gadaodaticketreservationsystem.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadaodaticketreservationsystem.R;
import com.example.gadaodaticketreservationsystem.Reservation_Detail_activity;
import com.example.gadaodaticketreservationsystem.ScheduleDetialActivity;
import com.example.gadaodaticketreservationsystem.ui.ReservationViewModel;
import com.example.gadaodaticketreservationsystem.ui.ScheduleViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReservationRecyclerViewListAdapter extends RecyclerView.Adapter<ReservationRecyclerViewListAdapter.ViewHolder> {

    private List<ReservationViewModel> reservationList;


    public ReservationRecyclerViewListAdapter(Context context, List<ReservationViewModel> reservations){
        this.reservationList = reservations;

    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.reservation_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ReservationViewModel reservation = reservationList.get(position);
        String reservation_Id=String.valueOf(reservation.getReservation_Id());
        String schedule=String.valueOf(reservation.getSchedule());
        String bus=String.valueOf(reservation.getBus());
        String status=reservation.getStatus();
        String service=reservation.getService();


        holder.reservation_Id.setText(reservation_Id);
        holder.schedule.setText(schedule);
        holder.bus.setText(bus);
        holder.status.setText(status);
        holder.service.setText(service);
        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        Bundle bundle=new Bundle();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),Reservation_Detail_activity.class);

                bundle.putString("schedule_Id",schedule);
                bundle.putString("reservation_Id",reservation_Id);
                bundle.putString("bus_no",bus);
                bundle.putString("service",service);
                bundle.putString("service_status",status);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }


    public int getItemCount() {
        return reservationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView status;
        public TextView dot;
        public TextView schedule;
        public TextView bus;
        public TextView service;
        public TextView reservation_Id;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dot = (TextView) itemView.findViewById(R.id.dot);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.reservation_Id = (TextView) itemView.findViewById(R.id.reservation_Id);
            this.schedule = (TextView) itemView.findViewById(R.id.schedule);
            this.bus = (TextView) itemView.findViewById(R.id.bus_Id);
            this.service = (TextView) itemView.findViewById(R.id.service);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }

    private String formatDate(String dateStr) {
//        try {
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = fmt.parse(dateStr);
//            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
//            return fmtOut.format(date);
//        } catch (ParseException e) {
//
//        }

        return "";
    }
    public void setItems(List<ReservationViewModel> reservationViewModelList){
        reservationList = reservationViewModelList;
        notifyDataSetChanged();
    }

    public void clear() {
        reservationList.clear();
        notifyDataSetChanged();
    }


}
