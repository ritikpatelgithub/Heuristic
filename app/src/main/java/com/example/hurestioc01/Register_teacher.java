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

public class Register_teacher extends AppCompatActivity {
EditText t_name,t_email,t_password,t_cpass,t_id,t_specialization;
CheckBox termbox;
Button r_button;
FirebaseAuth auth;
FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        t_name = findViewById(R.id.name_edittext);
        t_email = findViewById(R.id.email_edittext);
        t_password = findViewById(R.id.pass_edittext);
        t_cpass = findViewById(R.id.cpass_edittext);
        t_id = findViewById(R.id.tid_edittext);
        t_specialization = findViewById(R.id.tspecialisation_edittext);
        termbox = findViewById(R.id.termbox);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        r_button=findViewById(R.id.button_register);
        r_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String tname, temail, tpass, tcpass, tid, tspecialization;
        Boolean termb;
        tname = t_name.getText().toString();
        temail = t_email.getText().toString().trim();
        tpass = t_password.getText().toString().trim();
        tcpass = t_cpass.getText().toString().trim();
        tid = t_id.getText().toString().trim();
        tspecialization = t_specialization.getText().toString();
        termb = termbox.isChecked();
        if (TextUtils.isEmpty(tname) || TextUtils.isEmpty(temail) || TextUtils.isEmpty(tpass) || TextUtils.isEmpty(tcpass)
                || TextUtils.isEmpty(tid) || TextUtils.isEmpty(tspecialization)) {
            t_name.setError("Fill your Name");
            t_email.setError("Fil your email");
            t_password.setError("Fill your error");
            t_cpass.setError("Fill confirm password");
            t_id.setError("Fill your id");
            t_specialization.setError("Fill your Rollno");
//            termbox.setError("Fill your institute Name");
        } else {
            HashMap<String, Object> user = new HashMap<>();
            user.put("name", t_name.getText().toString());
            user.put("email", t_email.getText().toString());
            user.put("password", t_password.getText().toString());
            user.put("confirm password", t_cpass.getText().toString());
            user.put("roll number", t_id.getText().toString());
            user.put("institute name", t_specialization.getText().toString());
//authentication
            auth.createUserWithEmailAndPassword(temail, tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(Register_teacher.this, "Account is created", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register_teacher.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            });
//                    realtime database

            firebaseDatabase.getReference().child("teacher").push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(Register_teacher.this, "created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_teacher.this,MainActivity.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register_teacher.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
            }
        });

        }
    }
