package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chanakya.ecommerceprojectver2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {


    public RegistrationFragment() {
        // Required empty public constructor
    }

    EditText username,password,email,phoneNumber;
    Button register;

    String usernameValue,passwordValue,emailValue,phoneNumberValue;

    String ADDRESS_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reg.php";
    static String finalUrl;

    RequestQueue queue;
    StringRequest stringRequest;
    Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registration, container, false);;

        username = v.findViewById(R.id.editTextUsernameRegistration);
        password = v.findViewById(R.id.editTextPasswordRegistration);
        email = v.findViewById(R.id.editTextEmailRegistration);
        phoneNumber = v.findViewById(R.id.editTextPhoneNumberRegistration);
        register = v.findViewById(R.id.buttonRegisterRegistrationFragment);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();
                emailValue    = email.getText().toString();
                phoneNumberValue = phoneNumber.getText().toString();


                if(!(username.getText().toString().equals(null) ||
                        password.getText().toString().equals(null) ||
                    email.getText().toString().equals(null) ||
                    phoneNumber.getText().toString().equals(null) )
                        ) {


                    if (username.getText().toString().length() > 6 &&
                            password.getText().toString().length() > 6 &&
                            phoneNumber.getText().toString().length() == 10
                            ) {


                        String[] details = {usernameValue, passwordValue, emailValue, phoneNumberValue};


                        finalUrl = ADDRESS_URL + "?" + "name=" + usernameValue + "&" + "email=" + emailValue +
                                "&" + "mobile=" + phoneNumberValue + "&" + "password=" + passwordValue;

                        context = getContext();
                        queue = Volley.newRequestQueue(context);

                        stringRequest = registerWebService(finalUrl, context, details);

                        queue.add(stringRequest);

                        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).addToBackStack(null).commit();

                    } else {
                        Toast.makeText(getContext(), "Password,Username should be atleast 6 Characters and Mobile number should be 10 numbers ", Toast.LENGTH_LONG).show();
                        ;
                    }

                }
                else {
                    Toast.makeText(getContext(),"All the fields should be filled",Toast.LENGTH_LONG).show();;
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }


    private StringRequest registerWebService(String finalUrl, final Context context, final String[] details) {

        final String[] userDetails = details;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Responce","failed");
                    }
                });

        return stringRequest;
    }
}
