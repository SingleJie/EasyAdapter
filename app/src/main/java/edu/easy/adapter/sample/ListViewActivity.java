package edu.easy.adapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Single on 15-8-9.
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ListViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView mListView = (ListView) findViewById(R.id.lv_base);
        mAdapter = new ListViewAdapter(getResources().getStringArray(R.array.TestDatas));
        mListView.setAdapter(mAdapter);

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
