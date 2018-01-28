package niot.imoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText email, pass, name, cpass;
    Button reg;
    final String TAG = "NIOT";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.password2);

        mAuthstatelistener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
            }
        };

        reg = (Button) findViewById(R.id.sign_up_button);
        //reg.setMode(ActionProcessButton.Mode.ENDLESS);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               final String emails = email.getText().toString();
                final String passs = pass.getText().toString();
                final String cpasss = cpass.getText().toString();
                final String names = name.getText().toString();

                if (TextUtils.isEmpty(emails) || TextUtils.isEmpty(passs) || TextUtils.isEmpty(cpasss) || TextUtils.isEmpty(names)){
                    Toast.makeText(Register.this,"Fields are Empty",Toast.LENGTH_LONG).show();
                }
                else if(!passs.equals(cpasss)) {
                    Toast.makeText(Register.this, "Password and Confirm Password don't match!", Toast.LENGTH_LONG).show();
                }
                else {
                      mAuth.createUserWithEmailAndPassword(emails, passs)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserEmailVerification(user);
                                        //Upload();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }
            }
        });



    }



    private void UserEmailVerification(FirebaseUser user) {
        if(user!=null)
        {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this,"Check Your Email For Verification",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        Intent intent=new Intent(Register.this,Login.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthstatelistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthstatelistener != null) {
            mAuth.removeAuthStateListener(mAuthstatelistener);
        }
    }
}


