# EasyAdapter
EasyAdapter是一款可以快速书写 adapter的框架，只适用item相同的形式

#如何使用?

#EasyAdapter(适用与ListViewAdapter)









EasyAdapter<E,T extends ViewHolder> E表示List<E>中的参数，T表示ViewHolder

1.继承ViewHolder 属性名称必须和xml控件名称一致 (ps:如果是内部类则需要设置为static)

public class MyViewHolder extends ViewHolder {

    public  TextView tv_content;
    
}


# xml

<?xml version="1.0" encoding="utf-8"?>
    
    <TextView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:minHeight="48dp"
    android:textColor="@android:color/black"
    android:paddingLeft="16dp"
    android:paddingRight="16dp"
    android:gravity="center_vertical"
    android:textSize="15sp"
    android:id="@+id/tv_content"
    />
    

2.继承EasyAdapter，绑定数据
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
    }
}


#EasyRecyclerAdapter 适用于RecyclerView 使用方法于EasyAdapter雷同

