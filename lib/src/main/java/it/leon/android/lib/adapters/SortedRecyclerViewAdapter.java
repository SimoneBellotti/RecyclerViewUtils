package it.leon.android.lib.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.leon.android.lib.listeners.OnRVItemClickListener;
import it.leon.android.lib.listeners.OnRVLongItemClickListener;
import it.leon.android.lib.recyclerview.RecyclerViewHolder;

/**
 * Created with Android Studio.
 * User: Simone Bellotti
 * Date: 29/05/2015
 * Time: 14.35
 * App:  RecyclerViewUtils
 */
public abstract class SortedRecyclerViewAdapter<T>
        extends RecyclerView.Adapter<RecyclerViewHolder<T>>
        implements View.OnClickListener, View.OnLongClickListener {

    protected Context context;
    protected int resId;
    protected SortedList<T> items;
    protected OnRVItemClickListener<T> onItemClickListener;
    protected OnRVLongItemClickListener<T> onItemLongClickListener;

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

    protected abstract SortedListAdapterCallback<T> getComparator();

    protected abstract RecyclerViewHolder<T> getViewHolder(View itemView);

    @Override
    public RecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return getViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        T item = items.get(position);
        holder.itemView.setTag(item);
        holder.bindItem(item);
    }

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

    public SortedRecyclerViewAdapter setOnItemClickListener(OnRVItemClickListener<T> listener) {
        this.onItemClickListener = listener;
        return this;
    }

    public SortedRecyclerViewAdapter setOnItemLongClickListener(OnRVLongItemClickListener<T> listener) {
        this.onItemLongClickListener = listener;
        return this;
    }
}