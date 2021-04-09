package com.home.tv.widget;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.tv.R;

//import androidx.leanback.R.styleable;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView.LayoutParams;
//import androidx.recyclerview.widget.RecyclerView.c0;    ViewHolder
//import androidx.recyclerview.widget.RecyclerView.g;     Adapter
//import androidx.recyclerview.widget.RecyclerView.o;     LayoutManager
//import androidx.recyclerview.widget.RecyclerView.s;     OnScrollListener
//import androidx.recyclerview.widget.RecyclerView.y.a;   SmoothScroller.Action

public class TvRecyclerView extends RecyclerView {
    private final static String TAG = "TvRecyclerView";
    public static boolean IS_DEBUG = true;
    public TvRecyclerView.XSmoothScroller xSmoothScroller;
    public int scrollMode;
    public boolean mAutoProcessFocus;
    public int mOrientation;
    public int mDirection;
    public boolean hasSetItemSelected;
    public int mSpanCount;
    public boolean I;
    public int mLayerType;
    public FocusBorderView mFocusBorderView;
    public Drawable mFocusDrawable;
    public boolean c;
    public float mFocusScale;
    public int mSelectedPosition;
    public View mNextFocused;
    public boolean isOnLayouting;
    public int q;
    public int r;
    public int s;
    public int t;
    public boolean keyEnterPressed;
    public View mFocusedView;
    public TvRecyclerView.OnItemStateListener onItemStateListener;
    public TvRecyclerView.OverstepBorderListener mOverstepBorderListener;
    public TvRecyclerView.OnScrollStateListener mOnScrollStateListener;
    public Scroller mScroller;

    public TvRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TvRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TvRecyclerView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.scrollMode = 0;
        this.hasSetItemSelected = false;
        this.mSpanCount = 1;
        this.I = false;
        this.mLayerType = LAYER_TYPE_SOFTWARE;
        this.init();
        this.parseAttribute(attributeSet);
        this.addOnScrollListener(new OnScrollListener() {

            public void onScrolled(RecyclerView var1, int dx, int dy) {
                if (hasSetItemSelected) {
                    if (TvRecyclerView.IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("onScrolled: mSelectedPosition=");
                        sb.append(mSelectedPosition);
                        Log.d("TvRecyclerView", sb.toString());
                    }

                    hasSetItemSelected = false;
                    int first = findFirstVisibleItemPosition();
                    mNextFocused = getChildAt(mSelectedPosition - first);
                    if (mNextFocused != null) {
                        if (TvRecyclerView.IS_DEBUG) {
                            Log.d("TvRecyclerView", "onScrolled: start adjust scroll distance");
                        }

                        if (mAutoProcessFocus) {
                            scrollDistance(mNextFocused, true);
                        } else {
                            I = true;
                            mNextFocused.requestFocus();
                        }
                    }
                }

            }
        });
    }

    public final int getViewHeight() {
        int height;
        if (this.mOrientation == HORIZONTAL) {
            height = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        } else {
            height = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        }
        Log.d(TAG, "TvRecyclerView real height:" + height);
        return height;
    }

    public final int a(int var1) {
        byte var3;
        label64:
        {
            label65:
            {
                label66:
                {
                    int var2 = this.mOrientation;
                    var3 = 0;
                    if (var2 == 0) {
                        switch (var1) {
                            case 19:
                                break label65;
                            case 20:
                                break label64;
                            case 21:
                                return var3;
                            case 22:
                                break label66;
                        }
                    } else if (var2 == 1) {
                        switch (var1) {
                            case 19:
                                return var3;
                            case 20:
                                break label66;
                            case 21:
                                break label65;
                            case 22:
                                break label64;
                        }
                    }

                    var3 = 17;
                    return var3;
                }

                var3 = 1;
                return var3;
            }

            var3 = 2;
            return var3;
        }

        var3 = 3;
        return var3;
    }

    public final int getViewAdapterPosition(View child) {
        if (child == null) {
            return NO_POSITION;
        } else {
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            return layoutParams != null && !layoutParams.isItemRemoved() ? layoutParams.getViewAdapterPosition() : NO_POSITION;
        }
    }

    public final void initFocusBorderView(Context context) {
        if (this.mFocusBorderView == null) {
            this.mFocusBorderView = new FocusBorderView(context);
            ((Activity) context).getWindow().addContentView(this.mFocusBorderView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.mFocusBorderView.setSelectPadding(this.q, this.r, this.s, this.t);
        }

    }

    public final void parseAttribute(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = this.getContext().obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView);
            this.scrollMode = typedArray.getInteger(R.styleable.TvRecyclerView_scrollMode, 0);
            Drawable drawable = typedArray.getDrawable(R.styleable.TvRecyclerView_focusDrawable);
            if (drawable != null) {
                this.setFocusDrawable(drawable);
            }

            this.mFocusScale = typedArray.getFloat(R.styleable.TvRecyclerView_focusScale, 1.04F);
            boolean autoprocess = typedArray.getBoolean(R.styleable.TvRecyclerView_isAutoProcessFocus, true);
            this.mAutoProcessFocus = autoprocess;
            if (!autoprocess) {
                this.mFocusScale = 1.0F;
                this.setChildrenDrawingOrderEnabled(true);
            }
            typedArray.recycle();
        }

        if (this.mAutoProcessFocus) {
            this.setDescendantFocusability(131072);
        }

    }

    public final void scrollDistance(View var1, boolean var2) {
        int var3 = this.f(var1);
        if (IS_DEBUG) {
            StringBuilder var4 = new StringBuilder();
            var4.append("scrollToView: scrollDistance==");
            var4.append(var3);
            Log.d("TvRecyclerView", var4.toString());
        }

        if (var3 != 0) {
            if (var2) {
                this.g(var3);
            } else {
                this.f(var3);
            }
        }

        this.k();
    }

    public final void a(boolean var1) {
        if (var1) {
            if (this.i()) {
                return;
            }
        } else if (this.h()) {
            return;
        }

        TvRecyclerView.XSmoothScroller smoothScroller = this.xSmoothScroller;
        if (smoothScroller == null) {
            this.stopScroll();
            Context var4 = this.getContext();
            byte var3;
            if (var1) {
                var3 = 1;
            } else {
                var3 = -1;
            }

            smoothScroller = new TvRecyclerView.XSmoothScroller(var4, var3);
            this.getLayoutManager().startSmoothScroll(smoothScroller);
            if (smoothScroller.isRunning()) {
                this.xSmoothScroller = smoothScroller;
            }
        } else if (var1) {
            smoothScroller.targetPositionIncrease();
        } else {
            smoothScroller.targetPositionDecrease();
        }

    }

    public final int b(View var1) {
        boolean var2 = this.l(var1);
        int var3;
        if (!this.j(var1) && var2) {
            var3 = 0;
        } else {
            var3 = this.i(var1);
        }

        return var3;
    }

    public Drawable getFocusDrawable() {
        return this.mFocusDrawable;
    }

    public final boolean b(int var1) {
        View var2 = this.mFocusedView;
        if (var2 == null) {
            return false;
        } else if (var1 == 21) {
            return this.mOverstepBorderListener.a(var2, this.getChildViewHolder(var2), 0);
        } else if (var1 == 22) {
            return this.mOverstepBorderListener.a(var2, this.getChildViewHolder(var2), 2);
        } else if (var1 == 19) {
            return this.mOverstepBorderListener.a(var2, this.getChildViewHolder(var2), 1);
        } else {
            return var1 == 20 ? this.mOverstepBorderListener.a(var2, this.getChildViewHolder(var2), 3) : false;
        }
    }

    public int findFirstVisibleItemPosition() {
        LayoutManager var1 = this.getLayoutManager();
        int var2;
        if (var1 != null) {
            if (var1 instanceof LinearLayoutManager) {
                var2 = ((LinearLayoutManager) var1).findFirstVisibleItemPosition();
                return var2;
            }
        }

        var2 = -1;
        return var2;
    }

    public final int c(View var1) {
        LayoutParams var2 = (LayoutParams) var1.getLayoutParams();
        int var3;
        int var4;
        if (this.mOrientation == VERTICAL) {
            var3 = this.getLayoutManager().getDecoratedBottom(var1);
            var4 = var2.bottomMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedRight(var1);
            var4 = var2.rightMargin;
        }

        return var3 + var4;
    }

    public final boolean c(int var1) {
        if (!this.mAutoProcessFocus) {
            return false;
        } else {
            int var2 = this.a(var1);
            if (var2 == 1) {
                if (!this.i()) {
                    this.a(true);
                    return true;
                } else {
                    return this.b(var1);
                }
            } else if (var2 == 0) {
                if (!this.h()) {
                    this.a(false);
                    return true;
                } else {
                    return this.b(var1);
                }
            } else if (var2 == 2) {
                return this.b(var1);
            } else {
                return var2 == 3 ? this.b(var1) : false;
            }
        }
    }

    @Override
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.c) {
                this.mScroller.getCurrX();
            }
            this.postInvalidate();
        } else if (this.c) {
            this.c = false;
            this.m(this.mNextFocused);
            this.setLayerType(this.mLayerType, (Paint) null);
            this.postInvalidate();
            if (onItemStateListener != null) {
                onItemStateListener.a(true, this.mFocusedView, this.mSelectedPosition);
            }
        }

    }

    public final int d() {
        return this.mOrientation == HORIZONTAL ? this.getPaddingLeft() : this.getPaddingTop();
    }

    public final int d(View var1) {
        LayoutParams var2 = (LayoutParams) var1.getLayoutParams();
        int var3;
        int var4;
        if (this.mOrientation == VERTICAL) {
            var3 = this.getLayoutManager().getDecoratedTop(var1);
            var4 = var2.topMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedLeft(var1);
            var4 = var2.leftMargin;
        }
        return var3 - var4;
    }

    public final void d(int var1) {
        if (mOnScrollStateListener != null) {
            if (this.mOrientation == HORIZONTAL) {
                if (var1 == 22) {
                    mOnScrollStateListener.b(this.mFocusedView);
                } else if (var1 == 21) {
                    mOnScrollStateListener.a(this.mFocusedView);
                }
            } else if (var1 == 20) {
                mOnScrollStateListener.b(this.mFocusedView);
            } else if (var1 == 19) {
                mOnScrollStateListener.a(this.mFocusedView);
            }
        }

    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mFocusBorderView != null && mFocusBorderView.getTvRecyclerView() != null) {
            if (IS_DEBUG) {
                Log.d("TvRecyclerView", "dispatchDraw: Border view invalidate.");
            }

            this.mFocusBorderView.invalidate();
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            int var2 = event.getKeyCode();
            if (this.mFocusedView == null) {
                this.mFocusedView = this.getChildAt(this.mSelectedPosition);
            }

            label49:
            {
                Exception var10000;
                boolean var10001;
                if (var2 == 21) {
                    try {
                        this.mNextFocused = FocusFinder.getInstance().findNextFocus(this, this.mFocusedView, 17);
                        break label49;
                    } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                    }
                } else if (var2 == 22) {
                    try {
                        this.mNextFocused = FocusFinder.getInstance().findNextFocus(this, this.mFocusedView, 66);
                        break label49;
                    } catch (Exception var6) {
                        var10000 = var6;
                        var10001 = false;
                    }
                } else if (var2 == 19) {
                    try {
                        this.mNextFocused = FocusFinder.getInstance().findNextFocus(this, this.mFocusedView, 33);
                        break label49;
                    } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                    }
                } else {
                    if (var2 != 20) {
                        break label49;
                    }

                    try {
                        this.mNextFocused = FocusFinder.getInstance().findNextFocus(this, this.mFocusedView, 130);
                        break label49;
                    } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                    }
                }

                Exception var3 = var10000;
                StringBuilder sb = new StringBuilder();
                sb.append("dispatchKeyEvent: get next focus item error: ");
                sb.append(var3.getMessage());
                Log.e("TvRecyclerView", sb.toString());
                this.mNextFocused = null;
            }

            if (IS_DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("dispatchKeyEvent: mNextFocused=");
                sb.append(this.mNextFocused);
                sb.append("=nextPos=");
                sb.append(this.getChildAdapterPosition(this.mNextFocused));
                Log.d("TvRecyclerView", sb.toString());
            }
        }

        return super.dispatchKeyEvent(event);
    }

    public int e() {
        return this.mSelectedPosition;
    }

    public final int e(View var1) {
        int var2;
        if (this.k(var1)) {
            var2 = this.i(var1);
        } else {
            var2 = 0;
        }

        return var2;
    }

    public final boolean e(int var1) {
        View var2 = this.mNextFocused;
        if (var2 == null) {
            if (this.c(var1)) {
                return true;
            } else {
                if (this.mAutoProcessFocus) {
                    this.d(var1);
                    this.c = false;
                }

                if (IS_DEBUG) {
                    Log.d("TvRecyclerView", "processMoves: error");
                }

                return false;
            }
        } else {
            if (this.c) {
                this.m(var2);
            }

            this.scrollDistance(this.mNextFocused, true);
            return true;
        }
    }

    public float f() {
        return this.mFocusScale;
    }

    public final int f(View focused) {
        int var2 = this.scrollMode;
        if (var2 != 1) {
            return var2 != 2 ? this.b(focused) : this.g(focused);
        } else {
            return this.e(focused);
        }
    }

    public final void f(int var1) {
        if (this.mOrientation == HORIZONTAL) {
            this.scrollBy(var1, 0);
        } else {
            this.scrollBy(0, var1);
        }

    }

    @Override
    public View focusSearch(View focused, int direction) {
        this.mDirection = direction;
        return super.focusSearch(focused, direction);
    }

    public final int g(View var1) {
        boolean var2 = this.mAutoProcessFocus;
        int var3 = 0;
        if (!var2) {
            return 0;
        } else {
            var2 = this.l(var1);
            if (this.j(var1) || !var2) {
                var3 = this.h(var1);
            }

            return var3;
        }
    }

    public View g() {
        return this.mFocusedView;
    }

    public final void g(int var1) {
        if (this.mOrientation == HORIZONTAL) {
            this.smoothScrollBy(var1, 0);
        } else {
            this.smoothScrollBy(0, var1);
        }

    }

    @Override
    public int getChildDrawingOrder(int var1, int var2) {
        int var3 = this.indexOfChild(this.mFocusedView);
        if (var3 < 0) {
            return var2;
        } else if (var2 < var3) {
            return var2;
        } else {
            int var4 = var3;
            if (var2 < var1 - 1) {
                var4 = var3 + var1 - 1 - var2;
            }

            return var4;
        }
    }

    public final int h(View var1) {
        int var2 = this.d(var1);
        int var3 = this.c(var1);
        int var4 = this.d();
        int var5 = this.getViewHeight() + var4 - 45;
        View var6 = null;
        if (var2 >= var4) {
            if (var3 > var5) {
                Object var7 = null;
                var6 = var1;
                var1 = (View) var7;
            } else {
                var1 = null;
            }
        }

        if (var1 != null) {
            var5 = this.d(var1) - var4 - 45;
        } else if (var6 != null) {
            var5 = this.c(var6) - var5;
        } else {
            var5 = 0;
        }

        return var5;
    }

    public final boolean h() {
        int var1 = this.getLayoutManager().getItemCount();
        boolean var2 = false;
        if (var1 == 0 || this.findViewHolderForAdapterPosition(0) != null) {
            var2 = true;
        }

        return var2;
    }

    public final int i(View var1) {
        int var2;
        int var3;
        if (this.mOrientation == HORIZONTAL) {
            var2 = this.mDirection;
            if (var2 != -1 && (var2 == 33 || var2 == 130)) {
                return 0;
            }

            var3 = var1.getLeft() + var1.getWidth() / 2;
            var2 = this.getViewHeight() / 2;
        } else {
            var2 = this.mDirection;
            if (var2 != -1 && (var2 == 17 || var2 == 66)) {
                return 0;
            }

            var3 = var1.getTop() + var1.getHeight() / 2;
            var2 = this.getViewHeight() / 2;
        }

        return var3 - var2;
    }

    public final boolean i() {
        int var1 = this.getLayoutManager().getItemCount();
        boolean var2 = true;
        boolean var3 = var2;
        if (var1 != 0) {
            if (this.findViewHolderForAdapterPosition(var1 - 1) != null) {
                var3 = var2;
            } else {
                var3 = false;
            }
        }

        return var3;
    }

    @Override
    public boolean isInTouchMode() {
        boolean var1 = super.isInTouchMode();
        boolean var2 = var1;
        if (VERSION.SDK_INT == 19) {
            if (this.hasFocus() && !var1) {
                var2 = false;
            } else {
                var2 = true;
            }
        }

        return var2;
    }

    public final void init() {
        this.mScroller = new Scroller(this.getContext());
        this.c = false;
        this.keyEnterPressed = false;
        this.mSelectedPosition = 0;
        this.mNextFocused = null;
        this.isOnLayouting = false;
        this.mFocusScale = 1.04F;
        this.mAutoProcessFocus = true;
        this.q = 22;
        this.r = 22;
        this.s = 22;
        this.t = 22;
        this.mOrientation = HORIZONTAL;
    }

    public final boolean j(View var1) {
        boolean var2 = false;
        boolean var3 = false;
        boolean var4 = var2;
        if (var1 != null) {
            Rect var5 = new Rect();
            boolean var6 = var1.getLocalVisibleRect(var5);
            if (this.mOrientation == HORIZONTAL) {
                var4 = var3;
                if (var6) {
                    var4 = var3;
                    if (var5.width() < var1.getWidth()) {
                        var4 = true;
                    }
                }

                return var4;
            }

            var4 = var2;
            if (var6) {
                var4 = var2;
                if (var5.height() < var1.getHeight()) {
                    var4 = true;
                }
            }
        }

        return var4;
    }

    public final void k() {
        this.mScroller.abortAnimation();
        FocusBorderView var1 = this.mFocusBorderView;
        if (var1 != null) {
            var1.stopAnimation();
            this.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            this.c = true;
            TvRecyclerView.OnItemStateListener var2 = this.onItemStateListener;
            if (var2 != null) {
                var2.a(false, this.mFocusedView, this.mSelectedPosition);
            }

            this.mScroller.startScroll(0, 0, 100, 100, 200);
            this.invalidate();
        } else {
            Log.d("TvRecyclerView", "startFocusMoveAnim: mFocusBorderView is null");
        }

    }

    public final boolean k(View var1) {
        Rect var2 = new Rect();
        var1.getGlobalVisibleRect(var2);
        int var3 = this.getViewHeight();
        int var4 = this.mOrientation;
        boolean var5 = false;
        boolean var6 = false;
        if (var4 == HORIZONTAL) {
            var4 = var2.right;
            var3 /= 2;
            if (var4 > var3 || var2.left < var3) {
                var6 = true;
            }

            return var6;
        } else {
            var4 = var2.top;
            var3 /= 2;
            if (var4 >= var3) {
                var6 = var5;
                if (var2.bottom <= var3) {
                    return var6;
                }
            }

            var6 = true;
            return var6;
        }
    }

    public final boolean l(View var1) {
        return var1 != null ? var1.getLocalVisibleRect(new Rect()) : false;
    }

    public final void m(View var1) {
        if (var1 != null) {
            this.mFocusedView = var1;
            this.mSelectedPosition = this.getChildAdapterPosition(var1);
        }

    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.mAutoProcessFocus) {
            if (IS_DEBUG) {
                Log.d("TvRecyclerView", "onFinishInflate: add fly border view");
            }

            this.mLayerType = this.getLayerType();
            this.initFocusBorderView(this.getContext());
        }

    }

    @Override
    public void onFocusChanged(boolean gainFocus, int var2, Rect var3) {
        super.onFocusChanged(gainFocus, var2, var3);
        if (IS_DEBUG) {
            StringBuilder var4 = new StringBuilder();
            var4.append("onFocusChanged: gainFocus==");
            var4.append(gainFocus);
            Log.d("TvRecyclerView", var4.toString());
        }

        if (!gainFocus) {
            this.mFocusBorderView.setVisibility(GONE);
        } else {
            this.mFocusBorderView.setVisibility(VISIBLE);
        }

        if (this.onItemStateListener != null) {
            if (this.mFocusedView == null) {
                this.mFocusedView = this.getChildAt(this.mSelectedPosition - this.findFirstVisibleItemPosition());
            }

            this.onItemStateListener.a(gainFocus, this.mFocusedView, this.mSelectedPosition);
        }

        FocusBorderView var5 = this.mFocusBorderView;
        if (var5 != null) {
            var5.setTvRecyclerView(this);
            if (gainFocus) {
                this.mFocusBorderView.bringToFront();
            }

            View var6 = this.mFocusedView;
            if (var6 != null) {
                if (gainFocus) {
                    var6.setSelected(true);
                } else {
                    var6.setSelected(false);
                }

                if (gainFocus && !this.isOnLayouting) {
                    this.mFocusBorderView.startFocusAnim();
                }
            }

            if (!gainFocus) {
                this.mFocusBorderView.resetFocusAnim();
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_ENTER) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (this.e(keyCode)) {
                        return true;
                    }
                    return super.onKeyDown(keyCode, event);
                case 23:
                    break;
                default:
                    return super.onKeyDown(keyCode, event);
            }
        }

        this.keyEnterPressed = true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) && this.keyEnterPressed) {
            if (this.getAdapter() != null && this.mFocusedView != null && this.onItemStateListener != null) {
                FocusBorderView var3 = this.mFocusBorderView;
                if (var3 != null) {
                    var3.startClickAnim();
                }

                this.onItemStateListener.a(this.mFocusedView, this.mSelectedPosition);
            }

            this.keyEnterPressed = false;
            if (this.mAutoProcessFocus) {
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
        this.isOnLayouting = true;
        super.onLayout(var1, var2, var3, var4, var5);
        Adapter adapter = this.getAdapter();
        if (adapter != null && this.mSelectedPosition >= adapter.getItemCount()) {
            this.mSelectedPosition = adapter.getItemCount() - 1;
        }

        var3 = this.mSelectedPosition - this.findFirstVisibleItemPosition();
        int selectPos = var3;
        if (var3 < 0) {
            selectPos = 0;
        }

        this.mFocusedView = this.getChildAt(selectPos);
        this.isOnLayouting = false;
        if (IS_DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("onLayout: selectPos=");
            sb.append(selectPos);
            sb.append("=SelectedItem=");
            sb.append(this.mFocusedView);
            Log.d("TvRecyclerView", sb.toString());
        }
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("super_data"));
        this.setItemSelected(bundle.getInt("select_pos", 0));
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super_data", super.onSaveInstanceState());
        bundle.putInt("select_pos", this.mSelectedPosition);
        return bundle;
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        Log.d(TAG, "requestChildFocus:" + mSelectedPosition);
        if (this.mSelectedPosition < 0) {
            this.mSelectedPosition = getViewAdapterPosition(child);
        }

        if (this.mAutoProcessFocus) {
            this.requestFocus();
        } else {
            int focusPosition = getViewAdapterPosition(focused);
            Log.d(TAG, "focused child position:" + focusPosition);
            if ((this.mSelectedPosition != focusPosition || this.I) && !this.hasSetItemSelected) {
                this.mSelectedPosition = focusPosition;
                this.mFocusedView = focused;
                int var4 = this.f(focused);
                focusPosition = var4;
                if (this.I) {
                    focusPosition = var4;
                    if (this.scrollMode != 1) {
                        focusPosition = this.e(focused);
                    }
                }

                this.I = false;
                if (focusPosition != 0) {
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestChildFocus: scroll distance=");
                        sb.append(focusPosition);
                        Log.d("TvRecyclerView", sb.toString());
                    }

                    this.g(focusPosition);
                }
            }
        }

        if (IS_DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("requestChildFocus: SelectPos=");
            sb.append(this.mSelectedPosition);
            Log.d("TvRecyclerView", sb.toString());
        }
    }

    public void setFocusDrawable(Drawable d) {
        this.mFocusDrawable = d;
    }

    public void setIsAutoProcessFocus(boolean processFocus) {
        this.mAutoProcessFocus = processFocus;
        if (!processFocus) {
            this.mFocusScale = 1.0F;
            this.setChildrenDrawingOrderEnabled(true);
        } else if (this.mFocusScale == 1.0F) {
            this.mFocusScale = 1.04F;
        }

    }

    public void setItemSelected(int position) {
        if (this.mSelectedPosition != position) {
            int temp = position;
            if (position >= this.getAdapter().getItemCount()) {
                temp = this.getAdapter().getItemCount() - 1;
            }

            int first = this.getChildAdapterPosition(this.getChildAt(0));
//            View child = this.getChildAt(this.getChildCount() - 1);
//            Log.d(TAG, "child is null:" + (child == null));
//            int x = getChildAdapterPosition(child);
//            Log.d(TAG, "x:" + x);
            int last = this.getChildAdapterPosition(this.getChildAt(this.getChildCount() - 1));
            if (IS_DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("setItemSelected: first=");
                sb.append(first);
                sb.append("=last=");
                sb.append(last);
                sb.append("=pos=");
                sb.append(temp);
                Log.d("TvRecyclerView", sb.toString());
            }

            if (temp >= first && temp <= last) {
                View var5 = this.getChildAt(temp - first);
                this.mNextFocused = var5;
                if (this.mAutoProcessFocus && !this.c) {
                    Log.d(TAG, "setItemSelectedx1:" + mSelectedPosition + "," + position);
                    this.scrollDistance(var5, true);
                } else {
                    this.mNextFocused.requestFocus();
                }
            } else {
                Log.i(TAG, "setItemSelectedx2:" + mSelectedPosition + "," + position);
                this.hasSetItemSelected = true;
                this.mSelectedPosition = temp;
                this.scrollToPosition(temp);
            }

        }
    }

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager var2 = (GridLayoutManager) layoutManager;
            this.mOrientation = var2.getOrientation();
            this.mSpanCount = var2.getSpanCount();
        } else if (layoutManager instanceof LinearLayoutManager) {
            this.mOrientation = ((LinearLayoutManager) layoutManager).getOrientation();
            this.mSpanCount = 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("setLayoutManager: orientation==");
        sb.append(this.mOrientation);
        Log.i("TvRecyclerView", sb.toString());
        super.setLayoutManager(layoutManager);
    }

    public void setOnItemStateListener(TvRecyclerView.OnItemStateListener onItemStateListener) {
        this.onItemStateListener = onItemStateListener;
    }

    public void setOnScrollStateListener(TvRecyclerView.OnScrollStateListener onScrollStateListener) {
        this.mOnScrollStateListener = onScrollStateListener;
    }

    public void setOverstepBorderListener(TvRecyclerView.OverstepBorderListener overstepBorderListener) {
        this.mOverstepBorderListener = overstepBorderListener;
    }

    public void setScrollMode(int var1) {
        this.scrollMode = var1;
    }

    public void setSelectPadding(int var1, int var2, int var3, int var4) {
        this.q = var1;
        this.r = var2;
        this.s = var3;
        this.t = var4;
        FocusBorderView var5 = this.mFocusBorderView;
        if (var5 != null) {
            var5.setSelectPadding(var1, var2, var3, var4);
        }

    }

    public void setSelectedScale(float scale) {
        if (scale >= 1.0F) {
            this.mFocusScale = scale;
        }
    }

    public interface OnItemStateListener {
        void a(View var1, int var2);

        void a(boolean var1, View var2, int var3);
    }

    public interface OverstepBorderListener {
        boolean a(View var1, ViewHolder var2, int var3);
    }

    private final class XSmoothScroller extends TvSmoothScroller {
        public int mTargetPosition;
        public int defaultPosition = 10;

        public XSmoothScroller(Context var2, int pos) {
            super(var2);
            this.mTargetPosition = pos;
            int var3 = mSelectedPosition;
            int var4;
            if (this.mTargetPosition > 0) {
                var4 = var3 + mSpanCount;
                int itemCount = getAdapter().getItemCount() - 1;
                var3 = var4;
                if (var4 > itemCount) {
                    var3 = itemCount;
                }
            } else {
                var4 = var3 - mSpanCount;
                var3 = var4;
                if (var4 < 0) {
                    var3 = 0;
                }
            }
            Log.d(TAG, "setTargetPosition:" + var3);
            setTargetPosition(var3);
        }

        public void targetPositionDecrease() {
            int var1 = this.mTargetPosition;
            if (var1 > -this.defaultPosition) {
                this.mTargetPosition = var1 - 1;
            }

        }

        public void targetPositionIncrease() {
            int var1 = this.mTargetPosition;
            if (var1 < this.defaultPosition) {
                this.mTargetPosition = var1 + 1;
            }

        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            targetPosition = this.mTargetPosition;
            if (targetPosition == 0) {
                return null;
            } else {
                byte var2;
                if (targetPosition < 0) {
                    var2 = -1;
                } else {
                    var2 = 1;
                }
                Log.d(TAG, "computeScrollVectorForPosition:" + var2);
                return mOrientation == HORIZONTAL ? new PointF((float) var2, 0.0F) : new PointF(0.0F, (float) var2);
            }
        }

        @Override
        public void onStop() {
            this.mTargetPosition = 0;
            xSmoothScroller = null;
            int targetPos = this.getTargetPosition();
            View targetView = this.findViewByPosition(targetPos);

            StringBuilder sb = new StringBuilder();
            sb.append("PendingMoveSmoothScroller onStop: targetPos=");
            sb.append(targetPos);
            sb.append("==targetView=");
            sb.append(targetView);
            Log.i("TvRecyclerView", sb.toString());

            if (targetView == null) {
                super.onStop();
            } else {
                if (mSelectedPosition != targetPos) {
                    mSelectedPosition = targetPos;
                }

                if (!mAutoProcessFocus) {
                    targetView.requestFocus();
                } else {
                    mNextFocused = targetView;
                    scrollDistance(targetView, true);
                }

                super.onStop();
            }
        }

        @Override
        public void updateActionForInterimTarget(Action var1) {
            if (this.mTargetPosition != 0) {
                super.updateActionForInterimTarget(var1);
            }
        }
    }

    public interface OnScrollStateListener {
        void a(View var1);

        void b(View var1);
    }
}

