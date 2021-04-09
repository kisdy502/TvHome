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
import android.widget.Scroller;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.Action;

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
    public static boolean K;
    public TvRecyclerView.d A;
    public int B;
    public boolean C;
    public int E;
    public int F;
    public boolean G;
    public int H;
    public boolean I;
    public int J;
    public FocusBorderView a;
    public Drawable b;
    public boolean c;
    public float d;
    public int e;
    public View f;
    public boolean g;
    public int q;
    public int r;
    public int s;
    public int t;
    public boolean u;
    public View v;
    public TvRecyclerView.b w;
    public TvRecyclerView.c x;
    public TvRecyclerView.e y;
    public Scroller z;

    public TvRecyclerView(Context var1) {
        this(var1, (AttributeSet) null);
    }

    public TvRecyclerView(Context var1, AttributeSet var2) {
        this(var1, var2, 0);
    }

    public TvRecyclerView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
        this.B = 0;
        this.G = false;
        this.H = 1;
        this.I = false;
        this.J = 1;
        this.j();
        this.a(var2);
        this.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView var1, int var2, int var3) {
                if (TvRecyclerView.this.G) {
                    if (TvRecyclerView.K) {
                        StringBuilder var4 = new StringBuilder();
                        var4.append("onScrolled: mSelectedPosition=");
                        var4.append(TvRecyclerView.this.e);
                        Log.d("TvRecyclerView", var4.toString());
                    }

                    TvRecyclerView.this.G = false;
                    var2 = TvRecyclerView.this.c();
                    TvRecyclerView var5 = TvRecyclerView.this;
                    var5.f = var5.getChildAt(var5.e - var2);
                    if (TvRecyclerView.this.f != null) {
                        if (TvRecyclerView.K) {
                            Log.d("TvRecyclerView", "onScrolled: start adjust scroll distance");
                        }

                        if (TvRecyclerView.this.C) {
                            var5 = TvRecyclerView.this;
                            var5.a(var5.f, true);
                        } else {
                            TvRecyclerView.this.I = true;
                            TvRecyclerView.this.f.requestFocus();
                        }
                    }
                }

            }
        });
    }

    public final int a() {
        int var1;
        int var2;
        if (this.E == 0) {
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
                    int var2 = this.E;
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
        if (this.a == null) {
            this.a = new FocusBorderView(var1);
            ((Activity) var1).getWindow().addContentView(this.a, new LayoutParams(-1, -1));
            this.a.setSelectPadding(this.q, this.r, this.s, this.t);
        }

    }

    public final void a(AttributeSet var1) {
        if (var1 != null) {
            TypedArray var4 = this.getContext().obtainStyledAttributes(var1, R.styleable.TvRecyclerView);
            this.B = var4.getInteger(R.styleable.TvRecyclerView_scrollMode, 0);
            Drawable var2 = var4.getDrawable(R.styleable.TvRecyclerView_focusDrawable);
            if (var2 != null) {
                this.setFocusDrawable(var2);
            }

            this.d = var4.getFloat(R.styleable.TvRecyclerView_focusScale, 1.04F);
            boolean var3 = var4.getBoolean(R.styleable.TvRecyclerView_isAutoProcessFocus, true);
            this.C = var3;
            if (!var3) {
                this.d = 1.0F;
                this.setChildrenDrawingOrderEnabled(true);
            }

            var4.recycle();
        }

        if (this.C) {
            this.setDescendantFocusability(131072);
        }

    }

    public final void a(View var1, boolean var2) {
        int var3 = this.f(var1);
        if (K) {
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

        TvRecyclerView.d var2 = this.A;
        if (var2 == null) {
            this.stopScroll();
            Context var4 = this.getContext();
            byte var3;
            if (var1) {
                var3 = 1;
            } else {
                var3 = -1;
            }

            var2 = new TvRecyclerView.d(var4, var3);
            this.getLayoutManager().startSmoothScroll(var2);
            if (var2.isRunning()) {
                this.A = var2;
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

    public Drawable b() {
        return this.b;
    }

    public final boolean b(int var1) {
        View var2 = this.v;
        if (var2 == null) {
            return false;
        } else if (var1 == 21) {
            return this.x.a(var2, this.getChildViewHolder(var2), 0);
        } else if (var1 == 22) {
            return this.x.a(var2, this.getChildViewHolder(var2), 2);
        } else if (var1 == 19) {
            return this.x.a(var2, this.getChildViewHolder(var2), 1);
        } else {
            return var1 == 20 ? this.x.a(var2, this.getChildViewHolder(var2), 3) : false;
        }
    }

    public int c() {
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
        if (this.E == 1) {
            var3 = this.getLayoutManager().getDecoratedBottom(var1);
            var4 = var2.bottomMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedRight(var1);
            var4 = var2.rightMargin;
        }

        return var3 + var4;
    }

    public final boolean c(int var1) {
        if (!this.C) {
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

    public void computeScroll() {
        if (this.z.computeScrollOffset()) {
            if (this.c) {
                this.z.getCurrX();
            }

            this.postInvalidate();
        } else if (this.c) {
            this.c = false;
            this.m(this.f);
            this.setLayerType(this.J, (Paint) null);
            this.postInvalidate();
            TvRecyclerView.b var1 = this.w;
            if (var1 != null) {
                var1.a(true, this.v, this.e);
            }
        }

    }

    public final int d() {
        return this.E == 0 ? this.getPaddingLeft() : this.getPaddingTop();
    }

    public final int d(View var1) {
        LayoutParams var2 = (LayoutParams) var1.getLayoutParams();
        int var3;
        int var4;
        if (this.E == 1) {
            var3 = this.getLayoutManager().getDecoratedTop(var1);
            var4 = var2.topMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedLeft(var1);
            var4 = var2.leftMargin;
        }

        return var3 - var4;
    }

    public final void d(int var1) {
        TvRecyclerView.e var2 = this.y;
        if (var2 != null) {
            if (this.E == 0) {
                if (var1 == 22) {
                    var2.b(this.v);
                } else if (var1 == 21) {
                    var2.a(this.v);
                }
            } else if (var1 == 20) {
                var2.b(this.v);
            } else if (var1 == 19) {
                var2.a(this.v);
            }
        }

    }

    public void dispatchDraw(Canvas var1) {
        super.dispatchDraw(var1);
        FocusBorderView var2 = this.a;
        if (var2 != null && var2.c() != null) {
            if (K) {
                Log.d("TvRecyclerView", "dispatchDraw: Border view invalidate.");
            }

            this.a.invalidate();
        }

    }

    public boolean dispatchKeyEvent(KeyEvent var1) {
        if (var1.getAction() == 0) {
            int var2 = var1.getKeyCode();
            if (this.v == null) {
                this.v = this.getChildAt(this.e);
            }

            label49:
            {
                Exception var10000;
                boolean var10001;
                if (var2 == 21) {
                    try {
                        this.f = FocusFinder.getInstance().findNextFocus(this, this.v, 17);
                        break label49;
                    } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                    }
                } else if (var2 == 22) {
                    try {
                        this.f = FocusFinder.getInstance().findNextFocus(this, this.v, 66);
                        break label49;
                    } catch (Exception var6) {
                        var10000 = var6;
                        var10001 = false;
                    }
                } else if (var2 == 19) {
                    try {
                        this.f = FocusFinder.getInstance().findNextFocus(this, this.v, 33);
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
                        this.f = FocusFinder.getInstance().findNextFocus(this, this.v, 130);
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
                this.f = null;
            }

            if (K) {
                StringBuilder var9 = new StringBuilder();
                var9.append("dispatchKeyEvent: mNextFocused=");
                var9.append(this.f);
                var9.append("=nextPos=");
                var9.append(this.getChildAdapterPosition(this.f));
                Log.d("TvRecyclerView", var9.toString());
            }
        }

        return super.dispatchKeyEvent(var1);
    }

    public int e() {
        return this.e;
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
        View var2 = this.f;
        if (var2 == null) {
            if (this.c(var1)) {
                return true;
            } else {
                if (this.C) {
                    this.d(var1);
                    this.c = false;
                }

                if (K) {
                    Log.d("TvRecyclerView", "processMoves: error");
                }

                return false;
            }
        } else {
            if (this.c) {
                this.m(var2);
            }

            this.a(this.f, true);
            return true;
        }
    }

    public float f() {
        return this.d;
    }

    public final int f(View var1) {
        int var2 = this.B;
        if (var2 != 1) {
            return var2 != 2 ? this.b(var1) : this.g(var1);
        } else {
            return this.e(var1);
        }
    }

    public final void f(int var1) {
        if (this.E == 0) {
            this.scrollBy(var1, 0);
        } else {
            this.scrollBy(0, var1);
        }

    }

    public View focusSearch(View var1, int var2) {
        this.F = var2;
        return super.focusSearch(var1, var2);
    }

    public final int g(View var1) {
        boolean var2 = this.C;
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
        return this.v;
    }

    public final void g(int var1) {
        if (this.E == 0) {
            this.smoothScrollBy(var1, 0);
        } else {
            this.smoothScrollBy(0, var1);
        }

    }

    public int getChildDrawingOrder(int var1, int var2) {
        int var3 = this.indexOfChild(this.v);
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
        if (this.E == 0) {
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
        this.z = new Scroller(this.getContext());
        this.c = false;
        this.u = false;
        this.e = 0;
        this.f = null;
        this.g = false;
        this.d = 1.04F;
        this.C = true;
        this.q = 22;
        this.r = 22;
        this.s = 22;
        this.t = 22;
        this.E = 0;
    }

    public final boolean j(View var1) {
        boolean var2 = false;
        boolean var3 = false;
        boolean var4 = var2;
        if (var1 != null) {
            Rect var5 = new Rect();
            boolean var6 = var1.getLocalVisibleRect(var5);
            if (this.E == 0) {
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
        this.z.abortAnimation();
        FocusBorderView var1 = this.a;
        if (var1 != null) {
            var1.stopAnimation();
            this.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            this.c = true;
            TvRecyclerView.b var2 = this.w;
            if (var2 != null) {
                var2.a(false, this.v, this.e);
            }

            this.z.startScroll(0, 0, 100, 100, 200);
            this.invalidate();
        } else {
            Log.d("TvRecyclerView", "startFocusMoveAnim: mFocusBorderView is null");
        }

    }

    public final boolean k(View var1) {
        Rect var2 = new Rect();
        var1.getGlobalVisibleRect(var2);
        int var3 = this.a();
        int var4 = this.E;
        boolean var5 = false;
        boolean var6 = false;
        if (var4 == 0) {
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
            this.v = var1;
            this.e = this.getChildAdapterPosition(var1);
        }

    }

    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.C) {
            if (K) {
                Log.d("TvRecyclerView", "onFinishInflate: add fly border view");
            }

            this.J = this.getLayerType();
            this.a(this.getContext());
        }

    }

    public void onFocusChanged(boolean var1, int var2, Rect var3) {
        super.onFocusChanged(var1, var2, var3);
        if (K) {
            StringBuilder var4 = new StringBuilder();
            var4.append("onFocusChanged: gainFocus==");
            var4.append(var1);
            Log.d("TvRecyclerView", var4.toString());
        }

        if (!var1) {
            this.a.setVisibility(GONE);
        } else {
            this.a.setVisibility(VISIBLE);
        }

        if (this.w != null) {
            if (this.v == null) {
                this.v = this.getChildAt(this.e - this.c());
            }

            this.w.a(var1, this.v, this.e);
        }

        FocusBorderView var5 = this.a;
        if (var5 != null) {
            var5.setTvRecyclerView(this);
            if (var1) {
                this.a.bringToFront();
            }

            View var6 = this.v;
            if (var6 != null) {
                if (var1) {
                    var6.setSelected(true);
                } else {
                    var6.setSelected(false);
                }

                if (var1 && !this.g) {
                    this.a.e();
                }
            }

            if (!var1) {
                this.a.b();
            }

        }
    }

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

    public boolean onKeyUp(int var1, KeyEvent var2) {
        if ((var1 == 23 || var1 == 66) && this.u) {
            if (this.getAdapter() != null && this.v != null && this.w != null) {
                FocusBorderView var3 = this.a;
                if (var3 != null) {
                    var3.d();
                }

                this.w.a(this.v, this.e);
            }

            this.u = false;
            if (this.C) {
                return true;
            }
        }

        return super.onKeyUp(var1, var2);
    }

    public void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
        this.g = true;
        super.onLayout(var1, var2, var3, var4, var5);
        Adapter var6 = this.getAdapter();
        if (var6 != null && this.e >= var6.getItemCount()) {
            this.e = var6.getItemCount() - 1;
        }

        var3 = this.e - this.c();
        var2 = var3;
        if (var3 < 0) {
            var2 = 0;
        }

        this.v = this.getChildAt(var2);
        this.g = false;
        if (K) {
            StringBuilder var7 = new StringBuilder();
            var7.append("onLayout: selectPos=");
            var7.append(var2);
            var7.append("=SelectedItem=");
            var7.append(this.v);
            Log.d("TvRecyclerView", var7.toString());
        }

    }

    public void onRestoreInstanceState(Parcelable var1) {
        Bundle var2 = (Bundle) var1;
        super.onRestoreInstanceState(var2.getParcelable("super_data"));
        this.setItemSelected(var2.getInt("select_pos", 0));
    }

    public Parcelable onSaveInstanceState() {
        Bundle var1 = new Bundle();
        var1.putParcelable("super_data", super.onSaveInstanceState());
        var1.putInt("select_pos", this.e);
        return var1;
    }

    public void requestChildFocus(View var1, View var2) {
        super.requestChildFocus(var1, var2);
        if (this.e < 0) {
            this.e = this.a(var1);
        }

        StringBuilder var5;
        if (this.C) {
            this.requestFocus();
        } else {
            int var3 = this.a(var2);
            if ((this.e != var3 || this.I) && !this.G) {
                this.e = var3;
                this.v = var2;
                int var4 = this.f(var2);
                var3 = var4;
                if (this.I) {
                    var3 = var4;
                    if (this.B != 1) {
                        var3 = this.e(var2);
                    }
                }

                this.I = false;
                if (var3 != 0) {
                    if (K) {
                        var5 = new StringBuilder();
                        var5.append("requestChildFocus: scroll distance=");
                        var5.append(var3);
                        Log.d("TvRecyclerView", var5.toString());
                    }

                    this.g(var3);
                }
            }
        }

        if (K) {
            var5 = new StringBuilder();
            var5.append("requestChildFocus: SelectPos=");
            var5.append(this.e);
            Log.d("TvRecyclerView", var5.toString());
        }

    }

    public void setFocusDrawable(Drawable var1) {
        this.b = var1;
    }

    public void setIsAutoProcessFocus(boolean var1) {
        this.C = var1;
        if (!var1) {
            this.d = 1.0F;
            this.setChildrenDrawingOrderEnabled(true);
        } else if (this.d == 1.0F) {
            this.d = 1.04F;
        }

    }

    public void setItemSelected(int var1) {
        if (this.e != var1) {
            int var2 = var1;
            if (var1 >= this.getAdapter().getItemCount()) {
                var2 = this.getAdapter().getItemCount() - 1;
            }

            var1 = this.getChildAdapterPosition(this.getChildAt(0));
            int var3 = this.getChildAdapterPosition(this.getChildAt(this.getChildCount() - 1));
            if (K) {
                StringBuilder var4 = new StringBuilder();
                var4.append("setItemSelected: first=");
                var4.append(var1);
                var4.append("=last=");
                var4.append(var3);
                var4.append("=pos=");
                var4.append(var2);
                Log.d("TvRecyclerView", var4.toString());
            }

            if (var2 >= var1 && var2 <= var3) {
                View var5 = this.getChildAt(var2 - var1);
                this.f = var5;
                if (this.C && !this.c) {
                    this.a(var5, true);
                } else {
                    this.f.requestFocus();
                }
            } else {
                this.G = true;
                this.e = var2;
                this.scrollToPosition(var2);
            }

        }
    }

    public void setLayoutManager(LayoutManager var1) {
        if (var1 instanceof GridLayoutManager) {
            GridLayoutManager var2 = (GridLayoutManager) var1;
            this.E = var2.getOrientation();
            this.H = var2.getSpanCount();
        } else if (var1 instanceof LinearLayoutManager) {
            this.E = ((LinearLayoutManager) var1).getOrientation();
            this.H = 1;
        }

        StringBuilder var3 = new StringBuilder();
        var3.append("setLayoutManager: orientation==");
        var3.append(this.E);
        Log.i("TvRecyclerView", var3.toString());
        super.setLayoutManager(var1);
    }

    public void setOnItemStateListener(TvRecyclerView.b var1) {
        this.w = var1;
    }

    public void setOnScrollStateListener(TvRecyclerView.e var1) {
        this.y = var1;
    }

    public void setOverstepBorderListener(TvRecyclerView.c var1) {
        this.x = var1;
    }

    public void setScrollMode(int var1) {
        this.B = var1;
    }

    public void setSelectPadding(int var1, int var2, int var3, int var4) {
        this.q = var1;
        this.r = var2;
        this.s = var3;
        this.t = var4;
        FocusBorderView var5 = this.a;
        if (var5 != null) {
            var5.setSelectPadding(var1, var2, var3, var4);
        }

    }

    public void setSelectedScale(float var1) {
        if (var1 >= 1.0F) {
            this.d = var1;
        }

    }

    public interface b {
        void a(View var1, int var2);

        void a(boolean var1, View var2, int var3);
    }

    public interface c {
        boolean a(View var1, ViewHolder var2, int var3);
    }

    public final class d extends TvSmoothScroller {
        public int a;
        public int b = 10;

        public d(Context var2, int var3) {
            super(var2);
            this.a = var3;
            var3 = TvRecyclerView.this.e;
            int var4;
            if (this.a > 0) {
                var4 = var3 + TvRecyclerView.this.H;
                int var5 = TvRecyclerView.this.getAdapter().getItemCount() - 1;
                var3 = var4;
                if (var4 > var5) {
                    var3 = var5;
                }
            } else {
                var4 = var3 - TvRecyclerView.this.H;
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

                return TvRecyclerView.this.E == 0 ? new PointF((float) var2, 0.0F) : new PointF(0.0F, (float) var2);
            }
        }

        public void onStop() {
            this.a = 0;
            TvRecyclerView.this.A = null;
            int var1 = this.getTargetPosition();
            View var2 = this.findViewByPosition(var1);
            StringBuilder var3 = new StringBuilder();
            var3.append("PendingMoveSmoothScroller onStop: targetPos=");
            var3.append(var1);
            var3.append("==targetView=");
            var3.append(var2);
            Log.i("TvRecyclerView", var3.toString());
            if (var2 == null) {
                super.onStop();
            } else {
                if (TvRecyclerView.this.e != var1) {
                    TvRecyclerView.this.e = var1;
                }

                if (!TvRecyclerView.this.C) {
                    var2.requestFocus();
                } else {
                    TvRecyclerView.this.f = var2;
                    TvRecyclerView.this.a(var2, true);
                }

                super.onStop();
            }
        }

        public void updateActionForInterimTarget(Action var1) {
            if (this.a != 0) {
                super.updateActionForInterimTarget(var1);
            }
        }
    }

    public interface e {
        void a(View var1);

        void b(View var1);
    }
}

