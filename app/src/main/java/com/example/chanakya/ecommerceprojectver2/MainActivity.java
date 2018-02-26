package com.example.chanakya.ecommerceprojectver2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chanakya.ecommerceprojectver2.fragments.DisplayProductsFragment;
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
    public void show(String id,String visibleFragment) {


            if(visibleFragment.equals("categoryFragment") ) {
                Bundle bundle = new Bundle();
                bundle.putString("categoryId", id);
                DisplaySubCategoryFragment displaySubCategoryFragment = new DisplaySubCategoryFragment();

                displaySubCategoryFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, displaySubCategoryFragment)
                        .addToBackStack(null)
                        .commit();
            }
            else if(visibleFragment.equals("subCategoryFragment")){
                Bundle bundle = new Bundle();
                bundle.putString("subcategoryId", id);

                DisplayProductsFragment displayProductsFragment = new DisplayProductsFragment();
                displayProductsFragment.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,displayProductsFragment)
                        .addToBackStack(null)
                        .commit();
            }


    }
}

