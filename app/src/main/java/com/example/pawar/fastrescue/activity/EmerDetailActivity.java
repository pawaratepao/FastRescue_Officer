package com.example.pawar.fastrescue.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.dao.EmerItemDao;
import com.example.pawar.fastrescue.dao.PhotoItemDao;
import com.example.pawar.fastrescue.fragment.AlertDetailFragment;
import com.example.pawar.fastrescue.fragment.EmerListDetailFragment;
import com.example.pawar.fastrescue.fragment.NewsDetailFragment;
import com.example.pawar.fastrescue.fragment.ShowMapFragment;
import com.example.pawar.fastrescue.fragment.ShowMapListFragment;
import com.example.pawar.fastrescue.fragment.TabEmerListDetailFragment;

public class EmerDetailActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emer_detail);
        initInstances();
        EmerItemDao dao = getIntent().getParcelableExtra("dao");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, TabEmerListDetailFragment.newInstance(dao))
                    .commit();


        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("รายละเอียดเหตุ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}

