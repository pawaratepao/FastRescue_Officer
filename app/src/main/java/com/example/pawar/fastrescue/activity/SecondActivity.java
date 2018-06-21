package com.example.pawar.fastrescue.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.fragment.HomeMemFragment;
import com.example.pawar.fastrescue.fragment.MainFragment;
import com.google.firebase.iid.FirebaseInstanceId;

public class SecondActivity extends AppCompatActivity implements HomeMemFragment.HomeMemFragmentListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, HomeMemFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("หน้าหลัก");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void MemNewsClicked() {
        Intent intent = new Intent(SecondActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    @Override
    public void MemDetailClicked() {
        Intent intent = new Intent(SecondActivity.this, DetailActivity.class);
        startActivity(intent);

    }

    @Override
    public void MemStatusClicked() {
        Intent intent = new Intent(SecondActivity.this, StatusActivity.class);
        startActivity(intent);

    }

    @Override
    public void MemEmergencyClicked() {
        Intent intent = new Intent(SecondActivity.this, EmergencyActivity.class);
        startActivity(intent);


    }

}
