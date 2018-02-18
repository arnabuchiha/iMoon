package niot.imoon;

import android.app.ProgressDialog;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class buoy_info extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    //public static final String Firebase_Server_URL = "https://imoonnew-7e6e5.firebaseio.com/AD06/0";
    ProgressDialog progressDialog;
    String val,parm,parent;
    public String[] tagpara=
            {
                    "Parameter_____________Buoy_ID",
                    "GTS_ID",
                    "Date___Time",
                    "Batterydischarge__V_",
                    "Batteryvoltage__V_",
                    "Latitude__deg_",
                    "Longitude__deg_",
                    "Humidity____",
                    "Airpressure__hPa_",
                    "Airtemperature__degC_",
                    "Winddirection__deg_",
                    "Windspeed__m_s_",
                    "Windgust__m_s_",
                    "Currentspeed___cm_s_",
                    "Currentdirection___deg_",
                    "SST_1m__degC_",
                    "Conductivity__mmho_cm_",
                    "Hm0__m_"};
    List<buoy_parameters> list = new ArrayList<>();
    buoy_parameters buoyParameters;
    RecyclerView recyclerView;
    Firebase firebase;
    RecyclerView.Adapter adapter ;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buoy_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int buoysitem = getIntent().getIntExtra("buoys",0);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //Firebase.setAndroidContext(buoy_info.this);
        //firebase=new Firebase(Firebase_Server_URL);
        recyclerView.setLayoutManager(new LinearLayoutManager(buoy_info.this));

        progressDialog = new ProgressDialog(buoy_info.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);

        progressDialog.show();

        buoyRetrofit buoy = buoyRetrofit.retrofit.create(buoyRetrofit.class);
        Call<BuoyDataAdapter> call = buoy.buoy_data();
        call.enqueue(new Callback<BuoyDataAdapter>() {
            @Override
            public void onResponse(Call<BuoyDataAdapter> call, Response<BuoyDataAdapter> response) {
                List<Row> row = response.body().getRow();
                //See the response
                System.out.println("HAHA: " + response.raw().toString());
                buoyParameters = new buoy_parameters("GTS_ID",row.get(buoysitem).getGTSID());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Date___Time",row.get(buoysitem).getDateTime());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Batterydischarge__V_",row.get(buoysitem).getBatterydischargeV());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Batteryvoltage__V_",row.get(buoysitem).getBatteryvoltageV());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Latitude__deg_",row.get(buoysitem).getLatitudeDeg());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Longitude__deg_",row.get(buoysitem).getLongitudeDeg());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Humidity____",row.get(buoysitem).getHumidity());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Airpressure__hPa_",row.get(buoysitem).getAirpressureHPa());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Airtemperature__degC_",row.get(buoysitem).getAirtemperatureDegC());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Winddirection__deg_",row.get(buoysitem).getWinddirectionDeg());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Windspeed__m_s_",row.get(buoysitem).getWindspeedMS());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Windgust__m_s_",row.get(buoysitem).getWindgustMS());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Currentspeed___cm_s_",row.get(buoysitem).getCurrentspeedCmS());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Currentdirection___deg_",row.get(buoysitem).getCurrentdirectionDeg());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("SST_1m__degC_",row.get(buoysitem).getSST1mDegC());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Conductivity__mmho_cm_",row.get(buoysitem).getConductivityMmhoCm());
                list.add(buoyParameters);
                buoyParameters = new buoy_parameters("Hm0__m_",row.get(buoysitem).getHm0M());
                list.add(buoyParameters);

                adapter = new RecyclerViewAdapter(buoy_info.this, list);


                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();

            }


            @Override
            public void onFailure(Call<BuoyDataAdapter> call, Throwable t) {
                Toast.makeText(buoy_info.this, "Translate failed", Toast.LENGTH_LONG).show();
            }
        });

        /**
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference1 = databaseReference.child(buoys).child("0");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                databaseReference2= databaseReference.child("  Parameter             Buoy ID").child("0");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                        val = String.valueOf(dataSnapshot.getValue());
                        parent=String.valueOf(dataSnapshot.getKey());
                        parm=String.valueOf(dataSnapshot1.child(parent).getValue());
                        buoyParameters = new buoy_parameters(parm,val);
                        list.add(buoyParameters);
                    }
                    adapter = new RecyclerViewAdapter(buoy_info.this, list);

                    recyclerView.setAdapter(adapter);

                    progressDialog.dismiss();
                    }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(buoy_info.this, "error", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                }
            });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });**/

        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter = new RecyclerViewAdapter(buoy_info.this, list);
adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                mWaveSwipeRefreshLayout.setRefreshing(false);



            }
        });
}

}
