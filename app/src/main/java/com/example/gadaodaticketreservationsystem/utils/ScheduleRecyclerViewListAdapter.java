package com.example.gadaodaticketreservationsystem.utils;

import android.content.Context;
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

        holder.schedule_Id.setText(String.valueOf(schedule.getSchedule_Id()));
        holder.departure_city.setText(schedule.getDeparture_city());
        holder.destination_city.setText(schedule.getDestination_city());
        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.date.setText(formatDate(schedule.getDate()));
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+data.getFristname(),Toast.LENGTH_LONG).show();
//            }
//        });
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

}
