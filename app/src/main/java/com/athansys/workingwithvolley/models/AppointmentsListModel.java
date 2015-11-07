package com.athansys.workingwithvolley.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by deepak on 7/11/15.
 */
public class AppointmentsListModel {

    @SerializedName("appointments")
    private AppointmentsList mAppointmentsList;

    public class AppointmentsList {

        @SerializedName("doctorid")
        private int doctorId;

        @SerializedName("appointment")
        ArrayList<AppointmentModel> appointmentModelArrayList;

        public int getDoctorId() {
            return doctorId;
        }

        public ArrayList<AppointmentModel> getAppointmentModelArrayList() {
            return appointmentModelArrayList;
        }
    }

    public AppointmentsList getAppointmentsList() {
        return mAppointmentsList;
    }
}
