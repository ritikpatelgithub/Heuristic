package com.example.hurestioc01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText s_name,s_email,s_pass,s_cpass,st_class,s_roll,s_instname;
    Button register_btn;
    CheckBox term_box;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        s_name = findViewById(R.id.name_edittext);
        s_email = findViewById(R.id.email_edittext);
        s_pass = findViewById(R.id.pass_edittext);
        s_cpass = findViewById(R.id.cpass_edittext);
        st_class = findViewById(R.id.tid_edittext);
        s_roll = findViewById(R.id.rollno_edittext);
        s_instname = findViewById(R.id.tspecialisation_edittext);

        firebaseDatabase=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        register_btn = findViewById(R.id.button_register);
        term_box = findViewById(R.id.termbox);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, email, pass, cpass, s_class, roll, inst_name;
                name = s_name.getText().toString().trim();
                email = s_email.getText().toString().trim();
                pass = s_pass.getText().toString().trim();
                cpass = s_cpass.getText().toString().trim();
                s_class = st_class.getText().toString().trim();
                roll = s_roll.getText().toString().trim();
                inst_name = s_instname.getText().toString().trim();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(pass) && TextUtils.isEmpty(cpass)
                        && TextUtils.isEmpty(s_class) && TextUtils.isEmpty(roll))  {
                   s_name.setError("Fill your Name");
                   s_email.setError("Fil your email");
                   s_pass.setError("Fill your error");
                   s_cpass.setError("Fill confirm password");
                   st_class.setError("Fill your class");
                   s_roll.setError("Fill your Rollno");
                   s_instname.setError("Fill your institute Name");


                }else if (TextUtils.isEmpty(inst_name)){
                    s_instname.setError("Enter your Institute");
                }
                else {
                    HashMap<String, Object> user = new HashMap<>();
                    user.put("name", s_name.getText().toString());
                    user.put("email", s_email.getText().toString());
                    user.put("password", s_pass.getText().toString());
                    user.put("class", st_class.getText().toString());
                    user.put("roll number", s_roll.getText().toString());
                    user.put("institute name", s_instname.getText().toString());
//authentication
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Register.this, "Account is created", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    realtime database

                    firebaseDatabase.getReference().child("student").push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Register.this, "created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
