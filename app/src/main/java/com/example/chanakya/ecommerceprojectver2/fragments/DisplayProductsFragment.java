package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import com.example.chanakya.ecommerceprojectver2.adapter.MyAdapter;
import com.example.chanakya.ecommerceprojectver2.model.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayProductsFragment extends Fragment {


    public DisplayProductsFragment() {
        // Required empty public constructor
    }

    static String apiKey;
    static String userid;
    static String subcategoryid;

    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php";
    String finalUrl;

    ArrayList<Item> productList;
    Context context;
    RequestQueue queue;
    StringRequest stringRequest;
    RecyclerView recyclerView;

    SharedPreferences sharedPreferences;

    MyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        subcategoryid = bundle.getString("subcategoryId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_display_products, container, false);

        //http://rjtmobile.com/ansari/shopingcart/androidapp/
        // cust_product.php?Id=205&api_key=kflasfkla&user_id=2



        context = getActivity();
        sharedPreferences = context.getSharedPreferences("userDetails",context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");
        apiKey = sharedPreferences.getString("apikey","");

        finalUrl = BASE_URL+"?" + "Id=" + subcategoryid+"&"+"api_key="+apiKey+"&"+"user_id="+userid;

    //    recyclerView = v.findViewById(R.id.recyclerViewProductsDisplay);
        productList = new ArrayList<>();

        queue = Volley.newRequestQueue(context);

        stringRequest = registerWebService(finalUrl,context);

        queue.add(stringRequest);

        return v;
    }

    private StringRequest registerWebService(String finalUrl, final Context context) {



        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
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
