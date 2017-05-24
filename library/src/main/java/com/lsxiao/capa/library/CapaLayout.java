package com.lsxiao.capa.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewAnimator;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * write with Capa
 * author:lsxiao
 * date:2017-05-18 01:04
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */

public class CapaLayout extends ViewAnimator {
    @Retention(SOURCE)
    @IntDef({LOAD, EMPTY, ERROR, CONTENT})
    @interface StateMode {

    }

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

    private View mInitView;

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
        mLoadLayout = a.getInt(R.styleable.CapaLayout_cp_load_layout, R.layout.capa_load_layout);
        mEmptyLayout = a.getInt(R.styleable.CapaLayout_cp_empty_layout, R.layout.capa_empty_layout);
        mErrorLayout = a.getInt(R.styleable.CapaLayout_cp_error_layout, R.layout.capa_error_layout);
        a.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }


    private void init() {
        initViews();
        initAnimation();
    }


    private void initViews() {
        check();

        mContentView = getChildAt(0);
        mLoadView = LayoutInflater.from(getContext()).inflate(mLoadLayout, this, false);
        mEmptyView = LayoutInflater.from(getContext()).inflate(mEmptyLayout, this, false);
        mErrorView = LayoutInflater.from(getContext()).inflate(mErrorLayout, this, false);

        addView(mEmptyView, getChildCount());
        addView(mErrorView, getChildCount());
        addView(mLoadView, getChildCount());

        hideAllView();
        findInitView().setVisibility(VISIBLE);
    }


    private void check() {
        if (getChildCount() != 1) {
            throw new IllegalArgumentException("Child node which in CapaLayout must exist, and there can be only one.(CapaLayout的子节点必须存在，且只能有一个。)");
        }
    }

    private View findInitView() {
        switch (mState) {
            case CONTENT:
                mInitView = mContentView;
                break;
            case LOAD:
                mInitView = mLoadView;
                break;
            case EMPTY:
                mInitView = mEmptyView;
                break;
            case ERROR:
                mInitView = mErrorView;
                break;
        }
        return mInitView;
    }

    private void hideAllView() {
        mContentView.setVisibility(GONE);
        mLoadView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
    }

    private void initAnimation() {
        animIn(R.anim.capa_fade_in);
        animOut(R.anim.capa_fade_out);
    }

    public void animFade() {
        initAnimation();
    }

    public void animeSlideInTop() {
        animIn(R.anim.capa_slide_in_top);
        animOut(R.anim.capa_fade_out);
    }

    public void animeSlideInBottom() {
        animIn(R.anim.capa_slide_in_top);
        animOut(R.anim.capa_fade_out);
    }

    public void animNone() {
        setInAnimation(null);
        setOutAnimation(null);
    }

    public void animIn(@AnimRes int in) {
        setInAnimation(getContext(), in);
    }

    public void animOut(@AnimRes int out) {
        setOutAnimation(getContext(), out);
    }

    public int getState() {
        return mState;
    }

    public boolean isState(@StateMode int state) {
        return mState == state;
    }

    public void to(@StateMode int state) {
        if (mState == state) {
            return;
        }
        setDisplayedChild(getViewIndexByState(state));
        mState = state;
    }

    public void toContent() {
        to(CONTENT);
    }

    private int getViewIndexByState(@StateMode int state) {
        switch (state) {
            case LOAD:
                return indexOfChild(mLoadView);
            case EMPTY:
                return indexOfChild(mEmptyView);
            case ERROR:
                return indexOfChild(mErrorView);
            case CONTENT:
            default:
                return indexOfChild(mContentView);
        }
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


    public View getLoadView() {
        return mLoadView;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public View getErrorView() {
        return mErrorView;
    }

    public View getContentView() {
        return mContentView;
    }

}
