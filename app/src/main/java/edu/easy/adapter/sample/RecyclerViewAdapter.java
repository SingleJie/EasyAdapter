package edu.easy.adapter.sample;

import java.util.List;

import edu.easy.adapter.lib.EasyRecyclerAdapter;

/**
 * Created by Single on 15-8-9.
 */
public class RecyclerViewAdapter extends EasyRecyclerAdapter<String,MyViewHolder>{

    @Override
    public void onBindData(int position, MyViewHolder holder, String mItem) {
        holder.tv_content.setText(mItem);
    }

    public RecyclerViewAdapter(String[] mArray) {
        super(mArray,R.layout.item_text,MyViewHolder.class);
    }

    public RecyclerViewAdapter(List<String> mList) {
        super(mList,R.layout.item_text,MyViewHolder.class);
    }
}
