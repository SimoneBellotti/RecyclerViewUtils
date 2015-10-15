package it.leon.android.lib.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import it.leon.android.lib.adapters.RecyclerViewAdapter;
import it.leon.android.lib.recyclerview.RecyclerViewHolder;

/**
 * Created with Android Studio.
 * User: SimoneBellotti
 * Date: 31/07/2015
 * Time: 18.04
 * App:  RecyclerViewUtils
 */
public class RecyclerListView extends RecyclerView {

    private View mEmptyView;
    private boolean scrollWhenInserted = false;

    public RecyclerListView(Context context) {
        super(context);
    }

    public RecyclerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }

    public void setScrollWhenInserted(boolean scrollWhenInserted) {
        this.scrollWhenInserted = scrollWhenInserted;
    }

    public void scrollToLastPosition() {
        if (getAdapter() != null && getAdapter().getItemCount() > 0) {
            getLayoutManager().scrollToPosition(getAdapter().getItemCount() - 1);
        }
    }

    public void setEmptyObserver(AdapterDataObserver emptyObserver) {
        this.emptyObserver = emptyObserver;
    }

    /**
     * Set the view to show if the adapter is empty
     */
    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    /**
     * When the current adapter is empty, the RecyclerListView can display a special view
     * called the empty view. The empty view is used to provide feedback to the user
     * that no data is available.
     *
     * @return The view to show if the adapter is empty.
     */
    public View getEmptyView() {
        return mEmptyView;
    }



    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && mEmptyView != null) {
                if (adapter.getItemCount() == 0) {
                    mEmptyView.animate().alpha(1f).setDuration(300);
                    mEmptyView.setVisibility(View.VISIBLE);
//                    RecyclerListView.this.setVisibility(View.GONE);
                } else {
                    mEmptyView.animate().alpha(0f).setDuration(300);
                    mEmptyView.setVisibility(View.GONE);
//                    RecyclerListView.this.setVisibility(View.VISIBLE);
                }

            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            onChanged();

            if (scrollWhenInserted) {
                scrollToLastPosition();
            }
        }
    };

}
