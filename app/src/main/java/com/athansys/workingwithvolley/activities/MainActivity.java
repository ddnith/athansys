package com.athansys.workingwithvolley.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.athansys.workingwithvolley.R;
import com.athansys.workingwithvolley.adapters.PatientListAdapter;
import com.athansys.workingwithvolley.app.AppController;
import com.athansys.workingwithvolley.constatnts.UrlConstants;
import com.athansys.workingwithvolley.models.AppointmentModel;
import com.athansys.workingwithvolley.models.AppointmentsListModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Tag used to cancel the request
    String TAG_GET_PATIENTS_LIST = "get_patients_list";
    public static final String TAG = AppController.class
            .getSimpleName();

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView patientList;
    private PatientListAdapter mPatientListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                postSampleData();
            }
        });
        initUiFirstTime();
        loadPatientsList();
    }

    private void initUiFirstTime() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPatientsList();
            }
        });

        patientList = (RecyclerView) findViewById(R.id.patientList);
        patientList.setLayoutManager(new LinearLayoutManager(this));
        mPatientListAdapter = new PatientListAdapter(MainActivity.this,new ArrayList<AppointmentModel>());
        patientList.setAdapter(mPatientListAdapter);
    }

    private void postSampleData(){

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "http://httpbin.org/post";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        try {
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);
                            Toast.makeText(MainActivity.this,"Data Posted SuccessFully",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("site", "code");
                params.put("network", "tutsplus");
                return params;
            }
        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(postRequest, tag_json_obj);

    }

    private void loadPatientsList() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                UrlConstants.URL_GET_PATIENTS_LIST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                pDialog.hide();

                Gson gson = new Gson();
                AppointmentsListModel appointmentsListModel = gson.fromJson(response,AppointmentsListModel.class);
                mPatientListAdapter.updateData(appointmentsListModel.getAppointmentsList().getAppointmentModelArrayList());
                swipeContainer.setRefreshing(false);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, TAG_GET_PATIENTS_LIST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
