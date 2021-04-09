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
    public int F;
    public boolean G;
    public int mSpanCount;
    public boolean I;
    public int mLayerType;
    public FocusBorderView mFocusBorderView;
    public Drawable mFocusDrawable;
    public boolean c;
    public float mFocusScale;
    public int mSelectedPosition;
    public View mNextFocused;
    public boolean g;
    public int q;
    public int r;
    public int s;
    public int t;
    public boolean u;
    public View mFocusedView;
    public TvRecyclerView.OnItemStateListener onItemStateListener;
    public TvRecyclerView.OverstepBorderListener mOverstepBorderListener;
    public TvRecyclerView.OnScrollStateListener mOnScrollStateListener;
    public Scroller mScroller;

    public TvRecyclerView(Context var1) {
        this(var1, (AttributeSet) null);
    }

    public TvRecyclerView(Context var1, AttributeSet var2) {
        this(var1, var2, 0);
    }

    public TvRecyclerView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
        this.scrollMode = 0;
        this.G = false;
        this.mSpanCount = 1;
        this.I = false;
        this.mLayerType = LAYER_TYPE_SOFTWARE;
        this.j();
        this.a(var2);
        this.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView var1, int var2, int var3) {
                if (TvRecyclerView.this.G) {
                    if (TvRecyclerView.IS_DEBUG) {
                        StringBuilder var4 = new StringBuilder();
                        var4.append("onScrolled: mSelectedPosition=");
                        var4.append(TvRecyclerView.this.mSelectedPosition);
                        Log.d("TvRecyclerView", var4.toString());
                    }

                    TvRecyclerView.this.G = false;
                    var2 = TvRecyclerView.this.findFirstVisibleItemPosition();
                    TvRecyclerView var5 = TvRecyclerView.this;
                    var5.mNextFocused = var5.getChildAt(var5.mSelectedPosition - var2);
                    if (TvRecyclerView.this.mNextFocused != null) {
                        if (TvRecyclerView.IS_DEBUG) {
                            Log.d("TvRecyclerView", "onScrolled: start adjust scroll distance");
                        }

                        if (TvRecyclerView.this.mAutoProcessFocus) {
                            var5 = TvRecyclerView.this;
                            var5.a(var5.mNextFocused, true);
                        } else {
                            TvRecyclerView.this.I = true;
                            TvRecyclerView.this.mNextFocused.requestFocus();
                        }
                    }
                }

            }
        });
    }

    public final int a() {
        int var1;
        int var2;
        if (this.mOrientation == HORIZONTAL) {
            var1 = this.getWidth() - this.getPaddingLeft();
            var2 = this.getPaddingRight();
        } else {
            var1 = this.getHeight() - this.getPaddingTop();
            var2 = this.getPaddingBottom();
        }

        return var1 - var2;
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

    public final int a(View var1) {
        if (var1 == null) {
            return -1;
        } else {
            LayoutParams var2 = (LayoutParams) var1.getLayoutParams();
            return var2 != null && !var2.isItemRemoved() ? var2.getViewAdapterPosition() : -1;
        }
    }

    public final void a(Context var1) {
        if (this.mFocusBorderView == null) {
            this.mFocusBorderView = new FocusBorderView(var1);
            ((Activity) var1).getWindow().addContentView(this.mFocusBorderView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.mFocusBorderView.setSelectPadding(this.q, this.r, this.s, this.t);
        }

    }

    public final void a(AttributeSet var1) {
        if (var1 != null) {
            TypedArray var4 = this.getContext().obtainStyledAttributes(var1, R.styleable.TvRecyclerView);
            this.scrollMode = var4.getInteger(R.styleable.TvRecyclerView_scrollMode, 0);
            Drawable var2 = var4.getDrawable(R.styleable.TvRecyclerView_focusDrawable);
            if (var2 != null) {
                this.setFocusDrawable(var2);
            }

            this.mFocusScale = var4.getFloat(R.styleable.TvRecyclerView_focusScale, 1.04F);
            boolean var3 = var4.getBoolean(R.styleable.TvRecyclerView_isAutoProcessFocus, true);
            this.mAutoProcessFocus = var3;
            if (!var3) {
                this.mFocusScale = 1.0F;
                this.setChildrenDrawingOrderEnabled(true);
            }

            var4.recycle();
        }

        if (this.mAutoProcessFocus) {
            this.setDescendantFocusability(131072);
        }

    }

    public final void a(View var1, boolean var2) {
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

        TvRecyclerView.XSmoothScroller var2 = this.xSmoothScroller;
        if (var2 == null) {
            this.stopScroll();
            Context var4 = this.getContext();
            byte var3;
            if (var1) {
                var3 = 1;
            } else {
                var3 = -1;
            }

            var2 = new TvRecyclerView.XSmoothScroller(var4, var3);
            this.getLayoutManager().startSmoothScroll(var2);
            if (var2.isRunning()) {
                this.xSmoothScroller = var2;
            }
        } else if (var1) {
            var2.b();
        } else {
            var2.a();
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
            TvRecyclerView.OnItemStateListener var1 = this.onItemStateListener;
            if (var1 != null) {
                var1.a(true, this.mFocusedView, this.mSelectedPosition);
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
        TvRecyclerView.OnScrollStateListener var2 = this.mOnScrollStateListener;
        if (var2 != null) {
            if (this.mOrientation == HORIZONTAL) {
                if (var1 == 22) {
                    var2.b(this.mFocusedView);
                } else if (var1 == 21) {
                    var2.a(this.mFocusedView);
                }
            } else if (var1 == 20) {
                var2.b(this.mFocusedView);
            } else if (var1 == 19) {
                var2.a(this.mFocusedView);
            }
        }

    }

    @Override
    public void dispatchDraw(Canvas var1) {
        super.dispatchDraw(var1);
        FocusBorderView var2 = this.mFocusBorderView;
        if (var2 != null && var2.c() != null) {
            if (IS_DEBUG) {
                Log.d("TvRecyclerView", "dispatchDraw: Border view invalidate.");
            }

            this.mFocusBorderView.invalidate();
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent var1) {
        if (var1.getAction() == 0) {
            int var2 = var1.getKeyCode();
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
                StringBuilder var4 = new StringBuilder();
                var4.append("dispatchKeyEvent: get next focus item error: ");
                var4.append(var3.getMessage());
                Log.e("TvRecyclerView", var4.toString());
                this.mNextFocused = null;
            }

            if (IS_DEBUG) {
                StringBuilder var9 = new StringBuilder();
                var9.append("dispatchKeyEvent: mNextFocused=");
                var9.append(this.mNextFocused);
                var9.append("=nextPos=");
                var9.append(this.getChildAdapterPosition(this.mNextFocused));
                Log.d("TvRecyclerView", var9.toString());
            }
        }

        return super.dispatchKeyEvent(var1);
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

            this.a(this.mNextFocused, true);
            return true;
        }
    }

    public float f() {
        return this.mFocusScale;
    }

    public final int f(View var1) {
        int var2 = this.scrollMode;
        if (var2 != 1) {
            return var2 != 2 ? this.b(var1) : this.g(var1);
        } else {
            return this.e(var1);
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
    public View focusSearch(View var1, int var2) {
        this.F = var2;
        return super.focusSearch(var1, var2);
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
        int var5 = this.a() + var4 - 45;
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
            var2 = this.F;
            if (var2 != -1 && (var2 == 33 || var2 == 130)) {
                return 0;
            }

            var3 = var1.getLeft() + var1.getWidth() / 2;
            var2 = this.a() / 2;
        } else {
            var2 = this.F;
            if (var2 != -1 && (var2 == 17 || var2 == 66)) {
                return 0;
            }

            var3 = var1.getTop() + var1.getHeight() / 2;
            var2 = this.a() / 2;
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

    public final void j() {
        this.mScroller = new Scroller(this.getContext());
        this.c = false;
        this.u = false;
        this.mSelectedPosition = 0;
        this.mNextFocused = null;
        this.g = false;
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
        int var3 = this.a();
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
            this.a(this.getContext());
        }

    }

    @Override
    public void onFocusChanged(boolean var1, int var2, Rect var3) {
        super.onFocusChanged(var1, var2, var3);
        if (IS_DEBUG) {
            StringBuilder var4 = new StringBuilder();
            var4.append("onFocusChanged: gainFocus==");
            var4.append(var1);
            Log.d("TvRecyclerView", var4.toString());
        }

        if (!var1) {
            this.mFocusBorderView.setVisibility(GONE);
        } else {
            this.mFocusBorderView.setVisibility(VISIBLE);
        }

        if (this.onItemStateListener != null) {
            if (this.mFocusedView == null) {
                this.mFocusedView = this.getChildAt(this.mSelectedPosition - this.findFirstVisibleItemPosition());
            }

            this.onItemStateListener.a(var1, this.mFocusedView, this.mSelectedPosition);
        }

        FocusBorderView var5 = this.mFocusBorderView;
        if (var5 != null) {
            var5.setTvRecyclerView(this);
            if (var1) {
                this.mFocusBorderView.bringToFront();
            }

            View var6 = this.mFocusedView;
            if (var6 != null) {
                if (var1) {
                    var6.setSelected(true);
                } else {
                    var6.setSelected(false);
                }

                if (var1 && !this.g) {
                    this.mFocusBorderView.startFocusAnim();
                }
            }

            if (!var1) {
                this.mFocusBorderView.b();
            }

        }
    }

    @Override
    public boolean onKeyDown(int var1, KeyEvent var2) {
        if (var1 != 66) {
            switch (var1) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (this.e(var1)) {
                        return true;
                    }

                    return super.onKeyDown(var1, var2);
                case 23:
                    break;
                default:
                    return super.onKeyDown(var1, var2);
            }
        }

        this.u = true;
        return super.onKeyDown(var1, var2);
    }

    @Override
    public boolean onKeyUp(int var1, KeyEvent var2) {
        if ((var1 == 23 || var1 == 66) && this.u) {
            if (this.getAdapter() != null && this.mFocusedView != null && this.onItemStateListener != null) {
                FocusBorderView var3 = this.mFocusBorderView;
                if (var3 != null) {
                    var3.d();
                }

                this.onItemStateListener.a(this.mFocusedView, this.mSelectedPosition);
            }

            this.u = false;
            if (this.mAutoProcessFocus) {
                return true;
            }
        }

        return super.onKeyUp(var1, var2);
    }

    @Override
    public void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
        this.g = true;
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
        this.g = false;
        if (IS_DEBUG) {
            StringBuilder var7 = new StringBuilder();
            var7.append("onLayout: selectPos=");
            var7.append(selectPos);
            var7.append("=SelectedItem=");
            var7.append(this.mFocusedView);
            Log.d("TvRecyclerView", var7.toString());
        }
    }

    @Override
    public void onRestoreInstanceState(Parcelable var1) {
        Bundle var2 = (Bundle) var1;
        super.onRestoreInstanceState(var2.getParcelable("super_data"));
        this.setItemSelected(var2.getInt("select_pos", 0));
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle var1 = new Bundle();
        var1.putParcelable("super_data", super.onSaveInstanceState());
        var1.putInt("select_pos", this.mSelectedPosition);
        return var1;
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        Log.d(TAG, "requestChildFocus:" + mSelectedPosition);
        if (this.mSelectedPosition < 0) {
            this.mSelectedPosition = this.a(child);
        }

        if (this.mAutoProcessFocus) {
            this.requestFocus();
        } else {
            int var3 = this.a(focused);
            Log.d(TAG, "focused child position:" + var3);
            if ((this.mSelectedPosition != var3 || this.I) && !this.G) {
                this.mSelectedPosition = var3;
                this.mFocusedView = focused;
                int var4 = this.f(focused);
                var3 = var4;
                if (this.I) {
                    var3 = var4;
                    if (this.scrollMode != 1) {
                        var3 = this.e(focused);
                    }
                }

                this.I = false;
                if (var3 != 0) {
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestChildFocus: scroll distance=");
                        sb.append(var3);
                        Log.d("TvRecyclerView", sb.toString());
                    }

                    this.g(var3);
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
                    this.a(var5, true);
                } else {
                    this.mNextFocused.requestFocus();
                }
            } else {
                Log.i(TAG, "setItemSelectedx2:" + mSelectedPosition + "," + position);
                this.G = true;
                this.mSelectedPosition = temp;
                this.scrollToPosition(temp);
            }

        }
    }

    @Override
    public void setLayoutManager(LayoutManager var1) {
        if (var1 instanceof GridLayoutManager) {
            GridLayoutManager var2 = (GridLayoutManager) var1;
            this.mOrientation = var2.getOrientation();
            this.mSpanCount = var2.getSpanCount();
        } else if (var1 instanceof LinearLayoutManager) {
            this.mOrientation = ((LinearLayoutManager) var1).getOrientation();
            this.mSpanCount = 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("setLayoutManager: orientation==");
        sb.append(this.mOrientation);
        Log.i("TvRecyclerView", sb.toString());
        super.setLayoutManager(var1);
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
        public int a;
        public int b = 10;

        public XSmoothScroller(Context var2, int var3) {
            super(var2);
            this.a = var3;
            var3 = TvRecyclerView.this.mSelectedPosition;
            int var4;
            if (this.a > 0) {
                var4 = var3 + TvRecyclerView.this.mSpanCount;
                int var5 = TvRecyclerView.this.getAdapter().getItemCount() - 1;
                var3 = var4;
                if (var4 > var5) {
                    var3 = var5;
                }
            } else {
                var4 = var3 - TvRecyclerView.this.mSpanCount;
                var3 = var4;
                if (var4 < 0) {
                    var3 = 0;
                }
            }

            this.setTargetPosition(var3);
        }

        public void a() {
            int var1 = this.a;
            if (var1 > -this.b) {
                this.a = var1 - 1;
            }

        }

        public void b() {
            int var1 = this.a;
            if (var1 < this.b) {
                this.a = var1 + 1;
            }

        }

        @Override
        public PointF computeScrollVectorForPosition(int var1) {
            var1 = this.a;
            if (var1 == 0) {
                return null;
            } else {
                byte var2;
                if (var1 < 0) {
                    var2 = -1;
                } else {
                    var2 = 1;
                }

                return TvRecyclerView.this.mOrientation == HORIZONTAL ? new PointF((float) var2, 0.0F) : new PointF(0.0F, (float) var2);
            }
        }

        @Override
        public void onStop() {
            this.a = 0;
            TvRecyclerView.this.xSmoothScroller = null;
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
                if (TvRecyclerView.this.mSelectedPosition != targetPos) {
                    TvRecyclerView.this.mSelectedPosition = targetPos;
                }

                if (!TvRecyclerView.this.mAutoProcessFocus) {
                    targetView.requestFocus();
                } else {
                    TvRecyclerView.this.mNextFocused = targetView;
                    TvRecyclerView.this.a(targetView, true);
                }

                super.onStop();
            }
        }

        @Override
        public void updateActionForInterimTarget(Action var1) {
            if (this.a != 0) {
                super.updateActionForInterimTarget(var1);
            }
        }
    }

    public interface OnScrollStateListener {
        void a(View var1);

        void b(View var1);
    }
}

