package edu.easy.adapter.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Single on 15-8-9.
 */
public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,R.array.MainMenus,android.R.layout.simple_list_item_1);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Class<?> mClass = null;

         switch (position){
             case 0:

                 mClass = ListViewActivity.class;
                 break;

             case 1:

                 mClass = RecyclerViewActivity.class;
                 break;
         }


        Intent mIntent = new Intent(v.getContext(),mClass);
        v.getContext().startActivity(mIntent);
    }
}
