package edu.easy.adapter.sample;

import android.view.View;
import android.widget.Toast;

import java.util.List;

import edu.easy.adapter.lib.EasyRecyclerAdapter;

/**
 * Created by Single on 15-8-9.
 */
public class RecyclerViewAdapter extends EasyRecyclerAdapter<String,MyViewHolder>{

    @Override
    public void onBindData(int position, final MyViewHolder holder, String mItem) {
        holder.tv_content.setText(mItem);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeData(holder.currentPosition);
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), String.valueOf(holder.currentPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public RecyclerViewAdapter(String[] mArray) {
        super(mArray,R.layout.item_text,MyViewHolder.class);
    }

    public RecyclerViewAdapter(List<String> mList) {
        super(mList,R.layout.item_text,MyViewHolder.class);
    }
}
