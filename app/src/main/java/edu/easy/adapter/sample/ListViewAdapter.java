package edu.easy.adapter.sample;

import android.view.View;
import android.widget.Toast;

import java.util.List;

import edu.easy.adapter.lib.EasyAdapter;

/**
 * Created by Single on 15-8-9.
 */
public class ListViewAdapter extends EasyAdapter<String,MyViewHolder> {

    public ListViewAdapter(String[] mArray) {
        super(mArray,R.layout.item_text,MyViewHolder.class);
    }

    public ListViewAdapter(List<String> mList) {
        super(mList,R.layout.item_text,MyViewHolder.class);
    }

    @Override
    public void onBindData(int position,final MyViewHolder mViewHolder, String mItem) {
        mViewHolder.tv_content.setText(mItem);
        mViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeData(mViewHolder.currentPosition);
                return false;
            }
        });

        mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),String.valueOf(mViewHolder.currentPosition),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
