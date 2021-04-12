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
    private final static boolean IS_DEBUG = true;

    public final static int DIRECTION_LEFT = 0;
    public final static int DIRECTION_UP = 1;
    public final static int DIRECTION_RIGHT = 2;
    public final static int DIRECTION_DOWN = 3;


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
    public boolean startFocusMoveAnim;
    public float mFocusScale;
    public int mSelectedPosition;
    public View mNextFocused;
    public boolean isOnLayouting;
    public int focusBorderPaddingLeft;
    public int focusBorderPaddingTop;
    public int focusBorderPaddingRight;
    public int focusBorderPaddingBottom;
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

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
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
                } else {
                    Log.d(TAG, "onScrolled" + dx + "," + dy);
                }

            }
        });
    }

    public final int getTvRecyclerViewRealSize() {
        int height;
        if (this.mOrientation == HORIZONTAL) {
            height = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        } else {
            height = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        }
        Log.d(TAG, "TvRecyclerView real height:" + height);
        return height;
    }


    public final int getDirectionByKeyCode(int keyCode, int orientation) {
        if (orientation == 0) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    return 2;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    return 3;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    return 0;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    return 1;
            }
        } else if (orientation == 1) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    return 0;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    return 1;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    return 2;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    return 3;
            }
        } else {
            throw new IllegalArgumentException("un support orientation:" + orientation);
        }
        return 17;
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
            this.mFocusBorderView.setSelectPadding(this.focusBorderPaddingLeft, this.focusBorderPaddingTop, this.focusBorderPaddingRight, this.focusBorderPaddingBottom);
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
            mAutoProcessFocus = typedArray.getBoolean(R.styleable.TvRecyclerView_isAutoProcessFocus, true);
            if (!mAutoProcessFocus) {
                this.mFocusScale = 1.0F;
                this.setChildrenDrawingOrderEnabled(true);
            }
            typedArray.recycle();
        }

        if (this.mAutoProcessFocus) {
            this.setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        }

    }

    public final void scrollDistance(View child, boolean smooth) {
        int distance = this.calcDistance(child);
        if (IS_DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("scrollToView: scrollDistance==");
            sb.append(distance);
            sb.append(",smooth==");
            sb.append(smooth);
            Log.d("TvRecyclerView", sb.toString());
        }

        if (distance != 0) {
            if (smooth) {
                this.smoothScrollOffset(distance);
            } else {
                this.scrollOffset(distance);
            }
        }

        this.startFocusMoveAnim();
    }

    public final void xScrollTo(boolean toNext) {
        if (toNext) {
            if (this.lastItemVisible()) {
                return;
            }
        } else if (this.firstVisible()) {
            return;
        }

        TvRecyclerView.XSmoothScroller smoothScroller = this.xSmoothScroller;
        if (smoothScroller == null) {
            this.stopScroll();
            Context context = this.getContext();
            int pos;
            if (toNext) {
                pos = 1;
            } else {
                pos = -1;
            }

            smoothScroller = new TvRecyclerView.XSmoothScroller(context, pos);
            this.getLayoutManager().startSmoothScroll(smoothScroller);
            if (smoothScroller.isRunning()) {
                this.xSmoothScroller = smoothScroller;
            }
        } else if (toNext) {
            smoothScroller.targetPositionIncrease();
        } else {
            smoothScroller.targetPositionDecrease();
        }

    }

    public final int getDefaultModeDistance(View view) {
        boolean visible = this.isViewVisible(view);
        Log.d(TAG, "view可见性:" + visible);
        int distance;
        if (!this.viewPartVisible(view) && visible) {
            distance = 0;
        } else {
            distance = this.getViewDistance(view);
        }
        Log.d(TAG, "getDefaultModeDistance:" + distance);
        return distance;
    }

    public Drawable getFocusDrawable() {
        return this.mFocusDrawable;
    }

    public final boolean notAllowOverStepBorder(int keyCode) {
        if (mFocusedView == null) {
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            return this.mOverstepBorderListener.notAllowOverStepBorder(mFocusedView, this.getChildViewHolder(mFocusedView), 0);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            return this.mOverstepBorderListener.notAllowOverStepBorder(mFocusedView, this.getChildViewHolder(mFocusedView), 2);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            return this.mOverstepBorderListener.notAllowOverStepBorder(mFocusedView, this.getChildViewHolder(mFocusedView), 1);
        } else {
            return keyCode == KeyEvent.KEYCODE_DPAD_DOWN ? this.mOverstepBorderListener.notAllowOverStepBorder(mFocusedView,
                    this.getChildViewHolder(mFocusedView), 3) : false;
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


    public final boolean handleKeyCode(int keyCode) {
        if (!this.mAutoProcessFocus) {
            return false;
        } else {
            int direction = this.getDirectionByKeyCode(keyCode, mOrientation);
            Log.d(TAG, "handleKeyCode:keyCode:" + keyCode + ",direction:" + direction);
            if (direction == 1) {
                if (!this.lastItemVisible()) {
                    this.xScrollTo(true);
                    return true;
                } else {
                    return this.notAllowOverStepBorder(keyCode);
                }
            } else if (direction == 0) {
                if (!this.firstVisible()) {
                    this.xScrollTo(false);
                    return true;
                } else {
                    return this.notAllowOverStepBorder(keyCode);
                }
            } else if (direction == 2) {
                return this.notAllowOverStepBorder(keyCode);
            } else {
                return direction == 3 ? this.notAllowOverStepBorder(keyCode) : false;
            }
        }
    }

    @Override
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.startFocusMoveAnim) {
                this.mScroller.getCurrX();
            }
            this.postInvalidate();
        } else if (this.startFocusMoveAnim) {
            this.startFocusMoveAnim = false;
            this.setFocusedView(this.mNextFocused);
            this.setLayerType(this.mLayerType, (Paint) null);
            this.postInvalidate();
            if (onItemStateListener != null) {
                onItemStateListener.onItemFocus(true, this.mFocusedView, this.mSelectedPosition);
            }
        }

    }

    public final int getPaddingStart() {
        return this.mOrientation == HORIZONTAL ? this.getPaddingLeft() : this.getPaddingTop();
    }

    public final int getChildViewStartEdge(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int var3;
        int var4;
        if (this.mOrientation == VERTICAL) {
            var3 = this.getLayoutManager().getDecoratedTop(view);
            var4 = layoutParams.topMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedLeft(view);
            var4 = layoutParams.leftMargin;
        }
        return var3 - var4;
    }

    public final int getChildViewEndEdge(View view) {
        LayoutParams var2 = (LayoutParams) view.getLayoutParams();
        int var3;
        int var4;
        if (this.mOrientation == VERTICAL) {
            var3 = this.getLayoutManager().getDecoratedBottom(view);
            var4 = var2.bottomMargin;
        } else {
            var3 = this.getLayoutManager().getDecoratedRight(view);
            var4 = var2.rightMargin;
        }

        return var3 + var4;
    }

    public final void handleScrollState(int keyCode) {
        if (mOnScrollStateListener != null) {
            if (this.mOrientation == HORIZONTAL) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                    mOnScrollStateListener.scrollToNext(this.mFocusedView);
                } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                    mOnScrollStateListener.scrollToPrev(this.mFocusedView);
                }
            } else if (this.mOrientation == VERTICAL) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                    mOnScrollStateListener.scrollToNext(this.mFocusedView);
                } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                    mOnScrollStateListener.scrollToPrev(this.mFocusedView);
                }
            }
        }
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mFocusBorderView != null && mFocusBorderView.getTvRecyclerView() != null) {
            if (IS_DEBUG) {
                //Log.d("TvRecyclerView", "dispatchDraw: Border view invalidate.");
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

    public int getSelectPosition() {
        return this.mSelectedPosition;
    }

    public final int getCenterModeDistance(View view) {
        int offset;
        if (this.isBelowCenterPosition(view)) {
            offset = this.getViewDistance(view);
        } else {
            offset = 0;
        }

        return offset;
    }

    public final boolean processMoves(int keyCode) {
        View next_focused = this.mNextFocused;
        Log.d(TAG, "processMoves,mNextFocused is null:" + (next_focused == null));
        if (next_focused == null) {
            boolean handled = handleKeyCode(keyCode);
            Log.d(TAG, "processMoves,handled:" + handled);
            if (handled) {
                return true;
            } else {
                if (this.mAutoProcessFocus) {
                    this.handleScrollState(keyCode);
                    this.startFocusMoveAnim = false;
                }

                if (IS_DEBUG) {
                    Log.d("TvRecyclerView", "processMoves: error");
                }

                return false;
            }
        } else {
            if (this.startFocusMoveAnim) {
                this.setFocusedView(next_focused);
            }

            this.scrollDistance(this.mNextFocused, true);
            return true;
        }
    }

    public float getScale() {
        return this.mFocusScale;
    }

    public final int calcDistance(View focused) {
        int mode = this.scrollMode;
        int distance = 0;
        if (mode == 0) {
            return this.getDefaultModeDistance(focused);
        } else if (mode == 1) {
            distance = this.getCenterModeDistance(focused);
        } else if (mode == 2) {
            return this.g(focused);
        } else {
            throw new IllegalArgumentException("not support ScrollMode:" + scrollMode);
        }
        return distance;
    }

    public final void scrollOffset(int offset) {
        if (this.mOrientation == HORIZONTAL) {
            this.scrollBy(offset, 0);
        } else {
            this.scrollBy(0, offset);
        }

    }

    @Override
    public View focusSearch(View focused, int direction) {
        this.mDirection = direction;
        return super.focusSearch(focused, direction);
    }

    public final int g(View view) {
        int var3 = 0;
        if (!mAutoProcessFocus) {
            return 0;
        } else {
            boolean partVisible = this.isViewVisible(view);
            if (this.viewPartVisible(view) || !partVisible) {
                var3 = this.h(view);
            }
            return var3;
        }
    }

    public View getFocusedView() {
        return this.mFocusedView;
    }

    public final void smoothScrollOffset(int offset) {
        if (this.mOrientation == HORIZONTAL) {
            this.smoothScrollBy(offset, 0);
        } else {
            this.smoothScrollBy(0, offset);
        }

    }

    @Override
    public int getChildDrawingOrder(int childCount, int i) {
        int index = this.indexOfChild(this.mFocusedView);
        if (index < 0) {
            return i;
        } else if (i < index) {
            return i;
        } else {
            int targetIndex = index;
            if (i < childCount - 1) {
                targetIndex = index + childCount - 1 - i;
            }

            return targetIndex;
        }
    }

    public final int h(View view) {
        int startEdge = this.getChildViewStartEdge(view);
        int endEdge = this.getChildViewEndEdge(view);
        int paddingStart = this.getPaddingStart();
        int size = this.getTvRecyclerViewRealSize() + paddingStart - 45;
        Log.d(TAG, "startEdge:" + startEdge + ",endEdge:" + endEdge + ",paddingStart:" + paddingStart + ",size:" + size);
        View tempView = null;
        if (startEdge >= paddingStart) {
            if (endEdge > size) {
                View var7 = null;
                tempView = view;
                view = var7;
            } else {
                view = null;
            }
        }

        if (view != null) {
            size = this.getChildViewStartEdge(view) - paddingStart - 45;
        } else if (tempView != null) {
            size = this.getChildViewEndEdge(tempView) - size;
        } else {
            size = 0;
        }

        return size;
    }

    public final boolean firstVisible() {
        int itemCount = this.getLayoutManager().getItemCount();
        boolean isVisible = false;
        if (itemCount == 0 || this.findViewHolderForAdapterPosition(0) != null) {
            isVisible = true;
        }

        return isVisible;
    }

    public final int getViewDistance(View view) {
        int direction;
        int viewSize;
        int height;
        if (this.mOrientation == HORIZONTAL) {
            direction = this.mDirection;
            if (direction != -1 && (direction == FOCUS_UP || direction == FOCUS_DOWN)) {
                return 0;
            }

            viewSize = view.getLeft() + view.getWidth() / 2;
            height = this.getTvRecyclerViewRealSize() / 2;
        } else {
            direction = this.mDirection;
            if (direction != -1 && (direction == FOCUS_LEFT || direction == FOCUS_RIGHT)) {
                return 0;
            }

            viewSize = view.getTop() + view.getHeight() / 2;
            height = this.getTvRecyclerViewRealSize() / 2;
        }
        Log.d(TAG, "viewSize:" + viewSize + ",half height:" + height + ",clac result:" + (viewSize - height));
        return viewSize - height;
    }

    public final boolean lastItemVisible() {
        int itemCount = this.getLayoutManager().getItemCount();
        boolean isVisible = true;
        if (itemCount != 0) {
            if (this.findViewHolderForAdapterPosition(itemCount - 1) != null) {
                isVisible = true;
            } else {
                isVisible = false;
            }
        }
        return isVisible;
    }

    @Override
    public boolean isInTouchMode() {
        boolean inTouchMode = super.isInTouchMode();
        boolean result = inTouchMode;
        if (VERSION.SDK_INT == 19) {
            if (this.hasFocus() && !inTouchMode) {
                result = false;
            } else {
                result = true;
            }
        }

        return result;
    }

    public final void init() {
        this.mScroller = new Scroller(this.getContext());
        this.startFocusMoveAnim = false;
        this.keyEnterPressed = false;
        this.mSelectedPosition = 0;
        this.mNextFocused = null;
        this.isOnLayouting = false;
        this.mFocusScale = 1.04F;
        this.mAutoProcessFocus = true;
        this.focusBorderPaddingLeft = 22;
        this.focusBorderPaddingTop = 22;
        this.focusBorderPaddingRight = 22;
        this.focusBorderPaddingBottom = 22;
        this.mOrientation = HORIZONTAL;
    }

    public final boolean viewPartVisible(View view) {
        boolean result = false;
        if (view != null) {
            Rect rect = new Rect();
            boolean partVisible = view.getLocalVisibleRect(rect);
            Log.d(TAG, "j,rect:" + rect.toString());
            Log.d(TAG, "j,view:" + view.getWidth());
            Log.d(TAG, "j,view:" + view.getHeight());
            if (this.mOrientation == HORIZONTAL) {
                if (partVisible) {
                    result = false;
                    if (rect.width() < view.getWidth()) {
                        result = true;
                    }
                }
                Log.d(TAG, "j:" + result);
                return result;
            } else {
                if (partVisible) {
                    result = false;
                    if (rect.height() < view.getHeight()) {
                        result = true;
                    }
                }
                Log.i(TAG, "j:" + result);
                return result;
            }
        }

        return false;


    }

    public final void startFocusMoveAnim() {
        Log.d(TAG, "startFocusMoveAnim: mFocusBorderView is null:" + (mFocusBorderView == null));
        this.mScroller.abortAnimation();
        if (mFocusBorderView != null) {
            mFocusBorderView.stopAnimation();
            this.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            this.startFocusMoveAnim = true;
            if (onItemStateListener != null) {
                onItemStateListener.onItemFocus(false, this.mFocusedView, this.mSelectedPosition);
            }

            this.mScroller.startScroll(0, 0, 100, 100, 200);
            this.invalidate();
        }

    }

    /**
     * 目标view在RecyclerView中间位置之下
     *
     * @param view
     * @return
     */
    public final boolean isBelowCenterPosition(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int viewHeight = this.getTvRecyclerViewRealSize();
        int orientation = this.mOrientation;
        boolean result = false;
        if (orientation == HORIZONTAL) {
            int right = rect.right;
            viewHeight /= 2;
            if (right > viewHeight || rect.left < viewHeight) {
                result = true;
            }
            return result;
        } else {
            int top = rect.top;
            viewHeight /= 2;
            if (top >= viewHeight) {
                if (rect.bottom <= viewHeight) {
                    return false;
                }
            }
            return true;
        }
    }

    public final boolean isViewVisible(View view) {
        return view != null ? view.getLocalVisibleRect(new Rect()) : false;
    }

    public final void setFocusedView(View view) {
        if (view != null) {
            this.mFocusedView = view;
            this.mSelectedPosition = this.getChildAdapterPosition(view);
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
            this.onItemStateListener.onItemFocus(gainFocus, this.mFocusedView, this.mSelectedPosition);
        }

        if (mFocusBorderView != null) {
            mFocusBorderView.setTvRecyclerView(this);
            if (gainFocus) {
                this.mFocusBorderView.bringToFront();
            }
            if (mFocusedView != null) {
                if (gainFocus) {
                    mFocusedView.setSelected(true);
                } else {
                    mFocusedView.setSelected(false);
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
                case KeyEvent.KEYCODE_DPAD_UP:
                case KeyEvent.KEYCODE_DPAD_DOWN:
                case KeyEvent.KEYCODE_DPAD_LEFT:
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (this.processMoves(keyCode)) {
                        return true;
                    }
                    return super.onKeyDown(keyCode, event);
                case KeyEvent.KEYCODE_DPAD_CENTER:
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
                if (mFocusBorderView != null) {
                    mFocusBorderView.startClickAnim();
                }
                this.onItemStateListener.onClickItemView(this.mFocusedView, this.mSelectedPosition);
            }
            this.keyEnterPressed = false;
            if (this.mAutoProcessFocus) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        this.isOnLayouting = true;
        super.onLayout(changed, l, t, r, b);
        Adapter adapter = this.getAdapter();
        if (adapter != null && this.mSelectedPosition >= adapter.getItemCount()) {
            this.mSelectedPosition = adapter.getItemCount() - 1;
        }
        int selectPos = this.mSelectedPosition - this.findFirstVisibleItemPosition();
        if (selectPos < 0) {
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
        if (this.mSelectedPosition < 0) {
            this.mSelectedPosition = getViewAdapterPosition(child);
        }
        Log.d(TAG, "requestChildFocus:" + mSelectedPosition);
        if (this.mAutoProcessFocus) {
            this.requestFocus();
        } else {
            int focusPosition = getViewAdapterPosition(focused);
            Log.d(TAG, "focused child position:" + focusPosition);
            if ((this.mSelectedPosition != focusPosition || this.I) && !this.hasSetItemSelected) {
                this.mSelectedPosition = focusPosition;
                this.mFocusedView = focused;

                int distance = this.calcDistance(focused);
                Log.d(TAG, "distance:" + distance);
                int temp = distance;
                if (this.I) {
                    temp = distance;
                    if (this.scrollMode != 1) {
                        temp = this.getCenterModeDistance(focused);
                        Log.d(TAG, "distance2:" + temp);
                    }
                }

                this.I = false;
                if (temp != 0) {
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestChildFocus: scroll distance=");
                        sb.append(temp);
                        Log.d("TvRecyclerView", sb.toString());
                    }

                    this.scrollOffset(temp);
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

    public void setFocusDrawable(Drawable drawable) {
        this.mFocusDrawable = drawable;
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
                if (this.mAutoProcessFocus && !this.startFocusMoveAnim) {
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

    public void setScrollMode(int mode) {
        this.scrollMode = mode;
    }

    public void setSelectPadding(int l, int t, int r, int b) {
        this.focusBorderPaddingLeft = l;
        this.focusBorderPaddingTop = t;
        this.focusBorderPaddingRight = r;
        this.focusBorderPaddingBottom = b;
        if (mFocusBorderView != null) {
            mFocusBorderView.setSelectPadding(l, t, r, b);
        }

    }

    public void setSelectedScale(float scale) {
        if (scale >= 1.0F) {
            this.mFocusScale = scale;
        }
    }

    public interface OnItemStateListener {
        void onClickItemView(View itemView, int position);

        void onItemFocus(boolean hasFocus, View itemView, int position);
    }

    public interface OverstepBorderListener {
        /**
         * 不允许焦点脱离RecyclerView
         *
         * @param view
         * @param viewHolder
         * @param direction
         * @return true 不允许  false 允许
         */
        boolean notAllowOverStepBorder(View view, ViewHolder viewHolder, int direction);
    }

    private final class XSmoothScroller extends TvSmoothScroller {
        public int mTargetPosition;
        public int defaultPosition = 10;

        public XSmoothScroller(Context context, int pos) {
            super(context);
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
                byte pos;
                if (targetPosition < 0) {
                    pos = -1;
                } else {
                    pos = 1;
                }
                Log.d(TAG, "computeScrollVectorForPosition:" + pos);
                return mOrientation == HORIZONTAL ? new PointF((float) pos, 0.0F) : new PointF(0.0F, (float) pos);
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
        void scrollToPrev(View view);

        void scrollToNext(View view);
    }
}

