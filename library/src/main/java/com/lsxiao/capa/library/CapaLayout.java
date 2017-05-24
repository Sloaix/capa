package com.lsxiao.capa.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewAnimator;

/**
 * write with Capa
 * author:lsxiao
 * date:2017-05-18 01:04
 * github:https://github.com/lsxiao
 * zhihu:https://zhihu.com/people/lsxiao
 */

public class CapaLayout extends ViewAnimator {
//    @Retention(SOURCE)
//    @IntDef({LOAD, EMPTY, ERROR, CONTENT})
//    @interface StateMode {
//
//    }

    public static final int LOAD = 0;
    public static final int EMPTY = 1;
    public static final int ERROR = 2;
    public static final int CONTENT = 3;


    private int mState = CONTENT;

    //    @LayoutRes
    private int mLoadLayoutId;
    //    @LayoutRes
    private int mEmptyLayoutId;
    //    @LayoutRes
    private int mErrorLayoutId;


    private View mLoadView;
    private View mEmptyView;
    private View mErrorView;
    private View mContentView;
    private View mInitView;
    private boolean mInitAnimation;

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
        mLoadLayoutId = a.getResourceId(R.styleable.CapaLayout_cp_load_layout, R.layout.capa_load_layout);
        mEmptyLayoutId = a.getResourceId(R.styleable.CapaLayout_cp_empty_layout, R.layout.capa_empty_layout);
        mErrorLayoutId = a.getResourceId(R.styleable.CapaLayout_cp_error_layout, R.layout.capa_error_layout);
        mInitAnimation = a.getBoolean(R.styleable.CapaLayout_cp_anim_enable, true);
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
        mLoadView = LayoutInflater.from(getContext()).inflate(mLoadLayoutId, this, false);
        mEmptyView = LayoutInflater.from(getContext()).inflate(mEmptyLayoutId, this, false);
        mErrorView = LayoutInflater.from(getContext()).inflate(mErrorLayoutId, this, false);

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
        if (!mInitAnimation) {
            return;
        }
        animFade();
    }

    public void animFade() {
        animIn(R.anim.capa_fade_in);
        animOut(R.anim.capa_fade_out);
    }

    public void animSlideInTop() {
        animIn(R.anim.capa_slide_in_top);
        animOut(R.anim.capa_fade_out);
    }

    public void animSlideInBottom() {
        animIn(R.anim.capa_slide_in_bottom);
        animOut(R.anim.capa_fade_out);
    }

    public void animNone() {
        setInAnimation(null);
        setOutAnimation(null);
    }

    public void animIn(int in) {
        setInAnimation(getContext(), in);
    }

    public void animOut(int out) {
        setOutAnimation(getContext(), out);
    }

    public int getState() {
        return mState;
    }

    public boolean isState(int state) {
        return mState == state;
    }

    private void to(int state) {
        if (mState == state) {
            return;
        }
        setDisplayedChild(getViewIndexByState(state));
        mState = state;
    }

    public void toContent() {
        to(CONTENT);
    }

    private int getViewIndexByState(int state) {
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
