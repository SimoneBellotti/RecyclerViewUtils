package it.leon.android.lib.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.View;

import it.leon.android.lib.listeners.OnRecyclerViewItemClickListener;
import it.leon.android.lib.listeners.OnRecyclerViewLongItemClickListener;

/**
 * Created with Android Studio.
 * User: Simone Bellotti
 * Date: 29/05/2015
 * Time: 14.35
 * App:  RecyclerViewUtils
 */
public abstract class SortedRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>
        implements View.OnClickListener, View.OnLongClickListener {

    protected Context context;
    protected int resId;
    protected SortedList<T> items;
    protected OnRecyclerViewItemClickListener<T> onItemClickListener;
    protected OnRecyclerViewLongItemClickListener<T> onItemLongClickListener;

    public SortedRecyclerViewAdapter(@NonNull Context context, @Nullable T[] items, @LayoutRes int resId, Class<T> klazz) {
        this.items = new SortedList<>(klazz, getComparator());
        this.resId = resId;
        this.context = context;
        if (items != null) {
            for (T obj : items) {
                this.items.add(obj);
            }
        }
    }

    public abstract SortedListAdapterCallback<T> getComparator();

    public int add(T item) {
        return items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            T item = (T) v.getTag();
            onItemClickListener.onItemClick(v, item);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null) {
            T item = (T) v.getTag();
            onItemLongClickListener.onLongItemClick(v, item);
            return true;
        }
        return false;
    }

    public SortedRecyclerViewAdapter setOnItemClickListener(OnRecyclerViewItemClickListener<T> listener) {
        this.onItemClickListener = listener;
        return this;
    }

    public SortedRecyclerViewAdapter setOnItemLongClickListener(OnRecyclerViewLongItemClickListener<T> listener) {
        this.onItemLongClickListener = listener;
        return this;
    }
}