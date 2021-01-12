package com.example.gadaodaticketreservationsystem.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gadaodaticketreservationsystem.R;

import com.example.gadaodaticketreservationsystem.URLs;
import com.example.gadaodaticketreservationsystem.VolleySingleton;
import com.example.gadaodaticketreservationsystem.utils.DividerItemDecoration;
import com.example.gadaodaticketreservationsystem.utils.ScheduleRecyclerViewListAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class ScheduleFragment extends Fragment {
    private ScheduleViewModel mViewModel;
    ScheduleRecyclerViewListAdapter scheduleRecyclerViewListAdapter;
    private TextView no_schedule_tv;
    RecyclerView recyclerView;
    private List<ScheduleViewModel> scheduleViewModelList =new ArrayList<>();
    String TAG = "The tag message";

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("LOG : ", "onCreatedView Run");
        View schedule_fragment_layout = inflater.inflate(R.layout.schedule_fragment, container, false);


//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
     //   no_schedule_tv = schedule_fragment_layout.findViewById(R.id.empty_schedule_view);

//        toggleEmptySchedules();

        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSchedule();
        recyclerView =  view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        Log.i(TAG, "onViewCreated: is called");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        scheduleRecyclerViewListAdapter= new ScheduleRecyclerViewListAdapter(getActivity(), scheduleViewModelList);
        recyclerView.setAdapter(scheduleRecyclerViewListAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this,new ScheduleFactory(getActivity().getApplication())).get(ScheduleViewModel.class);
        // TODO: Use the ViewModel
    }
    private void toggleEmptySchedules() {

        if (scheduleViewModelList.isEmpty()) {
            no_schedule_tv.setVisibility(View.VISIBLE);
        } else {
            no_schedule_tv.setVisibility(View.GONE);
        }
    }


    private void getSchedule() {
        Log.i(TAG, "getSchedule: method is called from oncreate");
        //if everything is fine
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URLs.URL_SCHEDULE, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: is called");

                    try {
                        for (int i =0; i < response.length(); i++){
                        JSONObject scheduleObject = response.getJSONObject(i);
                        ScheduleViewModel scheduleViewModel = new ScheduleViewModel(scheduleObject.getInt("schedule_Id"),
                                scheduleObject.getString("departure_city"),
                                scheduleObject.getString("destination_city"),scheduleObject.getString("date"));
                        scheduleViewModel.setSchedule_Id(scheduleObject.getInt("schedule_Id"));
                        scheduleViewModel.setDeparture_city(scheduleObject.getString("departure_city"));
                        scheduleViewModel.setDestination_city(scheduleObject.getString("destination_city"));
                        scheduleViewModel.setDate(scheduleObject.getString("date"));
                        scheduleViewModelList.add(scheduleViewModel);
                        System.out.println(scheduleObject.getString("destination_city"));
                        Log.i(TAG,scheduleViewModelList.toString());
                        }

                        scheduleRecyclerViewListAdapter.notifyDataSetChanged();
//                        scheduleRecyclerViewListAdapter.setItems(scheduleViewModelList);

//                        recyclerView.setAdapter(scheduleRecyclerViewListAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonArrayRequest);


    }

}