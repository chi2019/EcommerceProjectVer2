package com.example.chanakya.ecommerceprojectver2.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chanakya.ecommerceprojectver2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    Button register,login;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        register = v.findViewById(R.id.buttonRegistationMain);
        login = v.findViewById(R.id.buttonLoginMain);

        register.setOnClickListener(this);
        login.setOnClickListener(this);




        return v;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonRegistationMain:
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new RegistrationFragment()).addToBackStack(null).commit();

                break;

            case R.id.buttonLoginMain:
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new LoginFragment()).addToBackStack(null).commit();

                break;


            case R.id.buttonForgetPasswordMain:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,new ForgotPasswordFragment())
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.buttonResetPasswordMain:

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer,new ResetPasswordFragment())
                            .addToBackStack(null)
                            .commit();
                break;

        }

    }
}
