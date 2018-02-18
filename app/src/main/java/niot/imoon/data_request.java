package niot.imoon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Create by Risabh Mishra
 */
public class data_request extends Fragment {


    public String data[] = new String[13];
    public StringBuilder buoys = new StringBuilder();
    public StringBuilder paras = new StringBuilder();
    public EditText d[] = new EditText[9];
    public DatePicker dp[] = new DatePicker[2];
    public String label[] = {"Address: ", "Name: ", "Buoy Details: ", "Parameters: ", "Time Duration:-\n\nFrom date: ", "To date: ", "Phone Number: ",
            "Project Cost(Rs): ", "Approval Details: "};
    public boolean dataforconsult;
    private View mProgressView;
    private View mLoginFormView;
    public Context context;
    String buoy, para;
    public String email;
    private FirebaseAuth mAuth;
    Calendar myCalendar;
    public String bid[] = {"CALVAL", "CB01", "CB02", "CB04", "CB06", "AD06", "AD07", "AD08", "AD09", "AD10", "BD08", "BD09", "BD10", "BD11", "BD12", "BD13", "BD14"};
    public String[] tagpara =
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_data_request, container, false);

        mAuth = FirebaseAuth.getInstance();
        context = getContext();

        mLoginFormView = rootView.findViewById(R.id.req_form);
        mProgressView = rootView.findViewById(R.id.login_progress);
        d[0] = (EditText) rootView.findViewById(R.id.add);
        d[1] = (EditText) rootView.findViewById(R.id.name);

        final List<String> list = Arrays.asList(bid);

        final List<KeyPairBoolData> listArray0 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }

        MultiSpinnerSearch searchMultiSpinnerUnlimited = (MultiSpinnerSearch) rootView.findViewById(R.id.buoyspin);
        searchMultiSpinnerUnlimited.setItems(listArray0, -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        buoys.append(items.get(i).getName().toString() + ",").toString();
                    }
                }
            }
        });


        final List<String> list1 = Arrays.asList(tagpara);

        final List<KeyPairBoolData> listArray1 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list1.get(i));
            h.setSelected(false);
            listArray1.add(h);
        }

        MultiSpinnerSearch searchMultiSpinnerUnlimited1 = (MultiSpinnerSearch) rootView.findViewById(R.id.paraspin);
        searchMultiSpinnerUnlimited1.setItems(listArray1, -1, new SpinnerListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        paras.append(items.get(i).getName().toString() + ",").toString();
                    }
                }
            }
        });


        d[4] = (EditText) rootView.findViewById(R.id.idate);
        d[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                d[4].setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });


        d[5] = (EditText) rootView.findViewById(R.id.fdate);
        d[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                d[5].setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        d[6] = (EditText) rootView.findViewById(R.id.pro);
        d[7] = (EditText) rootView.findViewById(R.id.cost);
        d[8] = (EditText) rootView.findViewById(R.id.apdet);
        d[8].setVisibility(View.GONE);
        d[7].setVisibility(View.GONE);
        RadioButton r = (RadioButton) rootView.findViewById(R.id.cons);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataforconsult = true;
                d[8].setVisibility(View.VISIBLE);
                d[7].setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.apdettext).setVisibility(View.VISIBLE);
            }
        });

        RadioButton o = (RadioButton) rootView.findViewById(R.id.owr);
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataforconsult = false;
                rootView.findViewById(R.id.apdettext).setVisibility(View.GONE);
                d[8].setVisibility(View.GONE);
                d[7].setVisibility(View.GONE);
            }
        });

        Button b = (Button) rootView.findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                            // cost, approvaldetails;
                attemptUpload();
            }
        });

        return rootView;
    }


    public void attemptUpload() {

        for (int i = 0; i < 9; i++) {
            if (i != 2 && i != 3)
                d[i].setError(null);
        }

        for (int i = 0; i < 7; i++) {
            if (i == 2)
                data[i] = buoys.toString();
            else if (i == 3)
                data[i] = paras.toString();
            else
                data[i] = d[i].getText().toString();
        }

        if (dataforconsult) {
            data[8] = d[8].getText().toString();
            data[7] = d[7].getText().toString();
        } else {
            data[8] = "";
            data[7] = "";
        }


        boolean cancel = false;
        View focusView = null;

        for (int i = 4; i < 7; i++)
            if (TextUtils.isEmpty(data[i])) {
                d[i].setError("This field is required");
                focusView = d[i];
                cancel = true;
            }

        StringBuilder string = new StringBuilder();

        if (dataforconsult) {
            string.append("\n Sponsored Project").toString();
            for (int i = 0; i < 9; i++) {
                string.append("\n\n" + label[i]).append(" " + data[i]).toString();
            }

        } else {

            string.append("\n\n Own Research").toString();
            for (int i = 0; i < 7; i++) {
                string.append("\n\n" + label[i]).append(" " + data[i]).toString();
            }


        }
        Intent mail = new Intent(Intent.ACTION_SEND);
        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"venkat@incois.gov.in"});
        mail.putExtra(Intent.EXTRA_CC, new String[]{"rsundar@niot.res.in"});
        mail.putExtra(Intent.EXTRA_SUBJECT, new String[]{"NIOT Data Requisition"});
        mail.putExtra(Intent.EXTRA_TEXT, string.toString());
        mail.setType("message/rfc822");
        for (int i = 0; i < 9; i++) {
            if (i != 2 && i != 3)
                d[i].setText("");
        }
        startActivity(mail);


    }

}


