package com.example.hurestioc01;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView txt5;
    EditText email,pass;
    Button btnLogin,btnCreateAcount,btnCreateAcountT;
    ImageButton instagram,twiter,google;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    FirebaseAuth auth;
    
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // final String TAG ="TAG";
        txt5=findViewById(R.id.forgot_textview);
        email=findViewById(R.id.email_edittext);
        pass=findViewById(R.id.pass_edittext);
        btnLogin=findViewById(R.id.btnLogin);
        btnCreateAcount=findViewById(R.id.btnCreateAcountS);
        btnCreateAcountT=findViewById(R.id.btnCreateAcountT);
        instagram=findViewById(R.id.instagram);
        twiter=findViewById(R.id.twiter);
        google=findViewById(R.id.google);
        auth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails=email.getText().toString().trim();
                String passs=pass.getText().toString().trim();



                if(TextUtils.isEmpty(emails)){
                   email.setError("please fill the mail");
                    if (emails.matches("\"[a-zA-Z0-9._-]+@[a-z]+\\\\ .+[a-z]+\""));
                    else {
                        Toast.makeText(MainActivity.this, "invalid mail", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (TextUtils.isEmpty(passs)){
                    pass.setError("Please Enter Password");


                }else{
                    auth.signInWithEmailAndPassword(emails,passs).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Dashboard.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
        
       
        
        
        
        
        
        
        
        

        Intent teacher = new Intent(MainActivity.this,Register_teacher.class);
        btnCreateAcountT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(teacher);
            }
        });
        Intent forgot =new Intent(MainActivity.this,Forgot.class);

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(forgot);
            }
        });
        Intent register =new Intent(MainActivity.this, Register.class);

        btnCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(register);}
        });
    }
}