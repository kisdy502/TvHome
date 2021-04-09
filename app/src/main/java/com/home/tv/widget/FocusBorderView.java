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
    public TvRecyclerView tvRecyclerView;
    public final Scroller scroller;
    public boolean c;
    public boolean d;
    public int e;
    public int f;
    public int g;
    public int q;
    public View r = null;

    public FocusBorderView(Context var1) {
        super(var1);
        this.scroller = new Scroller(var1);
        this.c = false;
        this.d = false;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.q = 0;
    }

    public void stopAnimation() {
        this.scroller.abortAnimation();
    }

    public final void drawFocus(Canvas var1) {
        if (!this.c) {
            TvRecyclerView var2 = this.tvRecyclerView;
            if (!var2.c && !this.d) {
                final View var18 = var2.g();
                boolean var3;
                if (var18 == this.r) {
                    var3 = false;
                } else {
                    var3 = true;
                }

                if (var18 != null) {
                    int[] var4 = new int[2];
                    var18.getLocationInWindow(var4);
                    StringBuilder var5 = new StringBuilder();
                    var5.append("drawFocus: ===itemLocationX===");
                    var5.append(var4[0]);
                    var5.append("===itemLocationY==");
                    var5.append(var4[1]);
                    Log.i("TvRecyclerView.FB", var5.toString());
                    int var6 = var18.getWidth();
                    int var7 = var18.getHeight();
                    float var8 = this.tvRecyclerView.d;
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
                            var12 = Math.min((float)var11 * 1.4F / (float)var6, (float)var10 * 1.4F / (float)var7) + 1.0F;
                        }
                    }

                    var8 = (float)var4[0];
                    float var13 = (float)var4[1];
                    StringBuilder var19 = new StringBuilder();
                    var19.append("drawFocus: ======itemPositionX=====");
                    var19.append(var8);
                    var19.append("===itemPositionY===");
                    var19.append(var13);
                    Log.i("TvRecyclerView.FB", var19.toString());
                    Drawable var20 = this.tvRecyclerView.b;
                    var11 = this.e;
                    var10 = this.g;
                    int var14 = this.f;
                    int var24 = this.q;
                    float var15 = var8 - (float)var11;
                    float var16 = var13 - (float)var14;
                    var5 = new StringBuilder();
                    var5.append("drawFocus: ===drawPositionX==");
                    var5.append(var15);
                    var5.append("===drawPositionY===");
                    var5.append(var16);
                    Log.i("TvRecyclerView.FB", var5.toString());
                    if (var20 != null) {
                        var1.save();
                        var1.translate(var15, var16);
                        var20.setBounds(0, 0, var6 + var11 + var10, var7 + var14 + var24);
                        var20.draw(var1);
                        var1.restore();
                    }

                    var1.save();
                    var1.translate(var8, var13);
                    var18.draw(var1);
                    var1.restore();
                    this.setPivotX(var8 + (float)(var6 / 2));
                    this.setPivotY(var13 + (float)(var7 / 2));
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

    public void b() {
        this.c = false;
    }

    public final void drawFocusMoveAnim(Canvas var1) {
        if (this.tvRecyclerView.c && TvRecyclerView.IS_DEBUG) {
            Log.d("TvRecyclerView.FB", "drawFocusMoveAnim: ==============");
        }

    }

    public TvRecyclerView c() {
        return this.tvRecyclerView;
    }

    public final void drawGetFocusOrClickScaleAnim(Canvas var1) {
        if (this.d) {
            if (TvRecyclerView.IS_DEBUG) {
                StringBuilder var2 = new StringBuilder();
                var2.append("drawGetFocusOrClickScaleAnim: ==isClicked=");
                var2.append(this.d);
                var2.append("=GetFocusAnim=");
                var2.append(this.c);
                Log.d("TvRecyclerView.FB", var2.toString());
            }

            View var3 = this.tvRecyclerView.f;
            if (var3 == null) {
                return;
            }

            int var4 = var3.getWidth();
            int var5 = var3.getHeight();
            int[] var6 = new int[2];
            var3.getLocationInWindow(var6);
            this.getLocationInWindow(new int[2]);
            Drawable var11 = this.tvRecyclerView.b();
            if (var11 != null) {
                int var7 = this.e;
                int var8 = this.g;
                int var9 = this.f;
                int var10 = this.q;
                var1.save();
                var1.translate((float)(var6[0] - this.e), (float)(var6[1] - this.f));
                var11.setBounds(0, 0, var4 + var7 + var8, var5 + var9 + var10);
                var11.draw(var1);
                var1.restore();
            }

            var1.save();
            var1.translate((float)var6[0], (float)var6[1]);
            var3.draw(var1);
            var1.restore();
        }

    }

    public void computeScroll() {
        if (this.scroller.computeScrollOffset()) {
            this.tvRecyclerView.f();
            if (this.c) {
                this.scroller.getCurrX();
                this.scroller.getCurrY();
            } else if (this.d) {
                this.scroller.getCurrX();
                this.scroller.getCurrY();
            }

            this.invalidate();
        } else {
            TvRecyclerView var1;
            if (this.c) {
                this.c = false;
                var1 = this.tvRecyclerView;
                if (var1 != null) {
                    var1.setLayerType(var1.J, (Paint)null);
                    this.invalidate();
                }
            } else if (this.d) {
                this.d = false;
                var1 = this.tvRecyclerView;
                if (var1 != null) {
                    var1.setLayerType(var1.J, (Paint)null);
                    this.invalidate();
                }
            }
        }

    }

    public void d() {
        TvRecyclerView var1 = this.tvRecyclerView;
        if (var1 != null) {
            var1.setLayerType(LAYER_TYPE_NONE, (Paint)null);
            int var2 = this.tvRecyclerView.e();
            View var3;
            if (var2 >= 0 && var2 < this.tvRecyclerView.getAdapter().getItemCount()) {
                var3 = this.tvRecyclerView.g();
            } else {
                var3 = null;
            }

            if (var3 != null) {
                if (TvRecyclerView.IS_DEBUG) {
                    Log.d("TvRecyclerView.FB", "startClickAnim: start click animation");
                }

                this.d = true;
                this.r = null;
                this.scroller.abortAnimation();
                this.scroller.startScroll(0, 0, 100, 100, 200);
                this.invalidate();
            }
        }

    }

    @Override
    public void dispatchDraw(Canvas var1) {
        super.dispatchDraw(var1);
        TvRecyclerView var2 = this.tvRecyclerView;
        if (var2 != null && var2.hasFocus()) {
            this.drawGetFocusOrClickScaleAnim(var1);
            this.drawFocusMoveAnim(var1);
            this.drawFocus(var1);
        }

    }

    public void e() {
        TvRecyclerView var1 = this.tvRecyclerView;
        if (var1 != null) {
            var1.setLayerType(LAYER_TYPE_NONE, (Paint)null);
            if (this.tvRecyclerView.g() != null) {
                if (TvRecyclerView.IS_DEBUG) {
                    Log.d("TvRecyclerView.FB", "startFocusAnim: start focus animation");
                }

                this.c = true;
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

