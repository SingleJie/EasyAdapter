package edu.easy.adapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Single on 15-8-9.
 */
public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView mBase = (RecyclerView) findViewById(R.id.rv_base);
        LinearLayoutManager mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBase.setLayoutManager(mManager);
        mBase.setHasFixedSize(true);
        mAdapter = new RecyclerViewAdapter(getResources().getStringArray(R.array.TestDatas));
        mBase.setAdapter(mAdapter);

        findViewById(R.id.btn_single_add).setOnClickListener(this);
        findViewById(R.id.btn_multi_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_single_add:

                mAdapter.addData("New Datas");
                break;

            case R.id.btn_multi_add:

                mAdapter.addData(getResources().getStringArray(R.array.TestDatas));
                break;
        }
    }
}
