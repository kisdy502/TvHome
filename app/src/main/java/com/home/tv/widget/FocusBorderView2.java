//package com.home.tv.widget;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.DecelerateInterpolator;
//import android.widget.Scroller;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class FocusBorderView2 extends View {
//    private final static String TAG = "FocusBorderView";
//
//    public TvRecyclerView tvRecyclerView;
//    public final Scroller scroller;
//    public boolean c;
//    public boolean isClicked;
//    public int e;
//    public int f;
//    public int g;
//    public int q;
//    public View targetDrawView = null;
//
//    public FocusBorderView2(Context context) {
//        super(context);
//        this.scroller = new Scroller(context);
//        this.c = false;
//        this.isClicked = false;
//        this.e = 0;
//        this.f = 0;
//        this.g = 0;
//        this.q = 0;
//    }
//
//    public void abortAnimation() {
//        this.scroller.abortAnimation();
//    }
//
//    public final void drawFocus(Canvas canvas) {
//        if (!this.c) {
//            TvRecyclerView var2 = this.tvRecyclerView;
//            if (!var2.c && !this.isClicked) {
//                final View focusView = var2.getFocusView();
//                boolean needMove;
//                if (focusView == this.targetDrawView) {
//                    needMove = false;
//                } else {
//                    needMove = true;
//                }
//
//                if (focusView != null) {
//                    int[] location = new int[2];
//                    focusView.getLocationInWindow(location);
//
//                    StringBuilder var5 = new StringBuilder();
//                    var5.append("drawFocus: ===itemLocationX===");
//                    var5.append(location[0]);
//                    var5.append("===itemLocationY==");
//                    var5.append(location[1]);
//                    Log.i(TAG, var5.toString());
//
//                    int var6 = focusView.getWidth();
//                    int var7 = focusView.getHeight();
//                    float var8 = this.tvRecyclerView.getScale();
//                    RecyclerView.LayoutManager var22 = this.tvRecyclerView.getLayoutManager();
//                    byte var9 = -1;
//                    int var10;
//                    var10 = -1;
//
//                    int var11 = var9;
//                    if (this.tvRecyclerView.getLayoutManager() != null) {
//                        var11 = var9;
//                    }
//
//                    float var12 = var8;
//                    if (var10 > 0) {
//                        var12 = var8;
//                        if (var11 > 0) {
//                            var12 = Math.min((float) var11 * 1.4F / (float) var6, (float) var10 * 1.4F / (float) var7) + 1.0F;
//                        }
//                    }
//
//                    var8 = (float) location[0];
//                    float var13 = (float) location[1];
//
//                    StringBuilder var19 = new StringBuilder();
//                    var19.append("drawFocus: ======itemPositionX=====");
//                    var19.append(var8);
//                    var19.append("===itemPositionY===");
//                    var19.append(var13);
//                    Log.i(TAG, var19.toString());
//
//                    Drawable drawable = this.tvRecyclerView.getFocusDrawable();
//
//                    var11 = this.e;
//                    var10 = this.g;
//                    int var14 = this.f;
//                    int var24 = this.q;
//                    float var15 = var8 - (float) var11;
//                    float var16 = var13 - (float) var14;
//
//                    var5 = new StringBuilder();
//                    var5.append("drawFocus: ===drawPositionX==");
//                    var5.append(var15);
//                    var5.append("===drawPositionY===");
//                    var5.append(var16);
//                    Log.i(TAG, var5.toString());
//
//                    if (drawable != null) {
//                        canvas.save();
//                        canvas.translate(var15, var16);
//                        drawable.setBounds(0, 0, var6 + var11 + var10, var7 + var14 + var24);
//                        drawable.draw(canvas);
//                        canvas.restore();
//                    }
//
//                    canvas.save();
//                    canvas.translate(var8, var13);
//                    focusView.draw(canvas);
//                    canvas.restore();
//                    this.setPivotX(var8 + (float) (var6 / 2));
//                    this.setPivotY(var13 + (float) (var7 / 2));
//                    if (needMove) {
//                        AnimatorSet animatorSet = new AnimatorSet();
//                        var8 = (var12 - 1.0F) * 1.5F + 1.0F;
//                        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0F, var8, var12});
//                        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0F, var8, var12});
//                        animatorSet.setDuration(350L);
//                        animatorSet.setInterpolator(new DecelerateInterpolator());
//                        animatorSet.play(objectAnimatorX).with(objectAnimatorY);
//                        animatorSet.start();
//                        animatorSet.addListener(new AnimatorListenerAdapter() {
//                            public void onAnimationCancel(Animator animator) {
//                                super.onAnimationCancel(animator);
//                            }
//
//                            public void onAnimationEnd(Animator animator) {
//                                super.onAnimationEnd(animator);
//                                FocusBorderView2.this.targetDrawView = focusView;
//                            }
//                        });
//                    }
//                }
//            }
//        }
//
//    }
//
//    public void b() {
//        this.c = false;
//    }
//
//    public final void drawFocusMoveAnim(Canvas canvas) {
//        if (this.tvRecyclerView.c && TvRecyclerView.isDebug) {
//            Log.d(TAG, "drawFocusMoveAnim: ==============");
//        }
//
//    }
//
//    public TvRecyclerView getTvRecyclerView() {
//        return this.tvRecyclerView;
//    }
//
//    public final void drawGetFocusOrClickScaleAnim(Canvas canvas) {
//        if (this.isClicked) {
//            if (TvRecyclerView.isDebug) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("drawGetFocusOrClickScaleAnim: ==isClicked=");
//                sb.append(this.isClicked);
//                sb.append("=GetFocusAnim=");
//                sb.append(this.c);
//                Log.d(TAG, sb.toString());
//            }
//
//            View focusView = this.tvRecyclerView.getFocusView();
//            if (focusView == null) {
//                return;
//            }
//
//            int var4 = focusView.getWidth();
//            int var5 = focusView.getHeight();
//            int[] var6 = new int[2];
//            focusView.getLocationInWindow(var6);
//            this.getLocationInWindow(new int[2]);
//            Drawable drawable = this.tvRecyclerView.getFocusDrawable();
//            if (drawable != null) {
//                int var7 = this.e;
//                int var8 = this.g;
//                int var9 = this.f;
//                int var10 = this.q;
//                canvas.save();
//                canvas.translate((float) (var6[0] - this.e), (float) (var6[1] - this.f));
//                drawable.setBounds(0, 0, var4 + var7 + var8, var5 + var9 + var10);
//                drawable.draw(canvas);
//                canvas.restore();
//            }
//
//            canvas.save();
//            canvas.translate((float) var6[0], (float) var6[1]);
//            focusView.draw(canvas);
//            canvas.restore();
//        }
//
//    }
//
//    @Override
//    public void computeScroll() {
//        if (this.scroller.computeScrollOffset()) {
//            this.tvRecyclerView.getScale();
//            if (this.c) {
//                this.scroller.getCurrX();
//                this.scroller.getCurrY();
//            } else if (this.isClicked) {
//                this.scroller.getCurrX();
//                this.scroller.getCurrY();
//            }
//            this.invalidate();
//        } else {
//            TvRecyclerView var1;
//            if (this.c) {
//                this.c = false;
//                var1 = this.tvRecyclerView;
//                if (var1 != null) {
//                    var1.setLayerType(var1.layerType, (Paint) null);
//                    this.invalidate();
//                }
//            } else if (this.isClicked) {
//                this.isClicked = false;
//                var1 = this.tvRecyclerView;
//                if (var1 != null) {
//                    var1.setLayerType(var1.layerType, (Paint) null);
//                    this.invalidate();
//                }
//            }
//        }
//
//    }
//
//    public void startClickAnim() {
//        if (tvRecyclerView != null) {
//            tvRecyclerView.setLayerType(LAYER_TYPE_NONE, (Paint) null);
//            int selectPosition = this.tvRecyclerView.getSelectPosition();
//            View focusView;
//            if (selectPosition >= 0 && selectPosition < this.tvRecyclerView.getAdapter().getItemCount()) {
//                focusView = this.tvRecyclerView.getFocusView();
//            } else {
//                focusView = null;
//            }
//
//            if (focusView != null) {
//                if (TvRecyclerView.isDebug) {
//                    Log.d(TAG, "startClickAnim: start click animation");
//                }
//                this.isClicked = true;
//                this.targetDrawView = null;
//                this.scroller.abortAnimation();
//                this.scroller.startScroll(0, 0, 100, 100, 200);
//                this.invalidate();
//            }
//        }
//
//    }
//
//    @Override
//    public void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//        if (tvRecyclerView != null && tvRecyclerView.hasFocus()) {
//            this.drawGetFocusOrClickScaleAnim(canvas);
//            this.drawFocusMoveAnim(canvas);
//            this.drawFocus(canvas);
//        }
//    }
//
//    public void startFocusAnim() {
//        TvRecyclerView var1 = this.tvRecyclerView;
//        if (var1 != null) {
//            var1.setLayerType(LAYER_TYPE_NONE, (Paint) null);
//            if (this.tvRecyclerView.getFocusView() != null) {
//                if (TvRecyclerView.isDebug) {
//                    Log.d(TAG, "startFocusAnim: start focus animation");
//                }
//
//                this.c = true;
//                this.targetDrawView = null;
//                this.scroller.abortAnimation();
//                this.scroller.startScroll(0, 0, 100, 100, 245);
//                this.invalidate();
//            }
//        }
//
//    }
//
//    public void setSelectPadding(int left, int top, int right, int bottom) {
//        this.e = left;
//        this.f = top;
//        this.g = right;
//        this.q = bottom;
//    }
//
//    public void setTvRecyclerView(TvRecyclerView tvRecyclerView) {
//        if (this.tvRecyclerView == null) {
//            this.tvRecyclerView = tvRecyclerView;
//        }
//
//    }
//}
