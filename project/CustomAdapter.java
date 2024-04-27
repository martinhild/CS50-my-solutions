package com.example.sqltest;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList trip_id, trip_distance, trip_driver, trip_start_time, trip_stop_time, trip_date;
    Animation translate_animation;

    CustomAdapter(Context context, ArrayList trip_id, ArrayList trip_distance, ArrayList trip_driver,
                  ArrayList trip_start_time, ArrayList trip_stop_time, ArrayList trip_date) {
        this.context = context;
        this.trip_id = trip_id;
        this.trip_distance = trip_distance;
        this.trip_driver = trip_driver;
        this.trip_start_time = trip_start_time;
        this.trip_stop_time = trip_stop_time;
        this.trip_date = trip_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_of_table, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_distance_txt.setText(String.valueOf(trip_distance.get(position)));
        holder.trip_driver_txt.setText(String.valueOf(trip_driver.get(position)));
        holder.trip_start_time_txt.setText(String.valueOf(trip_start_time.get(position)));
        holder.trip_stop_time_txt.setText(String.valueOf(trip_stop_time.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, trip_distance_txt, trip_driver_txt, trip_start_time_txt, trip_stop_time_txt, trip_date_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_distance_txt = itemView.findViewById(R.id.trip_distance_txt);
            trip_driver_txt = itemView.findViewById(R.id.trip_driver_txt);
            trip_start_time_txt = itemView.findViewById(R.id.trip_start_time_txt);
            trip_stop_time_txt = itemView.findViewById(R.id.trip_stop_time_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
            mainLayout.setAnimation(translate_animation);
        }
    }
}
