package com.example.hurestioc01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainDashboard extends AppCompatActivity {
    Toolbar toolbar;
    TextView textName,textView14,textView15,textSpeaking,textAssertivenesss,textAgresive,textActiveListning,textSelfAware,textConfidence,textBodyLanguage,textFluency,textLeadership,textSmartWork,textBehaviour,textDecipline,textClinliness,textTimeManagement;
    BottomNavigationView nabView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        toolbar=findViewById(R.id.toolbar);
        textName=findViewById(R.id.textName);
        textView14=findViewById(R.id.textView14);

        textSpeaking=findViewById(R.id.agresion_txt);
        textAssertivenesss=findViewById(R.id.effective_txt);
        textAgresive=findViewById(R.id.asertive_txt);
//        textActiveListning=findViewById(R.id.textActivelistining);
//        textSelfAware=findViewById(R.id.textSelfAware);
        textConfidence=findViewById(R.id.submissive_txt);

        textLeadership=findViewById(R.id.verbal_txt);

        textBehaviour=findViewById(R.id.selfreflection_txt);


        nabView=findViewById(R.id.nabView);
        nabView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                int id =item.getItemId();
                if (id==R.id.home_button){
                  loadFragment(new Home(),true);
                }
                else if (id==R.id.opt_videocall){
                    loadFragment(new VideoCall(),false);
                }else
                {
                loadFragment(new Meet(),false);
                }

            }

        });
        nabView.setSelectedItemId(R.id.home_button);
    }

    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        if (flag) {
            ft.add(R.id.container, fragment);
        }
        else {
            ft.replace(R.id.container,fragment);
        }
        ft.commit();


    }

}