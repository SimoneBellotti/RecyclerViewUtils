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
public abstract class SortedRecyclerViewAdapter<T, VH extends RecyclerViewHolder<T>>
        extends RecyclerView.Adapter<VH> {

    protected int resId;
    protected SortedList<T> items;

    public SortedRecyclerViewAdapter(@Nullable T[] items, @LayoutRes int resId, Class<T> klazz) {
        this.items = new SortedList<>(klazz, getComparator());
        this.resId = resId;
        if (items != null) {
            this.items.addAll(items);
        }
    }

    protected abstract SortedListAdapterCallback<T> getComparator();

    protected abstract VH getViewHolder(View itemView);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
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
}