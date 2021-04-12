package com.home.tv.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.recyclerview.widget.RecyclerView.LayoutManager;

public class FocusBorderView extends View {
    private final static String TAG = "FocusBorderView";
    private final static boolean IS_DEBUG = false;
    
    public TvRecyclerView tvRecyclerView;
    public final Scroller scroller;
    public boolean getFocusAnim;
    public boolean isClicked;
    public int e;
    public int f;
    public int g;
    public int q;
    public View r = null;

    public FocusBorderView(Context var1) {
        super(var1);
        this.scroller = new Scroller(var1);
        this.getFocusAnim = false;
        this.isClicked = false;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.q = 0;
    }

    public void stopAnimation() {
        this.scroller.abortAnimation();
    }

    public final void drawFocus(Canvas var1) {
        if (!this.getFocusAnim) {
            if (!tvRecyclerView.startFocusMoveAnim && !this.isClicked) {
                final View var18 = tvRecyclerView.getFocusedView();
                boolean var3;
                if (var18 == this.r) {
                    var3 = false;
                } else {
                    var3 = true;
                }

                if (var18 != null) {
                    int[] var4 = new int[2];
                    var18.getLocationInWindow(var4);
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ===itemLocationX===");
                        sb.append(var4[0]);
                        sb.append("===itemLocationY==");
                        sb.append(var4[1]);
                        Log.i(TAG, sb.toString());
                    }
                    int var6 = var18.getWidth();
                    int var7 = var18.getHeight();
                    float var8 = this.tvRecyclerView.mFocusScale;
                    LayoutManager var22 = this.tvRecyclerView.getLayoutManager();
                    byte var9 = -1;
                    int var10;

                    var10 = -1;


                    int var11 = var9;
                    if (this.tvRecyclerView.getLayoutManager() != null) {
                        var11 = var9;
                    }

                    float var12 = var8;
                    if (var10 > 0) {
                        var12 = var8;
                        if (var11 > 0) {
                            var12 = Math.min((float) var11 * 1.4F / (float) var6, (float) var10 * 1.4F / (float) var7) + 1.0F;
                        }
                    }

                    var8 = (float) var4[0];
                    float var13 = (float) var4[1];
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ======itemPositionX=====");
                        sb.append(var8);
                        sb.append("===itemPositionY===");
                        sb.append(var13);
                        Log.i(TAG, sb.toString());
                    }
                    Drawable var20 = this.tvRecyclerView.getFocusDrawable();
                    var11 = this.e;
                    var10 = this.g;
                    int var14 = this.f;
                    int var24 = this.q;
                    float drawPositionX = var8 - (float) var11;
                    float drawPositionY = var13 - (float) var14;

                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ===drawPositionX==");
                        sb.append(drawPositionX);
                        sb.append("===drawPositionY===");
                        sb.append(drawPositionY);
                        Log.i(TAG, sb.toString());
                    }

                    if (var20 != null) {
                        var1.save();
                        var1.translate(drawPositionX, drawPositionY);
                        var20.setBounds(0, 0, var6 + var11 + var10, var7 + var14 + var24);
                        var20.draw(var1);
                        var1.restore();
                    }

                    var1.save();
                    var1.translate(var8, var13);
                    var18.draw(var1);
                    var1.restore();
                    this.setPivotX(var8 + (float) (var6 / 2));
                    this.setPivotY(var13 + (float) (var7 / 2));
                    if (var3) {
                        AnimatorSet var17 = new AnimatorSet();
                        var8 = (var12 - 1.0F) * 1.5F + 1.0F;
                        ObjectAnimator var21 = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0F, var8, var12});
                        ObjectAnimator var23 = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0F, var8, var12});
                        var17.setDuration(350L);
                        var17.setInterpolator(new DecelerateInterpolator());
                        var17.play(var21).with(var23);
                        var17.start();
                        var17.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationCancel(Animator var1) {
                                super.onAnimationCancel(var1);
                            }

                            public void onAnimationEnd(Animator var1) {
                                super.onAnimationEnd(var1);
                                FocusBorderView.this.r = var18;
                            }
                        });
                    }
                }
            }
        }

    }

    public void resetFocusAnim() {
        this.getFocusAnim = false;
    }

    public final void drawFocusMoveAnim(Canvas canvas) {
        if (this.tvRecyclerView.startFocusMoveAnim && IS_DEBUG) {
            Log.d(TAG, "drawFocusMoveAnim: ==============");
        }

    }

    public TvRecyclerView getTvRecyclerView() {
        return this.tvRecyclerView;
    }

    public final void drawGetFocusOrClickScaleAnim(Canvas canvas) {
        if (this.isClicked) {
            if (IS_DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("drawGetFocusOrClickScaleAnim: ==isClicked=");
                sb.append(this.isClicked);
                sb.append("=GetFocusAnim=");
                sb.append(this.getFocusAnim);
                Log.d(TAG, sb.toString());
            }

            View var3 = this.tvRecyclerView.getFocusedView();
            if (var3 == null) {
                return;
            }

            int var4 = var3.getWidth();
            int var5 = var3.getHeight();
            int[] var6 = new int[2];
            var3.getLocationInWindow(var6);
            this.getLocationInWindow(new int[2]);
            Drawable focusDrawable = this.tvRecyclerView.getFocusDrawable();
            if (focusDrawable != null) {
                int var7 = this.e;
                int var8 = this.g;
                int var9 = this.f;
                int var10 = this.q;
                canvas.save();
                canvas.translate((float) (var6[0] - this.e), (float) (var6[1] - this.f));
                focusDrawable.setBounds(0, 0, var4 + var7 + var8, var5 + var9 + var10);
                focusDrawable.draw(canvas);
                canvas.restore();
            }

            canvas.save();
            canvas.translate((float) var6[0], (float) var6[1]);
            var3.draw(canvas);
            canvas.restore();
        }

    }

    @Override
    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            this.tvRecyclerView.getScale();
            if (this.getFocusAnim) {
                this.scroller.getCurrX();
                this.scroller.getCurrY();
            } else if (this.isClicked) {
                this.scroller.getCurrX();
                this.scroller.getCurrY();
            }

            this.invalidate();
        } else {
            if (this.getFocusAnim) {
                this.getFocusAnim = false;
                if (tvRecyclerView != null) {
                    tvRecyclerView.setLayerType(tvRecyclerView.mLayerType, (Paint) null);
                    this.invalidate();
                }
            } else if (this.isClicked) {
                this.isClicked = false;
                if (tvRecyclerView != null) {
                    tvRecyclerView.setLayerType(tvRecyclerView.mLayerType, (Paint) null);
                    this.invalidate();
                }
            }
        }

    }

    public void startClickAnim() {
        TvRecyclerView var1 = this.tvRecyclerView;
        if (var1 != null) {
            var1.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            int var2 = this.tvRecyclerView.getSelectPosition();
            View var3;
            if (var2 >= 0 && var2 < this.tvRecyclerView.getAdapter().getItemCount()) {
                var3 = this.tvRecyclerView.getFocusedView();
            } else {
                var3 = null;
            }

            if (var3 != null) {
                if (IS_DEBUG) {
                    Log.d(TAG, "startClickAnim: start click animation");
                }

                this.isClicked = true;
                this.r = null;
                this.scroller.abortAnimation();
                this.scroller.startScroll(0, 0, 100, 100, 200);
                this.invalidate();
            }
        }

    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        TvRecyclerView var2 = this.tvRecyclerView;
        if (var2 != null && var2.hasFocus()) {
            this.drawGetFocusOrClickScaleAnim(canvas);
            this.drawFocusMoveAnim(canvas);
            this.drawFocus(canvas);
        }

    }

    public void startFocusAnim() {
        TvRecyclerView var1 = this.tvRecyclerView;
        if (var1 != null) {
            var1.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            if (this.tvRecyclerView.getFocusedView() != null) {
                if (IS_DEBUG) {
                    Log.d(TAG, "startFocusAnim: start focus animation");
                }

                this.getFocusAnim = true;
                this.r = null;
                this.scroller.abortAnimation();
                this.scroller.startScroll(0, 0, 100, 100, 245);
                this.invalidate();
            }
        }

    }

    public void setSelectPadding(int var1, int var2, int var3, int var4) {
        this.e = var1;
        this.f = var2;
        this.g = var3;
        this.q = var4;
    }

    public void setTvRecyclerView(TvRecyclerView var1) {
        if (this.tvRecyclerView == null) {
            this.tvRecyclerView = var1;
        }

    }
}

