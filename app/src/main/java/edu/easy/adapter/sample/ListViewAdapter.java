package edu.easy.adapter.sample;

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
    public void onBindData(int position,MyViewHolder mViewHolder, String mItem) {
        mViewHolder.tv_content.setText(mItem);
    }
}
