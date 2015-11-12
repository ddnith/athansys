package com.athansys.workingwithvolley.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.athansys.workingwithvolley.R;
import com.athansys.workingwithvolley.models.AppointmentModel;

import java.util.ArrayList;

/**
 * Created by deepak on 12/11/15.
 */
public class PatientListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<AppointmentModel> mAppointmentModelArrayList = new ArrayList<>();

    public PatientListAdapter(Context context,ArrayList<AppointmentModel> appointmentModelArrayList){
        mContext = context;
        mAppointmentModelArrayList = appointmentModelArrayList;
    }

    public void updateData(ArrayList<AppointmentModel> appointmentModelArrayList){
        mAppointmentModelArrayList.clear();
        mAppointmentModelArrayList.addAll(appointmentModelArrayList);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_patient_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AppointmentModel appointmentModel = mAppointmentModelArrayList.get(position);
        CustomViewHolder customViewHolder = (CustomViewHolder)holder;
        customViewHolder.slotIdTextView.setText(appointmentModel.getToken());
        customViewHolder.nameTextView.setText(appointmentModel.getName());
        customViewHolder.ageTextView.setText(String.valueOf(appointmentModel.getAge()));
        customViewHolder.timeTextView.setText(appointmentModel.getTime());

    }

    @Override
    public int getItemCount() {
        return mAppointmentModelArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView slotIdTextView,nameTextView,ageTextView,timeTextView;

        public CustomViewHolder(View view) {
            super(view);
            this.slotIdTextView = (TextView) view.findViewById(R.id.slotIdTextView);
            this.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            this.ageTextView = (TextView) view.findViewById(R.id.ageTextView);
            this.timeTextView = (TextView) view.findViewById(R.id.timeTextView);
        }
    }
}
