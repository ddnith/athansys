package com.athansys.workingwithvolley.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepak on 7/11/15.
 */
public class AppointmentModel {

    @SerializedName("patientid")
    private int patientId;

    @SerializedName("appointmentid")
    private int appointmentId;

    @SerializedName("token")
    private String token;

//    @SerializedName("phone")
//    private int phone;

    @SerializedName("age")
    private int age;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("sex")
    private String sex;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("status")
    private String status;

    @SerializedName("email")
    private String email;

    public int getPatientId() {
        return patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getToken() {
        return token;
    }

//    public int getPhone() {
//        return phone;
//    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getSex() {
        return sex;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
}
