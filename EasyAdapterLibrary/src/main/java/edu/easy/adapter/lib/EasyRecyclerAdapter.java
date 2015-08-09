package edu.easy.adapter.lib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * EasyRecyclerAdapter
 * 简介: 实现简单的列表形式,可以帮助你更快捷的开发,无须再重复实现内容
 * Created by Single on 15-8-6.
 *
 * @version 1.0
 */
public abstract class EasyRecyclerAdapter<E, T extends ViewHolder> extends RecyclerView.Adapter<RecyclerViewBaseHolder<T>> {

    private int layout;
    private Class<T> mHolderClass;
    private List<E> mList;
    private E[] mArray;

    public EasyRecyclerAdapter(List<E> mList, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mList = mList;
        this.mHolderClass = mHolderClass;
    }

    public EasyRecyclerAdapter(E[] mArray, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mArray = mArray;
        this.mHolderClass = mHolderClass;
    }

    public void updateDataSet(List<E> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    public void updateDataSet(E[] mArray) {
        this.mArray = mArray;
        this.notifyDataSetChanged();
    }

    public void addData(List<E> mList) {
        this.mList.addAll(mList);
        this.notifyItemRangeInserted(this.mList.size()-1,mList.size());
    }

    public void addData(E[] mArray) {
        int newLength = this.mArray.length + mArray.length;
        E[] mNewArray = Arrays.copyOf(this.mArray, newLength);
        for (int i = this.mArray.length; i < newLength; i++) {
            mNewArray[i] = mArray[i - this.mArray.length];
        }
        this.mArray = mNewArray;
        this.notifyItemRangeInserted(this.mArray.length,mArray.length);
    }

    public void addData(E mItem) {

        int insertPosition = 0;

        if (!EmptyUtils.emptyOfList(this.mList)) {
            this.mList.add(mItem);
            insertPosition = mList.size() - 1;
        }

        if (!EmptyUtils.emptyOfArray(this.mArray)) {
            this.mArray = Arrays.copyOf(this.mArray, this.mArray.length + 1);
            this.mArray[this.mArray.length - 1] = mItem;
            insertPosition = this.mArray.length - 1;
        }

        this.notifyItemInserted(insertPosition);
    }

    @Override
    public int getItemCount() {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.size();
        } else if (!EmptyUtils.emptyOfArray(this.mArray)) {
            return this.mArray.length;
        } else {
            return 0;
        }
    }

    public E getItem(int position) {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.get(position);
        } else if (!EmptyUtils.emptyOfArray(this.mArray)) {
            return this.mArray[position];
        } else {
            return null;
        }
    }

    @Override
    public RecyclerViewBaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layout, null);
        return new RecyclerViewBaseHolder<>(itemView, mHolderClass);
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseHolder<T> holder, int position) {
        holder.base.itemView = holder.itemView;
        onBindData(position, holder.base, getItem(position));
    }

    /**
     * 数据绑定
     *
     * @param position
     * @param holder
     * @param mItem
     */
    public abstract void onBindData(int position, T holder, E mItem);
}
