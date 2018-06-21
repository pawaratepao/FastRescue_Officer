package com.example.pawar.fastrescue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.dao.EmerPhotoItemDao;
import com.example.pawar.fastrescue.dao.PhotoItemDao;
import com.example.pawar.fastrescue.fragment.ListViewEmerPhotoFragment;
import com.example.pawar.fastrescue.fragment.ListViewNewsFragment;

public class EmerPhotoActivity extends AppCompatActivity  {

    Toolbar toolbar;
    String noti_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Bundle bundle = getIntent().getExtras();
        noti_id = bundle.getString("noti_id",null);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListViewEmerPhotoFragment.newInstance(noti_id))
                    .commit();
        }

    }
    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("รูปทั้งหมด");
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