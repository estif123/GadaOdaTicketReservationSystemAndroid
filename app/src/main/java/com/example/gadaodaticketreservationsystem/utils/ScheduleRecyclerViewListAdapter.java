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

import androidx.annotation.NonNull;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadaodaticketreservationsystem.R;
import com.example.gadaodaticketreservationsystem.ScheduleDetialActivity;
import com.example.gadaodaticketreservationsystem.ui.ScheduleViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScheduleRecyclerViewListAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewListAdapter.ViewHolder> {

    private List<ScheduleViewModel> scheduleList;


    public ScheduleRecyclerViewListAdapter(Context context, List<ScheduleViewModel> schedules){
        this.scheduleList = schedules;

    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.schedule_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ScheduleViewModel schedule = scheduleList.get(position);
        String schedule_Id=String.valueOf(schedule.getSchedule_Id());
        String departure_city=schedule.getDeparture_city();
        String destination_city = schedule.getDestination_city();
        String date=formatDate(schedule.getDate());

        holder.schedule_Id.setText(schedule_Id);
        holder.departure_city.setText(departure_city);
        holder.destination_city.setText(destination_city);

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.date.setText(date);
        Bundle bundle=new Bundle();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScheduleDetialActivity.class);
                bundle.putString("schedule_Id",schedule_Id);
                bundle.putString("departure_city",departure_city);
                bundle.putString("destination_city",destination_city);
                bundle.putString("date",date);
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
            }
        });
    }


    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView schedule_Id;
        public TextView departure_city;
        public TextView destination_city;
        public TextView dot;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.schedule_Id = (TextView) itemView.findViewById(R.id.schedule_Id);
            this.departure_city = (TextView) itemView.findViewById(R.id.depature_city);
            this.destination_city = (TextView) itemView.findViewById(R.id.destination_city);
            this.dot = (TextView) itemView.findViewById(R.id.dot);
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
    public void setItems(List<ScheduleViewModel> scheduleViewModelList){
        scheduleList = scheduleViewModelList;
        notifyDataSetChanged();
    }
    public void clear() {
        scheduleList.clear();
        notifyDataSetChanged();
    }

}
