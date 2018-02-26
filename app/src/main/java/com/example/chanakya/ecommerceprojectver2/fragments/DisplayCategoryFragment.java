package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayCategoryFragment extends Fragment {


    public DisplayCategoryFragment() {
        // Required empty public constructor
    }

    static String apiKey;
    static String userid;

    String BASE_URL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";
    String finalUrl;

    List<Item> productItems;
    Context context;
    RequestQueue queue;
    StringRequest stringRequest;
    RecyclerView recyclerView;

    SharedPreferences sharedPreferences;

     MyAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display, container, false);

        productItems = new ArrayList<>();

        recyclerView = v.findViewById(R.id.recyclerView);


        context = getContext();
        sharedPreferences = getActivity().getSharedPreferences("userDetails",context.MODE_PRIVATE);

        userid = sharedPreferences.getString("userid","");
        apiKey = sharedPreferences.getString("apikey","");

        Log.i("Data",apiKey+"----"+userid);



        finalUrl = BASE_URL +"?"+"api_key="+apiKey+"&"+"user_id="+userid;


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
                        Log.e("CategoryResponse",response);
                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("Category");

                            for(int i=0;i<array.length();i++){
                                JSONObject data= array.getJSONObject(i);
                                String category = data.getString("CatagoryName");
                                String imageUrl = data.getString("CatagoryImage");
                                String id = data.getString("Id");
                                String description = data.getString("CatagoryDiscription");


                               Item item = new Item(category,imageUrl,id,description);

                               productItems.add(item);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new MyAdapter(getContext(), productItems,"categoryFragment");
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
