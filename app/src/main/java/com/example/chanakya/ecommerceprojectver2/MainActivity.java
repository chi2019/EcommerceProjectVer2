package com.example.chanakya.ecommerceprojectver2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chanakya.ecommerceprojectver2.fragments.DisplaySubCategoryFragment;
import com.example.chanakya.ecommerceprojectver2.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new MainFragment()).addToBackStack(null).commit();



    }


    @Override
    public void show(String categoryId) {

        DisplaySubCategoryFragment displaySubCategoryFragment = new DisplaySubCategoryFragment();

        Bundle bundle = new Bundle();
        bundle.putString("categoryId",categoryId);
        displaySubCategoryFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,displaySubCategoryFragment)
                .addToBackStack(null)
                .commit();
    }
}

