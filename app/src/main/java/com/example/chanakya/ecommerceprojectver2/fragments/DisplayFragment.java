package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class DisplayFragment extends Fragment {


    public DisplayFragment() {
        // Required empty public constructor
    }

    String apiKey;
    String userid;

    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";
    String finalUrl;

    Context context;
    RequestQueue queue;
    StringRequest stringRequest;

  //  MyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display, container, false);



        Bundle bundle = this.getArguments();

        if(bundle != null){
            apiKey = bundle.getString("apikey");
            userid = bundle.getString("userid");
        }

        Log.i("Data",apiKey+"----"+userid);


        finalUrl = BASE_URL +"?"+"api_key="+apiKey+"&"+"user_id="+userid;

          context = getContext();
          queue = Volley.newRequestQueue(context);

          stringRequest = registerWebService(finalUrl,context);

          queue.add(stringRequest);

//
//        adapter = new MyAdapter();

        return v;
    }




    private StringRequest registerWebService(String finalUrl, Context context) {



        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("Category");

                            for(int i=0;i<array.length();i++){
                                Object object = array.get(i);
                                
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
