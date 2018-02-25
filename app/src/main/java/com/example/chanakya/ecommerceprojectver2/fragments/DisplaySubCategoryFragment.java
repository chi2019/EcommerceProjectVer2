package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplaySubCategoryFragment extends Fragment {


    public DisplaySubCategoryFragment() {
        // Required empty public constructor
    }

    static String apiKey;
    static String userid;
    static String categoryid;

    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php";
    String finalUrl;

    ArrayList<Item> subCateroryItems;
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
        categoryid = bundle.getString("categoryId");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_sub_category, container, false);

        context = getActivity();
        sharedPreferences = context.getSharedPreferences("userDetails",context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");
        apiKey = sharedPreferences.getString("apikey","");

        recyclerView = v.findViewById(R.id.recyclerViewSubCategory);

        subCateroryItems = new ArrayList<>();


    //    categoryid = sharedPreferences.getString("categoryId","");


       // http://rjtmobile.com/ansari/shopingcart/androidapp/
        // cust_sub_category.php?Id=107&api_key=kflasfkla&user_id=2

        finalUrl = BASE_URL+"?"+"Id="+categoryid+"&"+"api_key="+ apiKey+"&"+"user_id="+userid;

        queue = Volley.newRequestQueue(context);

        stringRequest = registerWebService(finalUrl,context);

        queue.add(stringRequest);

        return v;
    }


    private StringRequest registerWebService(String finalUrl, final Context context) {



        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                finalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("SubCategory");

                            for(int i=0;i<array.length();i++){
                                JSONObject data= array.getJSONObject(i);
                                String category = data.getString("SubCatagoryName");
                                String imageUrl = data.getString("CatagoryImage");
                                String id = data.getString("Id");
                                String description = data.getString("SubCatagoryDiscription");

                                Item item = new Item(category,imageUrl,id,description);

                                subCateroryItems.add(item);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new MyAdapter(getContext(), subCateroryItems);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);


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
