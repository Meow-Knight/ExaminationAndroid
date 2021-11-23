package com.example.tcpexamination.common;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollHorizontalEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public void setScrollHorizontalEnabled(boolean flag) {
        this.isScrollHorizontalEnabled = flag;
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollHorizontalEnabled;
    }
}
