package com.example.chanakya.ecommerceprojectver2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {


    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    Button resetPassword;
    EditText mobileNumber,oldPassword,newPassword;

    String BASE_URL ="http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php";
    String finalUrl;

    String mobileNumberValue;
    String oldPasswordValue;
    String newPasswordValue;

    Context context;
    RequestQueue queue;
    StringRequest stringRequest;

   // http://rjtmobile.com/ansari/shopingcart/androidapp/
    // shop_reset_pass.php?&mobile=1&password=2&newpassword=456

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reset_password, container, false);

        resetPassword = v.findViewById(R.id.buttonResetPassword);
        mobileNumber = v.findViewById(R.id.editTextMobileNumberReset);
        oldPassword = v.findViewById(R.id.editTextOldPassword);
        newPassword = v.findViewById(R.id.editTextNewPassword);



          resetPassword.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  mobileNumberValue = mobileNumber.getText().toString();
                  oldPasswordValue = oldPassword.getText().toString();
                  newPasswordValue = newPassword.getText().toString();

                  finalUrl = BASE_URL+ "?"+ "&"+"mobile="+mobileNumberValue+"&"+"password="+oldPassword+"&"+"newpassword="+newPassword;


                  context = getContext();
                  queue = Volley.newRequestQueue(context);

                  stringRequest = resetPasswordNew(finalUrl);

                  queue.add(stringRequest);
              }
          });

        return v;
    }

    private StringRequest resetPasswordNew(String finalUrl) {

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
