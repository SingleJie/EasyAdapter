package edu.easy.adapter.lib;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerViewBaseHolder 运用在RecyclerView
 *
 * @param <T>
 * @author Single
 * @version 1.0
 */
public class RecyclerViewBaseHolder<T> extends RecyclerView.ViewHolder {

    public T base;

    @SuppressWarnings("unchecked")
    public RecyclerViewBaseHolder(View itemView, Class<?> cls) {
        super(itemView);
        base = (T) ViewHolderUtils.loadingViewHolder(itemView, cls);
    }
}
