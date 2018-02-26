package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chanakya.ecommerceprojectver2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {


    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    Button enter;
    EditText mobileNumber;

    String mobileNumberValue;

    Context context;
    RequestQueue queue;
    StringRequest stringRequest;

   // http://rjtmobile.com/ansari/shopingcart/androidapp/shop_fogot_pass.php?&mobile=9988776655

    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_fogot_pass.php";
    String finalUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        enter = v.findViewById(R.id.buttonForgetPassword);
        mobileNumber = v.findViewById(R.id.editTextMobileNumberForgetPassword);

        mobileNumberValue = mobileNumber.getText().toString();

        context = getContext();



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobileNumberValue = mobileNumber.getText().toString();



                finalUrl = BASE_URL + "?"+"&"+"mobile="+mobileNumberValue;

                context = getContext();
                queue = Volley.newRequestQueue(context);

                stringRequest = forgetPassword(finalUrl);

                queue.add(stringRequest);


            }
        });

        return v;
    }

    private StringRequest forgetPassword(String finalUrl) {




        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer,new MainFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        return stringRequest;


    }






}
