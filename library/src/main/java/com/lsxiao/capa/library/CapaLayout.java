package com.lsxiao.capa.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewAnimator;

/**
 * write with Capa
 * author:lsxiao
 * date:2017-05-18 01:04
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */

public class CapaLayout extends ViewAnimator {
    public static final int LOAD = 0;
    public static final int EMPTY = 1;
    public static final int ERROR = 2;
    public static final int CONTENT = 3;


    private int mState = CONTENT;

    @LayoutRes
    private int mLoadLayout;
    @LayoutRes
    private int mEmptyLayout;
    @LayoutRes
    private int mErrorLayout;

    private View mLoadView;
    private View mEmptyView;
    private View mErrorView;
    private View mContentView;


    public CapaLayout(Context context) {
        this(context, null);
    }

    public CapaLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        receiveAttributes(attrs);
    }

    private void receiveAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.CapaLayout);
        mState = a.getInt(R.styleable.CapaLayout_cp_state, LOAD);
        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        init();
    }

    private void init() {
        if (getChildCount() == 0) {
            throw new IllegalArgumentException("你必须提供一个content布局");
        }

        if (getChildCount() > 1) {
            throw new IllegalArgumentException("你只能提供一个子布局");
        }

        initViews();
        initAnimation();
    }

    private void initAnimation() {
        setInAnimation(getContext(), R.anim.capa_fade_in);
        setOutAnimation(getContext(), R.anim.capa_fade_out);
    }

    private void initViews() {
        mLoadView = getT("load");
        mEmptyView = getT("empty");
        mErrorView = getT("error");
        mContentView = getT("content");

        addView(mLoadView);
        addView(mEmptyView);
        addView(mErrorView);
        addView(mContentView);


        hideALl();
        if (mState == LOAD) {
            mLoadView.setVisibility(VISIBLE);
        } else if (mState == ERROR) {
            mEmptyView.setVisibility(VISIBLE);
        } else if (mState == EMPTY) {
            mErrorView.setVisibility(VISIBLE);
        } else if (mState == CONTENT) {
            mContentView.setVisibility(VISIBLE);
        }

    }

    private void hideALl() {
        mLoadView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mContentView.setVisibility(GONE);
    }

    private TextView getT(String title) {
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public int getState() {
        return mState;
    }

    public void to(int state) {
        if (state == mState) {
            return;
        }
        mState = state;
        setDisplayedChild(mState);
    }

    public void toContent() {
        to(CONTENT);
    }

    public void toError() {
        to(ERROR);
    }

    public void toLoad() {
        to(LOAD);
    }

    public void toEmpty() {
        to(EMPTY);
    }

}
