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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class data_request extends Fragment {


    public String data[] = new String[13];
    public EditText d[] = new EditText[9];
    public DatePicker dp[] = new DatePicker[2];
    public String label[] = {"Address: ","Name: ","Buoy Details: ", "Parameters: ", "Time Duration-From date: ", "To date: ", "Phone Number: ",
            "Project Cost(Rs): ","Approval Details: ", "Own Research or Sponsored Project?:  "};
    public boolean dataforconsult;
    private View mProgressView;
    private View mLoginFormView;
    private UserLoginTask mAuthTask = null;
    public Context context;
    public String email;
    private FirebaseAuth mAuth;
    Calendar myCalendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_data_request, container, false);

        mAuth = FirebaseAuth.getInstance();


        mLoginFormView = rootView.findViewById(R.id.req_form);
        mProgressView = rootView.findViewById(R.id.login_progress);
        d[0] = (EditText)rootView.findViewById(R.id.add);
        d[1] = (EditText)rootView.findViewById(R.id.name);
        d[2] = (EditText)rootView.findViewById(R.id.bdetail);
        d[3] = (EditText)rootView.findViewById(R.id.para);
        d[4] = (EditText)rootView.findViewById(R.id.ieddate);
        d[5] = (EditText)rootView.findViewById(R.id.feddate);
        dp[0] = (DatePicker)rootView.findViewById(R.id.idate);
        d[4].setText(dp[0].getDayOfMonth() + "/" + (dp[0].getMonth() + 1) + "/" + dp[0].getYear());


        dp[1] = (DatePicker)rootView.findViewById(R.id.fdate);
        d[4].setText(dp[1].getDayOfMonth() + "/" + (dp[1].getMonth() + 1) + "/" + dp[1].getYear());

        //d[9]= d[6], d[10]= d[7]
        d[6] = (EditText)rootView.findViewById(R.id.pro);
        d[7] = (EditText)rootView.findViewById(R.id.cost);
        d[8] = (EditText)rootView.findViewById(R.id.apdet);
        d[8].setVisibility(View.GONE);
        d[7].setVisibility(View.GONE);
        RadioButton r = (RadioButton) rootView.findViewById(R.id.cons);
        r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataforconsult=true;
                d[8].setVisibility(View.VISIBLE);
                d[7].setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.apdettext).setVisibility(View.VISIBLE);
            }
        });

        RadioButton o = (RadioButton) rootView.findViewById(R.id.owr);
        o.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataforconsult=false;
                rootView.findViewById(R.id.apdettext).setVisibility(View.GONE);
                d[8].setVisibility(View.GONE);
                d[7].setVisibility(View.GONE);
            }
        });

        Button b = (Button) rootView.findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){                            // cost, approvaldetails;
                attemptUpload();
            }
        });

        return  rootView;
    }


    public void attemptUpload(){
        for(int i=0; i<9; i++)
            d[i].setError(null);

        for(int i=0; i<7; i++)
            data[i] = d[i].getText().toString();

        if(dataforconsult)
        {
            data[8] = d[8].getText().toString();
            data[7] = d[7].getText().toString();
        }

        else
        {
            data[8]="";
            data[7]="";
        }


        boolean cancel=false;
        View focusView = null;

        for(int i=0; i<7; i++)
            if(TextUtils.isEmpty(data[i])){
                d[i].setError("This field is required");
                focusView=d[i];
                cancel=true;
            }


        if(cancel) focusView.requestFocus();
        else
        {
            showProgress(true);
//            mAuthTask = new UserLoginTask(data);
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void)null);
            for(int i=0; i<9; i++)
                d[i].setText("");
        }

    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

//        UserLoginTask(String data[]) {
//        }

        @Override
        protected Boolean doInBackground(Void... params) {

            StringBuilder string = new StringBuilder();

            string.append(mAuth.getCurrentUser().getEmail().toString()).toString();

            if(dataforconsult){
                string.append("\n Sponsored Project").toString();
                for(int i=0; i<9; i++){
                    string.append("/n" + label[i]).append(" " + data[i]).toString();
                }

                string.append("\n"+label[9]).toString();
            }
            else {

                string.append("\n Own Research").toString();
                for(int i=0; i<7; i++){
                    string.append("/n" + label[i]).append(" " + data[i]).toString();
                }

                string.append("\n"+label[9]).toString();

            }
            Intent mail = new Intent(Intent.ACTION_SEND);
            mail.putExtra(Intent.EXTRA_EMAIL,new String[]{"risabhmishra19@gmail.com"});
            mail.putExtra(Intent.EXTRA_SUBJECT, new String[]{"NIOT Data Requisition"});
            mail.putExtra(Intent.EXTRA_TEXT, string.toString());
            mail.setType("message/rfc822");
            startActivity(mail);



            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if(success)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("Your request has been registered.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }

            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Connection Error");
                alertDialog.setMessage("Please check your internet connection.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

}