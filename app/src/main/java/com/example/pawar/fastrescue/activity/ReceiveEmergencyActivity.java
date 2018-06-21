package com.example.pawar.fastrescue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.fragment.EmergencyFragment;
import com.example.pawar.fastrescue.fragment.MapFragment;
import com.example.pawar.fastrescue.fragment.ReceiveEmergencyFragment;
import com.example.pawar.fastrescue.fragment.ShowDirectionFragment;
import com.example.pawar.fastrescue.fragment.ShowMapFragment;

import java.util.Map;

public class ReceiveEmergencyActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_emergency);
        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mapContainer, ShowDirectionFragment.newInstance())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ReceiveEmergencyFragment.newInstance())
                    .commit();


        }


    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("ข้อมูลเหตุที่รับมา");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


}

