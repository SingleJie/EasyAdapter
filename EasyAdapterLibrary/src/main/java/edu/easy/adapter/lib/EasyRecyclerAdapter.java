package edu.easy.adapter.lib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public EasyRecyclerAdapter(List<E> mList, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mList = mList;
        this.mHolderClass = mHolderClass;
    }

    public EasyRecyclerAdapter(E[] mArray, int layout, Class<T> mHolderClass) {
        this.layout = layout;
        this.mList = arrayToList(mArray);
        this.mHolderClass = mHolderClass;
    }

    private List<E> arrayToList(E[] mArray){
        int length = mArray.length;
        List<E> mList = new ArrayList<>();
        for(int i=0;i<length;i++){
            mList.add(mArray[i]);
        }
        return mList;
    }

    private E[] listToArray(List<E> mList){
        int length = mList.size();
        E[] mNewArray = (E[]) Array.newInstance(mList.getClass().getComponentType(), length);
        return mNewArray;
    }

    public void updateDataSet(List<E> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    public void updateDataSet(E[] mArray) {
        this.mList = arrayToList(mArray);
        this.notifyDataSetChanged();
    }

    public void addData(List<E> mList) {
        this.mList.addAll(mList);
        this.notifyItemRangeInserted(this.mList.size() - 1, mList.size());
    }

    public void addData(E[] mArray) {
        int listSize = this.mList.size();
        int length = mArray.length;
        for(int i=0;i<length;i++){
            this.mList.add(mArray[i]);
        }
        this.notifyItemRangeInserted(listSize, mArray.length);
    }

    public void addData(E mItem) {

        int insertPosition = 0;

        if (!EmptyUtils.emptyOfList(this.mList)) {
            this.mList.add(mItem);
            insertPosition = mList.size() - 1;
        }
        this.notifyItemInserted(insertPosition);
    }

    public E[] getArrayDataSet() {
        return listToArray(mList);
    }

    public List<E> getListDataSet() {
        return mList;
    }

    public void removeData(E mItem){
        if(!EmptyUtils.emptyOfList(mList)){
            this.mList.remove(mItem);
        }
        this.notifyDataSetChanged();
    }

    public void removeData(int position){

        if(!EmptyUtils.emptyOfList(mList)){
            mList.remove(position);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.size();
        }else {
            return 0;
        }
    }

    public E getItem(int position) {

        if (!EmptyUtils.emptyOfList(this.mList)) {
            return this.mList.get(position);
        }else {
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
        holder.base.currentPosition = position;
        onBindData(position, holder.base, getItem(position));
    }

    /**
     * 数据绑定
     * @param position
     * @param holder
     * @param mItem
     */
    public abstract void onBindData(int position, T holder, E mItem);
}
