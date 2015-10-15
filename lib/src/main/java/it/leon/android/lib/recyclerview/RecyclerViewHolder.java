package it.leon.android.lib.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class RecyclerViewHolder<T>
        extends RecyclerView.ViewHolder {

    protected RecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindItem(T item);

    public void setOnItemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

}
