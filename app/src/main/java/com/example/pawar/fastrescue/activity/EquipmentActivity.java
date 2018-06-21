package com.example.pawar.fastrescue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pawar.fastrescue.R;

import java.util.HashMap;
import java.util.Map;

public class EquipmentActivity extends AppCompatActivity {
    final String URL = "http://pohtecktung.welovepc.com/newproject/android/update_equi.php";
    Button commit;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10;
    private String[] st = new String[11];
    //String st1,st2,st3,st4,st5,st6,st7,st8,st9,st10;
    String id,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        initInstances();
        setData();
    }


    private void initInstances() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked())
                    st[1] = "1";
                else
                    st[1] = "0";

            }
        });
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb2.isChecked())
                    st[2] = "1";
                else
                    st[2] = "0";

            }
        });
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb3.isChecked())
                    st[3] = "1";
                else
                    st[3] = "0";

            }
        });
        cb4 = (CheckBox) findViewById(R.id.cb4);
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb4.isChecked())
                    st[4] = "1";
                else
                    st[4] = "0";

            }
        });

        cb5 = (CheckBox) findViewById(R.id.cb5);
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb5.isChecked())
                    st[5] = "1";
                else
                    st[5] = "0";

            }
        });
        cb6 = (CheckBox) findViewById(R.id.cb6);
        cb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb6.isChecked())
                    st[6] = "1";
                else
                    st[6] = "0";

            }
        });
        cb7 = (CheckBox) findViewById(R.id.cb7);
        cb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb7.isChecked())
                    st[7] = "1";
                else
                    st[7] = "0";

            }
        });
        cb8 = (CheckBox) findViewById(R.id.cb8);
        cb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb8.isChecked())
                    st[8] = "1";
                else
                    st[8] = "0";

            }
        });
        cb9 = (CheckBox) findViewById(R.id.cb9);
        cb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb9.isChecked())
                    st[9] = "1";
                else
                    st[9] = "0";

            }
        });
        cb10 = (CheckBox) findViewById(R.id.cb10);
        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb10.isChecked())
                    st[10] = "1";
                else
                    st[10] = "0";

            }
        });
        commit = (Button) findViewById(R.id.btcommit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
                sendData();

            }
        });


    }

    @Override
    public void onBackPressed() {
    }

    public void setData(){
        for(int i = 0;i<=10;i++){
            st[i] = "0";
        }
    }

    public void showData(){
        for(int i = 0;i<=10;i++){
            show = show+","+st[i];


        }
    }


    public void sendData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                Toast.makeText(EquipmentActivity.this, response, Toast.LENGTH_SHORT).show();
                Toast.makeText(EquipmentActivity.this, "อัพเดทข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                finish();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("official_id", id);
                params.put("skill_1", st[1]);
                params.put("skill_2", st[2]);
                params.put("skill_3", st[3]);
                params.put("skill_4", st[4]);
                params.put("skill_5", st[5]);
                params.put("skill_6", st[6]);
                params.put("skill_7", st[7]);
                params.put("skill_8", st[8]);
                params.put("skill_9", st[9]);
                params.put("skill_10", st[10]);


                return params;
            }
        };
        requestQueue.add(request);
    }

}
