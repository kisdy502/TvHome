package com.home.tv.widget;


import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.recyclerview.widget.RecyclerView;


public class TvSmoothScroller extends RecyclerView.SmoothScroller {
    public static final String TAG = "TvSmoothScroller";
    public static final boolean DEBUG = false;

    public static final float MILLISECONDS_PER_INCH = 25.0F;

    public static final int SNAP_TO_ANY = 0;

    public static final int SNAP_TO_END = 1;

    public static final int SNAP_TO_START = -1;

    public static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2F;

    public static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;

    public final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();

    public final DisplayMetrics mDisplayMetrics;

    public boolean mHasCalculatedMillisPerPixel = false;

    public int mInterimTargetDx = 0;

    public int mInterimTargetDy = 0;

    public final LinearInterpolator mLinearInterpolator = new LinearInterpolator();

    public float mMillisPerPixel;

    public PointF mTargetVector;

    public TvSmoothScroller(Context context) {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    private int clampApplyScroll(int paramInt1, int paramInt2) {
        paramInt2 = paramInt1 - paramInt2;
        return (paramInt1 * paramInt2 <= 0) ? 0 : paramInt2;
    }

    private float getSpeedPerPixel() {
        if (!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return this.mMillisPerPixel;
    }

    public int calculateDtToFit(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        if (paramInt5 != -1) {
            if (paramInt5 != 0) {
                if (paramInt5 == 1)
                    return paramInt4 - paramInt2;
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
            }
            paramInt1 = paramInt3 - paramInt1;
            if (paramInt1 > 0)
                return paramInt1;
            paramInt1 = paramInt4 - paramInt2;
            return (paramInt1 < 0) ? paramInt1 : 0;
        }
        return paramInt3 - paramInt1;
    }

    public int calculateDxToMakeVisible(View paramView, int paramInt) {
        RecyclerView.LayoutManager o = getLayoutManager();
        if (o == null || !o.canScrollHorizontally())
            return 0;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) paramView.getLayoutParams();
        return calculateDtToFit(o.getDecoratedLeft(paramView) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin,
                o.getDecoratedRight(paramView) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin,
                o.getPaddingLeft(), o.getWidth() - o.getPaddingRight(), paramInt);
    }

    public int calculateDyToMakeVisible(View paramView, int paramInt) {
        RecyclerView.LayoutManager o = getLayoutManager();
        if (o == null || !o.canScrollVertically())
            return 0;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) paramView.getLayoutParams();
        return calculateDtToFit(o.getDecoratedTop(paramView) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, o.getDecoratedBottom(paramView) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, o.getPaddingTop(), o.getHeight() - o.getPaddingBottom(), paramInt);
    }

    public float calculateSpeedPerPixel(DisplayMetrics paramDisplayMetrics) {
        return 25.0F / paramDisplayMetrics.densityDpi;
    }

    public int calculateTimeForDeceleration(int paramInt) {
        double d = calculateTimeForScrolling(paramInt);
        Double.isNaN(d);
        return (int) Math.ceil(d / 0.3356D);
    }

    public int calculateTimeForScrolling(int paramInt) {
        return (int) Math.ceil((Math.abs(paramInt) * getSpeedPerPixel()));
    }

    public int getHorizontalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF != null) {
            byte b;
            float f = pointF.x;
            if (f == 0.0F)
                return 0;
            if (f > 0.0F) {
                b = 1;
            } else {
                b = -1;
            }
            return b;
        }
        return 0;
    }

    public int getVerticalSnapPreference() {
        PointF pointF = this.mTargetVector;
        if (pointF != null) {
            byte b;
            float f = pointF.y;
            if (f == 0.0F)
                return 0;
            if (f > 0.0F) {
                b = 1;
            } else {
                b = -1;
            }
            return b;
        }
        return 0;
    }

    @Override
    public void onSeekTargetStep(int dx, int dy, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getChildCount() == 0) {
            stop();
            return;
        }
        this.mInterimTargetDx = clampApplyScroll(this.mInterimTargetDx, dx);
        dx = clampApplyScroll(this.mInterimTargetDy, dy);
        this.mInterimTargetDy = dx;
        if (this.mInterimTargetDx == 0 && dx == 0)
            updateActionForInterimTarget(action);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }

    @Override
    public void onTargetFound(View paramView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int i = calculateDxToMakeVisible(paramView, getHorizontalSnapPreference());
        int j = calculateDyToMakeVisible(paramView, getVerticalSnapPreference());
        int k = calculateTimeForDeceleration((int) Math.sqrt((i * i + j * j)));
        Log.d(TAG, "calculateDxToMakeVisible:" + i);
        Log.d(TAG, "calculateDyToMakeVisible:" + i);
        Log.d(TAG, "calculateTimeForDeceleration:" + i);
        if (k > 0)
            action.update(-i, -j, k, (Interpolator) this.mDecelerateInterpolator);
    }


    public void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action parama) {
        PointF pointF = computeScrollVectorForPosition(getTargetPosition());
        if (pointF == null || (pointF.x == 0.0F && pointF.y == 0.0F)) {
            parama.jumpTo(getTargetPosition());
            stop();
            return;
        }
        normalize(pointF);
        this.mTargetVector = pointF;
        this.mInterimTargetDx = (int) (pointF.x * 10000.0F);
        this.mInterimTargetDy = (int) (pointF.y * 10000.0F);
        int i = calculateTimeForScrolling(10000);
        parama.update((int) (this.mInterimTargetDx * 1.2F), (int) (this.mInterimTargetDy * 1.2F), (int) (i * 1.2F), (Interpolator) this.mLinearInterpolator);
    }
}
