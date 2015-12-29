package it.leon.android.lib.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import it.leon.android.lib.recyclerview.RecyclerViewHolder;

/**
 * Created with Android Studio.
 * User: Simone Bellotti
 * Date: 29/05/2015
 * Time: 14.35
 * App:  RecyclerViewUtils
 */
public abstract class RecyclerViewAdapter<T, VH extends RecyclerViewHolder<T>>
        extends RecyclerView.Adapter<VH> {

    protected int resId;
    protected ArrayList<T> items;

    public RecyclerViewAdapter(@Nullable T[] items, @LayoutRes int resId) {
        this(items == null ? null : new ArrayList<>(Arrays.asList(items)), resId);
    }

    public RecyclerViewAdapter(@Nullable ArrayList<T> items, @LayoutRes int resId) {
        this.items = items == null ? new ArrayList<T>() : items;
        this.resId = resId;
    }

    protected abstract VH getViewHolder(View itemView);

//    protected abstract void onBindView(VH holder, int position, T item);

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

//    @Override
//    public void onBindViewHolder(VH holder, int position) {
//        T item = items.get(position);
//        holder.itemView.setTag(item);
//        onBindView(holder, position, item);
//    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(T item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.indexOf(item));
    }

    public T remove(int position) {
        T item = items.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    public T remove(T item) {
        return remove(items.indexOf(item));
    }
}
