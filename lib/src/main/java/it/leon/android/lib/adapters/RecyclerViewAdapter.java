package it.leon.android.lib.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import it.leon.android.lib.listeners.OnRecyclerViewItemClickListener;
import it.leon.android.lib.listeners.OnRecyclerViewLongItemClickListener;
import it.leon.android.lib.recyclerview.RecyclerViewHolder;

/**
 * Created with Android Studio.
 * User: Simone Bellotti
 * Date: 29/05/2015
 * Time: 14.35
 * App:  RecyclerViewUtils
 */
public abstract class RecyclerViewAdapter<T>
        extends RecyclerView.Adapter<RecyclerViewHolder<T>>
        implements View.OnClickListener, View.OnLongClickListener {

    protected Context context;
    protected int resId;
    protected ArrayList<T> items;
    protected OnRecyclerViewItemClickListener<T> onItemClickListener;
    protected OnRecyclerViewLongItemClickListener<T> onItemLongClickListener;

    public RecyclerViewAdapter(@NonNull Context context, @Nullable T[] items, @LayoutRes int resId) {
        this(context,
                items == null ? null : new ArrayList<>(Arrays.asList(items)),
                resId);
    }

    public RecyclerViewAdapter(@NonNull Context context, @Nullable ArrayList<T> items, @LayoutRes int resId) {
        this.items = items == null ? new ArrayList<T>() : items;
        this.resId = resId;
        this.context = context;
    }

    protected abstract RecyclerViewHolder<T> getViewHolder(View itemView);

//    @Override
//    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
//        return new ViewHolder(v);
//    }


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
        holder.bindItem(item);
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

    public RecyclerViewAdapter setOnItemClickListener(OnRecyclerViewItemClickListener<T> listener) {
        this.onItemClickListener = listener;
        return this;
    }

    public RecyclerViewAdapter setOnItemLongClickListener(OnRecyclerViewLongItemClickListener<T> listener) {
        this.onItemLongClickListener = listener;
        return this;
    }
}
