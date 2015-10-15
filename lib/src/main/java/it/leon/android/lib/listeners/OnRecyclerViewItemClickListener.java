package it.leon.android.lib.listeners;

import android.view.View;

/**
 * Created with Android Studio.
 * User: SimoneBellotti
 * Date: 25/03/2015
 * Time: 17.12
 */
public interface OnRecyclerViewItemClickListener<T> {
    void onItemClick(View view, T object);
}
