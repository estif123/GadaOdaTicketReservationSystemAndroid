package com.example.gadaodaticketreservationsystem.ui;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.gadaodaticketreservationsystem.MainActivity;
import com.example.gadaodaticketreservationsystem.Model.User;
import com.example.gadaodaticketreservationsystem.R;

import com.example.gadaodaticketreservationsystem.SharedPrefManager;
import com.example.gadaodaticketreservationsystem.URLs;
import com.example.gadaodaticketreservationsystem.VolleySingleton;
import com.example.gadaodaticketreservationsystem.utils.DividerItemDecoration;
import com.example.gadaodaticketreservationsystem.utils.RecyclerTouchListener;
import com.example.gadaodaticketreservationsystem.utils.ReservationRecyclerViewListAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationFragment extends Fragment {
    private ReservationViewModel mViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    ReservationRecyclerViewListAdapter reservationRecyclerViewListAdapter;
    private TextView no_schedule_tv;

    RecyclerView recyclerView;
    private List<ReservationViewModel> reservationViewModelList =new ArrayList<>();
    String TAG = "The tag message";

    public static ReservationFragment newInstance() {
        return new ReservationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("LOG : ", "onCreatedView Run");

        return inflater.inflate(R.layout.reservation_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView =  view.findViewById(R.id.recyclerView_reservation);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        reservationRecyclerViewListAdapter= new ReservationRecyclerViewListAdapter(getActivity(), reservationViewModelList);
        recyclerView.setAdapter(reservationRecyclerViewListAdapter);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout_reservation);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

                reservationRecyclerViewListAdapter.getItemId(position);
                showActionsDialog(reservationViewModelList.get(position),position);
            }
        }));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getReservation();
            }
        });
        getReservation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this,new ReservationFactory(getActivity().getApplication())).get(ReservationViewModel.class);
        // TODO: Use the ViewModel


    }
    private void toggleEmptySchedules() {

        if (reservationViewModelList.isEmpty()) {
            no_schedule_tv.setVisibility(View.VISIBLE);
        } else {
            no_schedule_tv.setVisibility(View.GONE);
        }
    }


    private void getReservation() {
        reservationRecyclerViewListAdapter.clear();

        String id="65";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_RESERVATION,
                response -> {

                    try {

                        //converting response to json object
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONArray reservationJson = obj.getJSONArray("reservation");
                            //creating a new user object
                            for(int i= 0 ; i<reservationJson.length();i++){
                                JSONObject jsonObject = reservationJson.getJSONObject(i);
                                ReservationViewModel reservationViewModel = new ReservationViewModel(
                                        jsonObject.getInt("reservation_Id"),
                                        jsonObject.getInt("schedule"),
                                        jsonObject.getInt("customer"),
                                        jsonObject.getString("service"),
                                        jsonObject.getString("status")
                                );
                                reservationViewModelList.add(reservationViewModel);
                                Log.i(TAG, "onResponse: " + reservationViewModel.toString());
                            }

//
//                            reservationViewModel.setReservation_Id(reservationJson.getInt("reservation_Id"));
//                            reservationViewModel.setSchedule(reservationJson.getInt("schedule"));
//                            reservationViewModel.setBus(reservationJson.getInt("customer"));
//                            reservationViewModel.setService(reservationJson.getString("service"));
//                            reservationViewModel.setStatus(reservationJson.getString("status"));
//                            System.out.println(reservationJson.getString("service"));



//
//                            //storing the user in shared preferences
//                            SharedPrefManager.getInstance(getContext()).userLogin(user);
//                            //starting the profile activity
//                            finish();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    reservationRecyclerViewListAdapter.notifyDataSetChanged();
                },

                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", id);
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void showActionsDialog(ReservationViewModel reservationViewModel ,final int position) {
        CharSequence colors[] = new CharSequence[]{"Update Reservation", "Cancell Reservation","Delete Reservation"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    showReservationDialog(true,position);
                }
                if (which==1){

                    ReservationViewModel rv_check_status = reservationViewModelList.get(position);
                    String status = rv_check_status.getStatus().toString();
                    Toast.makeText(getContext(), ""+status, Toast.LENGTH_SHORT).show();
                    cancell_reservation(reservationViewModel.getReservation_Id());
                    getReservation();
                }
                else {
                    delete_reservation(reservationViewModel.getReservation_Id(),position);

                    getReservation();
                }
            }
        });
        builder.show();
    }

    private boolean updateReservation(ReservationViewModel reservationViewModel,int position) {
        ReservationViewModel reservationViewModel1 = reservationViewModelList.get(position);


        // updating note text
        reservationViewModel.setReservation_Id(reservationViewModel1.getReservation_Id());
        reservationViewModel.setSchedule(reservationViewModel1.getSchedule());
        reservationViewModel.setBus(reservationViewModel1.getBus());
        reservationViewModel.setService(reservationViewModel1.getService());
        reservationViewModel.setStatus(reservationViewModel1.getStatus());

        // refreshing the list
        reservationViewModelList.set(position, reservationViewModel);
        reservationRecyclerViewListAdapter.setItems(reservationViewModelList);
        reservationRecyclerViewListAdapter.notifyItemChanged(position);
        return true;

    }

    private void showReservationDialog(final boolean shouldUpdate,final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View view = layoutInflaterAndroid.inflate(R.layout.reservation_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(view);

        final EditText edt_reservation_Id = view.findViewById(R.id.edt_reservation_Id);
        final EditText edt_schedule_Id = view.findViewById(R.id.edt_rservation_schedule_Id);
        final EditText edt_bus_no = view.findViewById(R.id.edt_bus_no);
        final EditText edt_service = view.findViewById(R.id.edt_service);
        final EditText edt_status = view.findViewById(R.id.edt_status);

        int reservation_Id = Integer.parseInt(edt_reservation_Id.getText().toString());
        int schedule_Id = Integer.parseInt(edt_schedule_Id.getText().toString());
        int bus_no = Integer.parseInt(edt_bus_no.getText().toString());

        String service = edt_service.getText().toString();
        String status = edt_service.getText().toString();

        ReservationViewModel reservationViewModel = new ReservationViewModel(reservation_Id,schedule_Id,bus_no,service,status);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if user updating user
                if (shouldUpdate && reservationViewModel != null) {

                    // update note by it's id
                    if(updateReservation(reservationViewModel, position)){
                        Toast.makeText(getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                } else {
                    Toast.makeText(getContext(), "do something else", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void cancell_reservation(int r_Id) {
        reservationRecyclerViewListAdapter.clear();

        int reservation_Id=r_Id;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_CANCELL_RESERVATION,
                response -> {

                    try {
                        //converting response to json object
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //getting the user from the response
                            JSONObject reservationJson = obj.getJSONObject("message");
                            Toast.makeText(getContext(), reservationJson.getString("message"), Toast.LENGTH_SHORT).show();
                            reservationRecyclerViewListAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    reservationRecyclerViewListAdapter.notifyDataSetChanged();
                },

                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("reservation_Id", String.valueOf(reservation_Id));
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    private void delete_reservation(int r_Id,int position) {
        reservationRecyclerViewListAdapter.clear();
        int reservation_Id=r_Id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_DELETE_RESERVATION,
                response -> {
                    try {
                        //converting response to json object
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //getting the user from the response
                            JSONObject reservationJson = obj.getJSONObject("message");
                            reservationViewModelList.remove(position);
                            reservationRecyclerViewListAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), reservationJson.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    reservationRecyclerViewListAdapter.notifyDataSetChanged();
                },

                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("reservation_Id", String.valueOf(reservation_Id));
                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


}