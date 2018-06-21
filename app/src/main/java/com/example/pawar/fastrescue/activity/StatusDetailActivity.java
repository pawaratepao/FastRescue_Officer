package com.example.pawar.fastrescue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.dao.StatusItemDao;
import com.example.pawar.fastrescue.fragment.TabStatusListDetailFragment;

public class StatusDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail);
        initInstances();
        StatusItemDao dao = getIntent().getParcelableExtra("dao");
        username = dao.getOfficialId();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, TabStatusListDetailFragment.newInstance(dao))
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("รายละเอียดข่าวสาร");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //clearLocation();
        finish();
        return super.onOptionsItemSelected(item);
    }


}

