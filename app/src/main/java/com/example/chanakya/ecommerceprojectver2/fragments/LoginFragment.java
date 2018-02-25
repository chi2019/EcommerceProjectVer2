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
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    Button login;
    EditText mobileNumber,password;

    String mobileNumberValue,passwordValue;

    SharedPreferences sharedPreferences;

    Context context;
    RequestQueue queue;
    StringRequest stringRequest;

    static String userid;
    static String apikey;


    String finalUrl ;
    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View v =inflater.inflate(R.layout.fragment_login, container, false);

        login = v.findViewById(R.id.buttonLoginLaoginFragment);
        mobileNumber = v.findViewById(R.id.editTextMobileNumberLogin);
        password = v.findViewById(R.id.editTextPasswordLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mobileNumberValue = mobileNumber.getText().toString();
                passwordValue = password.getText().toString();
                String[] details = {mobileNumberValue,passwordValue};

                finalUrl = BASE_URL +"?"+"mobile="+mobileNumberValue+"&"+"password="+passwordValue;

                context = getContext();
                queue = Volley.newRequestQueue(context);

                stringRequest = checkUserRequest(finalUrl,context,details);

                queue.add(stringRequest);




            }
        });



        return v;
    }



    private StringRequest checkUserRequest(String finalUrl, final Context context, String[] details) {

        final String[] userDetails = details;


        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                           JSONArray array = new JSONArray(response);
                           JSONObject object = array.getJSONObject(0);
                            userid = object.getString("UserID");
                            apikey = object.getString("AppApiKey ");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finally {
                            DisplayCategoryFragment displayCategoryFragment = new DisplayCategoryFragment();

                            sharedPreferences = getActivity().getSharedPreferences("userDetails",context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            Log.i("userid",userid);
                            Log.i("apikey",apikey);

                            editor.putString("userid",userid);
                            editor.putString("apikey",apikey);
                            editor.commit();



                            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, displayCategoryFragment).addToBackStack(null).commit();

                        }

                    //    Log.e("Response",api_key);
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
