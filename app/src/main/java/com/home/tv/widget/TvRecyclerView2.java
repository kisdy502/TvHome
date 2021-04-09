//package com.home.tv.widget;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PointF;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.FocusFinder;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Scroller;
//
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.home.tv.R;
//
////import androidx.leanback.R.styleable;
////import androidx.recyclerview.widget.GridLayoutManager;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////import androidx.recyclerview.widget.RecyclerView.LayoutParams;
////import androidx.recyclerview.widget.RecyclerView.c0;    ViewHolder
////import androidx.recyclerview.widget.RecyclerView.g;     Adapter
////import androidx.recyclerview.widget.RecyclerView.o;     LayoutManager
////import androidx.recyclerview.widget.RecyclerView.s;     OnScrollListener
////import androidx.recyclerview.widget.RecyclerView.y.a;   SmoothScroller.Action
//
//
//public class TvRecyclerView2 extends RecyclerView {
//    private final static String TAG = "TvRecyclerView";
//    public static boolean isDebug = true;
//    public InnerTvSmoothScroller innerTvSmoothScroller;
//    public int scrollMode;
//    public boolean isAutoProcessFocus;
//    public int orientation;
//    public int direction;
//    public boolean G;
//    public int spanCount;
//    public boolean I;
//    public int layerType;
//    public FocusBorderView mFocusBorderView;
//    public Drawable mFocusDrawable;
//    public boolean c;
//    public float focusScale;
//    public int selectPosition;
//    public View targetView;
//    public boolean g;
//    public int q;
//    public int r;
//    public int s;
//    public int t;
//    public boolean u;
//    public View focuseView;
//    public ItemStateListener itemStateListener;
//    public OverstepBorderListener overstepBorderListener;
//    public ScrollStateListener scrollStateListener;
//    public Scroller scroller;
//
//    public TvRecyclerView2(Context context) {
//        this(context, (AttributeSet) null);
//    }
//
//    public TvRecyclerView2(Context context, AttributeSet attributeSet) {
//        this(context, attributeSet, 0);
//    }
//
//    public TvRecyclerView2(Context context, AttributeSet attributeSet, int defStyle) {
//        super(context, attributeSet, defStyle);
//        this.scrollMode = 0;
//        this.G = false;
//        this.spanCount = 1;
//        this.I = false;
//        this.layerType = 1;
//        this.resetState();
//        this.parseAttribute(attributeSet);
//        this.addOnScrollListener(new OnScrollListener() {
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (TvRecyclerView2.this.G) {
//                    if (TvRecyclerView2.isDebug) {
//                        StringBuilder var4 = new StringBuilder();
//                        var4.append("onScrolled: mSelectedPosition=");
//                        var4.append(TvRecyclerView2.this.selectPosition);
//                        Log.d("TvRecyclerView", var4.toString());
//                    }
//
//                    TvRecyclerView2.this.G = false;
//                    dx = TvRecyclerView2.this.getFirstVisibleItemPosition();
//                    TvRecyclerView2 var5 = TvRecyclerView2.this;
//                    var5.targetView = var5.getChildAt(var5.selectPosition - dx);
//                    if (TvRecyclerView2.this.targetView != null) {
//                        if (TvRecyclerView2.isDebug) {
//                            Log.d("TvRecyclerView", "onScrolled: start adjust scroll distance");
//                        }
//
//                        if (TvRecyclerView2.this.isAutoProcessFocus) {
//                            var5 = TvRecyclerView2.this;
//                            var5.scrollDistance(var5.targetView, true);
//                        } else {
//                            TvRecyclerView2.this.I = true;
//                            TvRecyclerView2.this.targetView.requestFocus();
//                        }
//                    }
//                }
//
//            }
//        });
//    }
//
//    public final int a() {
//        int var1;
//        int var2;
//        if (this.orientation == 0) {
//            var1 = this.getWidth() - this.getPaddingLeft();
//            var2 = this.getPaddingRight();
//        } else {
//            var1 = this.getHeight() - this.getPaddingTop();
//            var2 = this.getPaddingBottom();
//        }
//
//        return var1 - var2;
//    }
//
//    public final int a(int keyCode) {
//        byte var3;
//        label64:
//        {
//            label65:
//            {
//                label66:
//                {
//                    int var2 = this.orientation;
//                    var3 = 0;
//                    if (var2 == 0) {
//                        switch (keyCode) {
//                            case 19:
//                                break label65;
//                            case 20:
//                                break label64;
//                            case 21:
//                                return var3;
//                            case 22:
//                                break label66;
//                        }
//                    } else if (var2 == 1) {
//                        switch (keyCode) {
//                            case 19:
//                                return var3;
//                            case 20:
//                                break label66;
//                            case 21:
//                                break label65;
//                            case 22:
//                                break label64;
//                        }
//                    }
//
//                    var3 = 17;
//                    return var3;
//                }
//
//                var3 = 1;
//                return var3;
//            }
//
//            var3 = 2;
//            return var3;
//        }
//
//        var3 = 3;
//        return var3;
//    }
//
//
//    public final int getViewAdapterPosition(View view) {
//        if (view == null) {
//            return NO_POSITION;
//        } else {
//            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//            return layoutParams != null && !layoutParams.isItemRemoved() ? layoutParams.getViewAdapterPosition() : NO_POSITION;
//        }
//    }
//
//    public final void initFocusBorderView(Context context) {
//        if (this.mFocusBorderView == null) {
//            this.mFocusBorderView = new FocusBorderView(context);
//            ((Activity) context).getWindow().addContentView(this.mFocusBorderView,
//                    new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -ViewGroup.LayoutParams.MATCH_PARENT));
//            this.mFocusBorderView.setSelectPadding(this.q, this.r, this.s, this.t);
//        }
//
//    }
//
//    public final void parseAttribute(AttributeSet attributeSet) {
//        if (attributeSet != null) {
//            TypedArray typedArray = this.getContext().obtainStyledAttributes(attributeSet, R.styleable.TvRecyclerView);
//            this.scrollMode = typedArray.getInteger(R.styleable.TvRecyclerView_scrollMode, 0);
//            Drawable drawable = typedArray.getDrawable(R.styleable.TvRecyclerView_focusDrawable);
//            if (drawable != null) {
//                this.setmFocusDrawable(drawable);
//            }
//
//            this.focusScale = typedArray.getFloat(R.styleable.TvRecyclerView_focusScale, 1.04F);
//            this.isAutoProcessFocus = typedArray.getBoolean(R.styleable.TvRecyclerView_isAutoProcessFocus, true);
//            if (!isAutoProcessFocus) {
//                this.focusScale = 1.0F;
//                this.setChildrenDrawingOrderEnabled(true);
//            }
//            typedArray.recycle();
//        }
//
//        if (this.isAutoProcessFocus) {
//            this.setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
//        }
//
//    }
//
//    public final void scrollDistance(View itemView, boolean smooth) {
//        int offset = this.f(itemView);
//        if (isDebug) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("scrollToView: scrollDistance==");
//            sb.append(offset);
//            Log.d("TvRecyclerView", sb.toString());
//        }
//
//        if (offset != 0) {
//            if (smooth) {
//                this.smoothScrollOffset(offset);
//            } else {
//                this.scrollOffset(offset);
//            }
//        }
//
//        this.startFocusMoveAnim();
//    }
//
//    public final void a(boolean var1) {
//        if (var1) {
//            if (this.i()) {
//                return;
//            }
//        } else if (this.h()) {
//            return;
//        }
//
//        InnerTvSmoothScroller smoothScroller = this.innerTvSmoothScroller;
//        if (smoothScroller == null) {
//            this.stopScroll();
//            Context var4 = this.getContext();
//            byte var3;
//            if (var1) {
//                var3 = 1;
//            } else {
//                var3 = -1;
//            }
//
//            smoothScroller = new InnerTvSmoothScroller(var4, var3);
//            this.getLayoutManager().startSmoothScroll(smoothScroller);
//            if (smoothScroller.isRunning()) {
//                this.innerTvSmoothScroller = smoothScroller;
//            }
//        } else if (var1) {
//            smoothScroller.b();
//        } else {
//            smoothScroller.a();
//        }
//
//    }
//
//    public final int b(View view) {
//        boolean var2 = this.l(view);
//        int var3;
//        if (!this.childVisible(view) && var2) {
//            var3 = 0;
//        } else {
//            var3 = this.i(view);
//        }
//
//        return var3;
//    }
//
//    public Drawable getFocusDrawable() {
//        return this.mFocusDrawable;
//    }
//
//    public final boolean b(int keyCode) {
//        View view = this.focuseView;
//        if (view == null) {
//            return false;
//        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//            return this.overstepBorderListener.allowOverstep(view, this.getChildViewHolder(view), 0);
//        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//            return this.overstepBorderListener.allowOverstep(view, this.getChildViewHolder(view), 2);
//        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//            return this.overstepBorderListener.allowOverstep(view, this.getChildViewHolder(view), 1);
//        } else {
//            return keyCode == KeyEvent.KEYCODE_DPAD_DOWN ? this.overstepBorderListener.allowOverstep(view, this.getChildViewHolder(view), 3) : false;
//        }
//    }
//
//    public int getFirstVisibleItemPosition() {
//        LayoutManager layoutManager = this.getLayoutManager();
//        int position;
//        if (layoutManager != null) {
//            if (layoutManager instanceof LinearLayoutManager) {
//                position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
//                return position;
//            }
//        }
//
//        position = NO_POSITION;
//        return position;
//    }
//
//    public final int c(View var1) {
//        LayoutParams var2 = (LayoutParams) var1.getLayoutParams();
//        int var3;
//        int var4;
//        if (this.orientation == 1) {
//            var3 = this.getLayoutManager().getDecoratedBottom(var1);
//            var4 = var2.bottomMargin;
//        } else {
//            var3 = this.getLayoutManager().getDecoratedRight(var1);
//            var4 = var2.rightMargin;
//        }
//
//        return var3 + var4;
//    }
//
//    public final boolean c(int var1) {
//        if (!this.isAutoProcessFocus) {
//            return false;
//        } else {
//            int var2 = this.a(var1);
//            if (var2 == 1) {
//                if (!this.i()) {
//                    this.a(true);
//                    return true;
//                } else {
//                    return this.b(var1);
//                }
//            } else if (var2 == 0) {
//                if (!this.h()) {
//                    this.a(false);
//                    return true;
//                } else {
//                    return this.b(var1);
//                }
//            } else if (var2 == 2) {
//                return this.b(var1);
//            } else {
//                return var2 == 3 ? this.b(var1) : false;
//            }
//        }
//    }
//
//    @Override
//    public void computeScroll() {
//        if (this.scroller.computeScrollOffset()) {
//            if (this.c) {
//                this.scroller.getCurrX();
//            }
//            this.postInvalidate();
//        } else if (this.c) {
//            this.c = false;
//            this.m(this.targetView);
//            this.setLayerType(this.layerType, (Paint) null);
//            this.postInvalidate();
//            ItemStateListener var1 = this.itemStateListener;
//            if (var1 != null) {
//                var1.a(true, this.focuseView, this.selectPosition);
//            }
//        }
//
//    }
//
//    public final int d() {
//        return this.orientation == 0 ? this.getPaddingLeft() : this.getPaddingTop();
//    }
//
//    public final int d(View view) {
//        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//        int var3;
//        int var4;
//        if (this.orientation == VERTICAL) {
//            var3 = this.getLayoutManager().getDecoratedTop(view);
//            var4 = layoutParams.topMargin;
//        } else {
//            var3 = this.getLayoutManager().getDecoratedLeft(view);
//            var4 = layoutParams.leftMargin;
//        }
//
//        return var3 - var4;
//    }
//
//    public final void d(int var1) {
//        if (scrollStateListener != null) {
//            if (this.orientation == HORIZONTAL) {
//                if (var1 == 22) {
//                    scrollStateListener.b(this.focuseView);
//                } else if (var1 == 21) {
//                    scrollStateListener.a(this.focuseView);
//                }
//            } else if (var1 == 20) {
//                scrollStateListener.b(this.focuseView);
//            } else if (var1 == 19) {
//                scrollStateListener.a(this.focuseView);
//            }
//        }
//
//    }
//
//    @Override
//    public void dispatchDraw(Canvas var1) {
//        super.dispatchDraw(var1);
//        FocusBorderView var2 = this.mFocusBorderView;
//        if (var2 != null && var2.tvRecyclerView != null) {
//            if (isDebug) {
//                Log.d("TvRecyclerView", "dispatchDraw: Border view invalidate.");
//            }
//
//            this.mFocusBorderView.invalidate();
//        }
//
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            int keyCode = event.getKeyCode();
//            if (this.focuseView == null) {
//                this.focuseView = this.getChildAt(this.selectPosition);
//            }
//
//            label49:
//            {
//                Exception var10000;
//                boolean var10001;
//                if (keyCode == 21) {
//                    try {
//                        this.targetView = FocusFinder.getInstance().findNextFocus(this, this.focuseView, 17);
//                        break label49;
//                    } catch (Exception var5) {
//                        var10000 = var5;
//                        var10001 = false;
//                    }
//                } else if (keyCode == 22) {
//                    try {
//                        this.targetView = FocusFinder.getInstance().findNextFocus(this, this.focuseView, 66);
//                        break label49;
//                    } catch (Exception var6) {
//                        var10000 = var6;
//                        var10001 = false;
//                    }
//                } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//                    try {
//                        this.targetView = FocusFinder.getInstance().findNextFocus(this, this.focuseView, 33);
//                        break label49;
//                    } catch (Exception var7) {
//                        var10000 = var7;
//                        var10001 = false;
//                    }
//                } else {
//                    if (keyCode != KeyEvent.KEYCODE_DPAD_DOWN) {
//                        break label49;
//                    }
//
//                    try {
//                        this.targetView = FocusFinder.getInstance().findNextFocus(this, this.focuseView, 130);
//                        break label49;
//                    } catch (Exception var8) {
//                        var10000 = var8;
//                        var10001 = false;
//                    }
//                }
//
//                Exception var3 = var10000;
//                StringBuilder var4 = new StringBuilder();
//                var4.append("dispatchKeyEvent: get next focus item error: ");
//                var4.append(var3.getMessage());
//                Log.e("TvRecyclerView", var4.toString());
//                this.targetView = null;
//            }
//
//            if (isDebug) {
//                StringBuilder var9 = new StringBuilder();
//                var9.append("dispatchKeyEvent: mNextFocused=");
//                var9.append(this.targetView);
//                var9.append("=nextPos=");
//                var9.append(this.getChildAdapterPosition(this.targetView));
//                Log.d("TvRecyclerView", var9.toString());
//            }
//        }
//
//        return super.dispatchKeyEvent(event);
//    }
//
//    public int getSelectPosition() {
//        return this.selectPosition;
//    }
//
//    public final int e(View var1) {
//        int var2;
//        if (this.k(var1)) {
//            var2 = this.i(var1);
//        } else {
//            var2 = 0;
//        }
//
//        return var2;
//    }
//
//    public final boolean processMoves(int var1) {
//        if (targetView == null) {
//            if (this.c(var1)) {
//                return true;
//            } else {
//                if (this.isAutoProcessFocus) {
//                    this.d(var1);
//                    this.c = false;
//                }
//
//                if (isDebug) {
//                    Log.d("TvRecyclerView", "processMoves: error");
//                }
//
//                return false;
//            }
//        } else {
//            if (this.c) {
//                this.m(targetView);
//            }
//
//            this.scrollDistance(this.targetView, true);
//            return true;
//        }
//    }
//
//    public float getScale() {
//        return this.focusScale;
//    }
//
//    public final int f(View view) {
//        int scrollMode = this.scrollMode;
//        if (scrollMode != 1) {
//            return scrollMode != 2 ? this.b(view) : this.g(view);
//        } else {
//            return this.e(view);
//        }
//    }
//
//    public final void scrollOffset(int offset) {
//        if (this.orientation == HORIZONTAL) {
//            this.scrollBy(offset, 0);
//        } else {
//            this.scrollBy(0, offset);
//        }
//
//    }
//
//    @Override
//    public View focusSearch(View focused, int direction) {
//        this.direction = direction;
//        return super.focusSearch(focused, direction);
//    }
//
//    public final int g(View var1) {
//        boolean var2 = this.isAutoProcessFocus;
//        int var3 = 0;
//        if (!var2) {
//            return 0;
//        } else {
//            var2 = this.l(var1);
//            if (this.childVisible(var1) || !var2) {
//                var3 = this.h(var1);
//            }
//
//            return var3;
//        }
//    }
//
//    public View getFocusView() {
//        return this.focuseView;
//    }
//
//    public final void smoothScrollOffset(int offset) {
//        if (this.orientation == 0) {
//            this.smoothScrollBy(offset, 0);
//        } else {
//            this.smoothScrollBy(0, offset);
//        }
//
//    }
//
//    @Override
//    public int getChildDrawingOrder(int childCount, int drawingPosition) {
//        int index = this.indexOfChild(this.focuseView);
//        if (index < 0) {
//            return drawingPosition;
//        } else if (drawingPosition < index) {
//            return drawingPosition;
//        } else {
//            int order = index;
//            if (drawingPosition < childCount - 1) {
//                order = index + childCount - 1 - drawingPosition;
//            }
//            return order;
//        }
//    }
//
//    public final int h(View var1) {
//        int var2 = this.d(var1);
//        int var3 = this.c(var1);
//        int var4 = this.d();
//        int var5 = this.a() + var4 - 45;
//        View var6 = null;
//        if (var2 >= var4) {
//            if (var3 > var5) {
//                Object var7 = null;
//                var6 = var1;
//                var1 = (View) var7;
//            } else {
//                var1 = null;
//            }
//        }
//
//        if (var1 != null) {
//            var5 = this.d(var1) - var4 - 45;
//        } else if (var6 != null) {
//            var5 = this.c(var6) - var5;
//        } else {
//            var5 = 0;
//        }
//
//        return var5;
//    }
//
//    public final boolean h() {
//        int itemCount = this.getLayoutManager().getItemCount();
//        boolean var2 = false;
//        if (itemCount == 0 || this.findViewHolderForAdapterPosition(0) != null) {
//            var2 = true;
//        }
//
//        return var2;
//    }
//
//    public final int i(View var1) {
//        int var2;
//        int var3;
//        if (this.orientation == 0) {
//            var2 = this.direction;
//            if (var2 != -1 && (var2 == 33 || var2 == 130)) {
//                return 0;
//            }
//
//            var3 = var1.getLeft() + var1.getWidth() / 2;
//            var2 = this.a() / 2;
//        } else {
//            var2 = this.direction;
//            if (var2 != -1 && (var2 == 17 || var2 == 66)) {
//                return 0;
//            }
//
//            var3 = var1.getTop() + var1.getHeight() / 2;
//            var2 = this.a() / 2;
//        }
//
//        return var3 - var2;
//    }
//
//    public final boolean i() {
//        int var1 = this.getLayoutManager().getItemCount();
//        boolean var2 = true;
//        boolean var3 = var2;
//        if (var1 != 0) {
//            if (this.findViewHolderForAdapterPosition(var1 - 1) != null) {
//                var3 = var2;
//            } else {
//                var3 = false;
//            }
//        }
//
//        return var3;
//    }
//
//    public boolean isInTouchMode() {
//        boolean inTouchMode = super.isInTouchMode();
//        boolean result = inTouchMode;
//        if (VERSION.SDK_INT == 19) {
//            if (this.hasFocus() && !inTouchMode) {
//                result = false;
//            } else {
//                result = true;
//            }
//        }
//        return result;
//    }
//
//    public final void resetState() {
//        this.scroller = new Scroller(this.getContext());
//        this.c = false;
//        this.u = false;
//        this.selectPosition = 0;
//        this.targetView = null;
//        this.g = false;
//        this.focusScale = 1.04F;
//        this.isAutoProcessFocus = true;
//        this.q = 22;
//        this.r = 22;
//        this.s = 22;
//        this.t = 22;
//        this.orientation = HORIZONTAL;
//    }
//
//    public final boolean childVisible(View view) {
//        boolean var2 = false;
//        boolean var3 = false;
//        boolean var4 = var2;
//        if (view != null) {
//            Rect var5 = new Rect();
//            boolean var6 = view.getLocalVisibleRect(var5);
//            if (this.orientation == HORIZONTAL) {
//                var4 = var3;
//                if (var6) {
//                    var4 = var3;
//                    if (var5.width() < view.getWidth()) {
//                        var4 = true;
//                    }
//                }
//
//                return var4;
//            }
//
//            var4 = var2;
//            if (var6) {
//                var4 = var2;
//                if (var5.height() < view.getHeight()) {
//                    var4 = true;
//                }
//            }
//        }
//
//        return var4;
//    }
//
//    public final void startFocusMoveAnim() {
//        this.scroller.abortAnimation();
//        if (mFocusBorderView != null) {
//            mFocusBorderView.stopAnimation();
//            this.setLayerType(LAYER_TYPE_NONE, (Paint) null);
//            this.c = true;
//            ItemStateListener var2 = this.itemStateListener;
//            if (var2 != null) {
//                var2.a(false, this.focuseView, this.selectPosition);
//            }
//
//            this.scroller.startScroll(0, 0, 100, 100, 200);
//            this.invalidate();
//        } else {
//            Log.d("TvRecyclerView", "startFocusMoveAnim: mFocusBorderView is null");
//        }
//
//    }
//
//    public final boolean k(View var1) {
//        Rect var2 = new Rect();
//        var1.getGlobalVisibleRect(var2);
//        int var3 = this.a();
//        int var4 = this.orientation;
//        boolean var5 = false;
//        boolean var6 = false;
//        if (var4 == 0) {
//            var4 = var2.right;
//            var3 /= 2;
//            if (var4 > var3 || var2.left < var3) {
//                var6 = true;
//            }
//
//            return var6;
//        } else {
//            var4 = var2.top;
//            var3 /= 2;
//            if (var4 >= var3) {
//                var6 = var5;
//                if (var2.bottom <= var3) {
//                    return var6;
//                }
//            }
//
//            var6 = true;
//            return var6;
//        }
//    }
//
//    public final boolean l(View var1) {
//        return var1 != null ? var1.getLocalVisibleRect(new Rect()) : false;
//    }
//
//    public final void m(View var1) {
//        if (var1 != null) {
//            this.focuseView = var1;
//            this.selectPosition = this.getChildAdapterPosition(var1);
//        }
//
//    }
//
//    @Override
//    public void onFinishInflate() {
//        super.onFinishInflate();
//        if (this.isAutoProcessFocus) {
//            if (isDebug) {
//                Log.d("TvRecyclerView", "onFinishInflate: add fly border view");
//            }
//
//            this.layerType = this.getLayerType();
//            this.initFocusBorderView(this.getContext());
//        }
//
//    }
//
//    @Override
//    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
//        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//        if (isDebug) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("onFocusChanged: gainFocus==");
//            stringBuilder.append(gainFocus);
//            Log.d("TvRecyclerView", stringBuilder.toString());
//        }
//
//        if (!gainFocus) {
//            this.mFocusBorderView.setVisibility(GONE);
//        } else {
//            this.mFocusBorderView.setVisibility(VISIBLE);
//        }
//
//        if (this.itemStateListener != null) {
//            if (this.focuseView == null) {
//                this.focuseView = this.getChildAt(this.selectPosition - this.getFirstVisibleItemPosition());
//            }
//
//            this.itemStateListener.a(gainFocus, this.focuseView, this.selectPosition);
//        }
//
//        FocusBorderView borderView = this.mFocusBorderView;
//        if (borderView != null) {
//      //      borderView.setTvRecyclerView(this);
//            if (gainFocus) {
//                this.mFocusBorderView.bringToFront();
//            }
//
//            View var6 = this.focuseView;
//            if (var6 != null) {
//                if (gainFocus) {
//                    var6.setSelected(true);
//                } else {
//                    var6.setSelected(false);
//                }
//
//                if (gainFocus && !this.g) {
//                    this.mFocusBorderView.e();
//                }
//            }
//
//            if (!gainFocus) {
//                this.mFocusBorderView.b();
//            }
//
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyDown:" + keyCode);
//        if (keyCode != 66) {
//            switch (keyCode) {
//                case 19:
//                case 20:
//                case 21:
//                case 22:
//                    if (this.processMoves(keyCode)) {
//                        return true;
//                    }
//                    return super.onKeyDown(keyCode, event);
//                case 23:
//                    break;
//                default:
//                    return super.onKeyDown(keyCode, event);
//            }
//        }
//
//        this.u = true;
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if ((keyCode == 23 || keyCode == 66) && this.u) {
//            if (this.getAdapter() != null && this.focuseView != null && this.itemStateListener != null) {
//                FocusBorderView borderView = this.mFocusBorderView;
//                if (borderView != null) {
//                    borderView.d();
//                }
//
//                this.itemStateListener.a(this.focuseView, this.selectPosition);
//            }
//
//            this.u = false;
//            if (this.isAutoProcessFocus) {
//                return true;
//            }
//        }
//
//        return super.onKeyUp(keyCode, event);
//    }
//
//    public void onLayout(boolean changed, int l, int t, int r, int b) {
//        this.g = true;
//        super.onLayout(changed, l, t, r, b);
//        Adapter adapter = this.getAdapter();
//        if (adapter != null && this.selectPosition >= adapter.getItemCount()) {
//            this.selectPosition = adapter.getItemCount() - 1;
//        }
//
//        t = this.selectPosition - this.getFirstVisibleItemPosition();
//        l = t;
//        if (t < 0) {
//            l = 0;
//        }
//
//        this.focuseView = this.getChildAt(l);
//        this.g = false;
//        if (isDebug) {
//            StringBuilder var7 = new StringBuilder();
//            var7.append("onLayout: selectPos=");
//            var7.append(l);
//            var7.append("=SelectedItem=");
//            var7.append(this.focuseView);
//            Log.d("TvRecyclerView", var7.toString());
//        }
//
//    }
//
//    public void onRestoreInstanceState(Parcelable parcelable) {
//        Bundle bundle = (Bundle) parcelable;
//        super.onRestoreInstanceState(bundle.getParcelable("super_data"));
//        this.setItemSelected(bundle.getInt("select_pos", 0));
//    }
//
//    public Parcelable onSaveInstanceState() {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("super_data", super.onSaveInstanceState());
//        bundle.putInt("select_pos", this.selectPosition);
//        return bundle;
//    }
//
//    @Override
//    public void requestChildFocus(View child, View focused) {
//        super.requestChildFocus(child, focused);
//        if (this.selectPosition < 0) {
//            this.selectPosition = this.getViewAdapterPosition(child);
//        }
//
//        StringBuilder var5;
//        if (this.isAutoProcessFocus) {
//            this.requestFocus();
//        } else {
//            int var3 = this.getViewAdapterPosition(focused);
//            if ((this.selectPosition != var3 || this.I) && !this.G) {
//                this.selectPosition = var3;
//                this.focuseView = focused;
//                int var4 = this.f(focused);
//                var3 = var4;
//                if (this.I) {
//                    var3 = var4;
//                    if (this.scrollMode != 1) {
//                        var3 = this.e(focused);
//                    }
//                }
//
//                this.I = false;
//                if (var3 != 0) {
//                    if (isDebug) {
//                        var5 = new StringBuilder();
//                        var5.append("requestChildFocus: scroll distance=");
//                        var5.append(var3);
//                        Log.d("TvRecyclerView", var5.toString());
//                    }
//
//                    this.smoothScrollOffset(var3);
//                }
//            }
//        }
//
//        if (isDebug) {
//            var5 = new StringBuilder();
//            var5.append("requestChildFocus: SelectPos=");
//            var5.append(this.selectPosition);
//            Log.d("TvRecyclerView", var5.toString());
//        }
//
//    }
//
//    public void setmFocusDrawable(Drawable drawable) {
//        this.mFocusDrawable = drawable;
//    }
//
//    public void setIsAutoProcessFocus(boolean autoProcess) {
//        this.isAutoProcessFocus = autoProcess;
//        if (!autoProcess) {
//            this.focusScale = 1.0F;
//            this.setChildrenDrawingOrderEnabled(true);
//        } else if (this.focusScale == 1.0F) {
//            this.focusScale = 1.04F;
//        }
//
//    }
//
//    public void setItemSelected(int position) {
//        if (this.selectPosition != position) {
//            int var2 = position;
//            if (position >= this.getAdapter().getItemCount()) {
//                var2 = this.getAdapter().getItemCount() - 1;
//            }
//
//            position = this.getChildAdapterPosition(this.getChildAt(0));
//            int last = this.getChildAdapterPosition(this.getChildAt(this.getChildCount() - 1));
//            if (isDebug) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("setItemSelected: first=");
//                sb.append(position);
//                sb.append("=last=");
//                sb.append(last);
//                sb.append("=pos=");
//                sb.append(var2);
//                Log.d("TvRecyclerView", sb.toString());
//            }
//
//            if (var2 >= position && var2 <= last) {
//                View view = this.getChildAt(var2 - position);
//                this.targetView = view;
//                if (this.isAutoProcessFocus && !this.c) {
//                    this.scrollDistance(view, true);
//                } else {
//                    this.targetView.requestFocus();
//                }
//            } else {
//                this.G = true;
//                this.selectPosition = var2;
//                this.scrollToPosition(var2);
//            }
//
//        }
//    }
//
//    @Override
//    public void setLayoutManager(LayoutManager layoutManager) {
//        if (layoutManager instanceof GridLayoutManager) {
//            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//            this.orientation = gridLayoutManager.getOrientation();
//            this.spanCount = gridLayoutManager.getSpanCount();
//        } else if (layoutManager instanceof LinearLayoutManager) {
//            this.orientation = ((LinearLayoutManager) layoutManager).getOrientation();
//            this.spanCount = 1;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("setLayoutManager: orientation==");
//        sb.append(this.orientation);
//        Log.i("TvRecyclerView", sb.toString());
//        super.setLayoutManager(layoutManager);
//    }
//
//    public void setOnItemStateListener(ItemStateListener listener) {
//        this.itemStateListener = listener;
//    }
//
//    public void setOnScrollStateListener(ScrollStateListener listener) {
//        this.scrollStateListener = listener;
//    }
//
//    public void setOverstepBorderListener(OverstepBorderListener borderListener) {
//        this.overstepBorderListener = borderListener;
//    }
//
//    public void setScrollMode(int scrollMode) {
//        this.scrollMode = scrollMode;
//    }
//
//    public void setSelectPadding(int var1, int var2, int var3, int var4) {
//        this.q = var1;
//        this.r = var2;
//        this.s = var3;
//        this.t = var4;
//        FocusBorderView var5 = this.mFocusBorderView;
//        if (var5 != null) {
//            var5.setSelectPadding(var1, var2, var3, var4);
//        }
//
//    }
//
//    public void setSelectedScale(float scale) {
//        if (scale >= 1.0F) {
//            this.focusScale = scale;
//        }
//
//    }
//
//    public interface ItemStateListener {
//        void a(View view, int var2);
//
//        void a(boolean var1, View view, int var3);
//    }
//
//    //允许超越边界
//    public interface OverstepBorderListener {
//        boolean allowOverstep(View view, ViewHolder viewHolder, int direction);
//    }
//
//    public final class InnerTvSmoothScroller extends TvSmoothScroller {
//        public int a;
//        public int b = 10;
//
//        public InnerTvSmoothScroller(Context context, int var3) {
//            super(context);
//            this.a = var3;
//            var3 = TvRecyclerView2.this.selectPosition;
//            int var4;
//            if (this.a > 0) {
//                var4 = var3 + TvRecyclerView2.this.spanCount;
//                int var5 = TvRecyclerView2.this.getAdapter().getItemCount() - 1;
//                var3 = var4;
//                if (var4 > var5) {
//                    var3 = var5;
//                }
//            } else {
//                var4 = var3 - TvRecyclerView2.this.spanCount;
//                var3 = var4;
//                if (var4 < 0) {
//                    var3 = 0;
//                }
//            }
//
//            this.setTargetPosition(var3);
//        }
//
//        public void a() {
//            int var1 = this.a;
//            if (var1 > -this.b) {
//                this.a = var1 - 1;
//            }
//
//        }
//
//        public void b() {
//            int var1 = this.a;
//            if (var1 < this.b) {
//                this.a = var1 + 1;
//            }
//
//        }
//
//        public PointF computeScrollVectorForPosition(int var1) {
//            var1 = this.a;
//            if (var1 == 0) {
//                return null;
//            } else {
//                byte var2;
//                if (var1 < 0) {
//                    var2 = -1;
//                } else {
//                    var2 = 1;
//                }
//
//                return TvRecyclerView2.this.orientation == 0 ? new PointF((float) var2, 0.0F) : new PointF(0.0F, (float) var2);
//            }
//        }
//
//        public void onStop() {
//            this.a = 0;
//            TvRecyclerView2.this.innerTvSmoothScroller = null;
//            int targetPosition = this.getTargetPosition();
//            View targetView = this.findViewByPosition(targetPosition);
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("PendingMoveSmoothScroller onStop: targetPos=");
//            sb.append(targetPosition);
//            sb.append("==targetView=");
//            sb.append(targetView);
//            Log.i("TvRecyclerView", sb.toString());
//
//            if (targetView == null) {
//                super.onStop();
//            } else {
//                if (TvRecyclerView2.this.selectPosition != targetPosition) {
//                    TvRecyclerView2.this.selectPosition = targetPosition;
//                }
//
//                if (!TvRecyclerView2.this.isAutoProcessFocus) {
//                    targetView.requestFocus();
//                } else {
//                    TvRecyclerView2.this.targetView = targetView;
//                    TvRecyclerView2.this.scrollDistance(targetView, true);
//                }
//                super.onStop();
//            }
//        }
//
//        public void updateActionForInterimTarget(Action action) {
//            if (this.a != 0) {
//                super.updateActionForInterimTarget(action);
//            }
//        }
//    }
//
//    public interface ScrollStateListener {
//        void a(View view);
//
//        void b(View view);
//    }
//}
