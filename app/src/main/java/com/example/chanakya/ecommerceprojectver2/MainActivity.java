package com.example.chanakya.ecommerceprojectver2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chanakya.ecommerceprojectver2.fragments.DisplayFragment;
import com.example.chanakya.ecommerceprojectver2.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new MainFragment()).addToBackStack(null).commit();



    }




}

