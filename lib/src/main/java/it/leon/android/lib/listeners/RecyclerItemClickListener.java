package it.leon.android.lib.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import it.leon.android.lib.listeners.OnRecyclerViewItemClickListener;

/**
 * Created with Android Studio.
 * User: SimoneBellotti
 * Date: 14/10/2015
 * Time: 15.15
 * App:  RecyclerViewUtils
 */
public class RecyclerItemClickListener<T> implements RecyclerView.OnItemTouchListener {

    GestureDetector mGestureDetector;
    private OnRecyclerViewItemClickListener<T> mListener;

    public RecyclerItemClickListener(Context context, OnRecyclerViewItemClickListener<T> listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
//            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            mListener.onItemClick(childView, (T) view.getTag());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
