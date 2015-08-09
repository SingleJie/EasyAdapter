package edu.easy.adapter.lib;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * EasyAdapter
 * 简介: 实现简单的列表形式,可以帮助你更快捷的开发,无须再重复实现内容
 * Created by Single on 15-7-18.
 *
 * @version 1.0
 */
public abstract class EasyAdapter<E, T extends ViewHolder> extends BaseAdapter {

    private int layout;
    private Class<T> mHolderClass;
    private List<E> mList;
    private E[] mArray;

    public EasyAdapter(List<E> mList, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mList = mList;
        this.mHolderClass = mHolderClass;
    }

    public EasyAdapter(E[] mArray, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mArray = mArray;
        this.mHolderClass = mHolderClass;
    }

    private EasyAdapter() {
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
        this.notifyDataSetChanged();
    }

    public void addData(E[] mArray) {
        int newLength = this.mArray.length + mArray.length;
        E[] mNewArray = Arrays.copyOf(this.mArray, newLength);
        for (int i = this.mArray.length; i < newLength; i++) {
            mNewArray[i] = mArray[i - this.mArray.length];
        }
        this.mArray = mNewArray;
        this.notifyDataSetChanged();
    }

    public void addData(E mItem) {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            this.mList.add(mItem);
        }

        if (!EmptyUtils.emptyOfArray(this.mArray)) {
            this.mArray = Arrays.copyOf(this.mArray, this.mArray.length + 1);
            this.mArray[this.mArray.length - 1] = mItem;
        }
        this.notifyDataSetChanged();
    }

    public E[] getArrayDataSet() {
        return mArray;
    }

    public List<E> getListDataSet() {
        return mList;
    }

    @Override
    public int getCount() {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.size();
        }
        else if (!EmptyUtils.emptyOfArray(this.mArray)) {
            return this.mArray.length;
        }else {
            return 0;
        }
    }

    @Override
    public E getItem(int position) {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.get(position);
        }else if (!EmptyUtils.emptyOfArray(this.mArray)) {
            return this.mArray[position];
        }else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = ViewHolderUtils.loadingConvertView(parent.getContext(), convertView, layout, mHolderClass);
        T mHolder = (T) convertView.getTag();
        mHolder.itemView = convertView;
        onBindData(position, mHolder, getItem(position));
        return convertView;
    }

    /**
     * 进行数据绑定
     *
     * @param position
     * @param mViewHolder
     * @param mItem
     */
    public abstract void onBindData(int position, T mViewHolder, E mItem);
}
