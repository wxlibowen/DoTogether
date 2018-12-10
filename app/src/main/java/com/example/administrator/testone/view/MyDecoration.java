package com.example.administrator.testone.view;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * 创建时间:2018/11/28
 * 作者:LiBW
 * 描述:RecycleView修饰
 */
public class MyDecoration extends RecyclerView.ItemDecoration {
    private int mPageMargin = 10;
    private int mLeftPageVisibleWidth = 125;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        int leftMargin;
        if (position == 0) {
            leftMargin = dp2px(mLeftPageVisibleWidth);
        } else {
            leftMargin = dp2px(mPageMargin);
        }
        int rightMargin;
        if (position == (itemCount - 1)) {
            rightMargin = dp2px(mLeftPageVisibleWidth);
        } else {
            rightMargin = dp2px(mPageMargin);
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        params.setMargins(leftMargin, 30, rightMargin, 60);
        view.setLayoutParams(params);


    }

    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
