package ptdomains.tinderlikeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mPassword, mName;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioMale;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Boolean checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                Log.d("myTag", "he arribat");
                if (user != null && checker == true){
                    //User is signed in
                    Log.d("MyTagSI", "onAuthStateChanged:signed_in"+user.getUid());
                    /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();*/
                    return;
                }
                else{
                    //User is signed out
                    Log.d("MyTagSO", "onAuthStateChanged:signed_out");
                }
            }
        };

        mName = (EditText) findViewById(R.id.name);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mRadioMale = (RadioButton) findViewById(R.id.radioMale);

        mEmail = (EditText) findViewById(R.id.email);

        mPassword = (EditText) findViewById(R.id.password);

        Button mRegister = (Button) findViewById(R.id.register);


        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //final variables
                final String name = mName.getText().toString();
                int selectId = mRadioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = (RadioButton) findViewById(selectId);
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                //validation of variables
                if (!validateForm(name, selectId, email, password)){
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Log.e("error", "onComplete: Failed = " + task.getException().getMessage());
                            Toast.makeText(RegisterActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(radioButton.getText().toString()).child(userId).child("name");
                            currentUserDb.setValue(name);

                            Toast.makeText(RegisterActivity.this, "Password: "+password, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    protected boolean validateForm(String name, int selectId, String email, String password){
        boolean validate = true;

        if (selectId <= 0){
            mRadioMale.setError("Required");
            validate = false;
        }

        if (TextUtils.isEmpty(name)){
            mName.setError("Required");
            validate = false;
        }

        if (TextUtils.isEmpty(email)){
            mEmail.setError("Required");
            validate = false;
        }

        if (TextUtils.isEmpty(password)){
            mPassword.setError("Required");
            validate = false;
        }
        return validate;
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}
