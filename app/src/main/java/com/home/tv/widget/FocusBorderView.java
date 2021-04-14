package com.home.tv.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.recyclerview.widget.RecyclerView.LayoutManager;

public class FocusBorderView extends View {
    private final static String TAG = "FocusBorderView";
    private final static boolean IS_DEBUG = true;

    public TvRecyclerView tvRecyclerView;
    public Scroller scroller;
    public boolean getFocusAnim;
    public boolean isClicked;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public View targetFocusView = null;

    public FocusBorderView(Context context) {
        super(context);
        this.scroller = new Scroller(context);
        this.getFocusAnim = false;
        this.isClicked = false;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
    }

    public void stopAnimation() {
        this.scroller.abortAnimation();

        if (tvRecyclerView == null) {
            return;
        }
        View focusedView = tvRecyclerView.getFocusedView();
        if (focusedView == null) {
            return;
        }
        final AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(focusedView, "scaleX", new float[]{1.0F});
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(focusedView, "scaleY", new float[]{1.0F});
        animatorSet.setDuration(240L);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });


    }


    public final void drawFocus(final Canvas canvas) {
        if (!this.getFocusAnim) {
            if (!tvRecyclerView.startFocusMoveAnim && !this.isClicked) {
                final View focusedView = tvRecyclerView.getFocusedView();
                boolean isFocusChange;
                if (focusedView == this.targetFocusView) {
                    isFocusChange = false;
                } else {
                    isFocusChange = true;
                }

                if (focusedView != null) {
                    final int[] outLocation = new int[2];
                    focusedView.getLocationInWindow(outLocation);
                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ===itemLocationX===");
                        sb.append(outLocation[0]);
                        sb.append("===itemLocationY==");
                        sb.append(outLocation[1]);
                        Log.i(TAG, sb.toString());
                    }
                    int viewWidth = focusedView.getWidth();
                    int viewHeight = focusedView.getHeight();
                    float scale = this.tvRecyclerView.mFocusScale;
                    LayoutManager layoutManager = this.tvRecyclerView.getLayoutManager();
                    int var10 = -1;
                    int var11 = -1;
                    if (layoutManager != null) {
                        var11 = -1;
                    }

                    float targetScale = scale;
                    if (var10 > 0) {
                        targetScale = scale;
                        if (var11 > 0) {
                            targetScale = Math.min((float) var11 * 1.4F / (float) viewWidth, (float) var10 * 1.4F / (float) viewHeight) + 1.0F;
                        }
                    }


                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ======itemPositionX=====");
                        sb.append(outLocation[0]);
                        sb.append("===itemPositionY===");
                        sb.append(outLocation[1]);
                        Log.i(TAG, sb.toString());
                    }


                    float drawPositionX = outLocation[0] - this.left;
                    float drawPositionY = outLocation[1] - this.top;

                    if (IS_DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("drawFocus: ===drawPositionX==");
                        sb.append(drawPositionX);
                        sb.append("===drawPositionY===");
                        sb.append(drawPositionY);
                        Log.i(TAG, sb.toString());
                    }

                    if (tvRecyclerView.getFocusDrawable() != null) {
                        canvas.save();
                        canvas.translate(drawPositionX, drawPositionY);
                        tvRecyclerView.getFocusDrawable().setBounds(0, 0,
                                viewWidth + this.left + this.right, viewHeight + this.top + this.bottom);
                        tvRecyclerView.getFocusDrawable().draw(canvas);
                        canvas.restore();
                    }

                    canvas.save();
                    canvas.translate(outLocation[0], outLocation[1]);
                    focusedView.draw(canvas);
                    canvas.restore();

                    this.setPivotX(outLocation[0] + (float) (viewWidth / 2));
                    this.setPivotY(outLocation[1] + (float) (viewHeight / 2));

                    if (isFocusChange) {
                        final AnimatorSet animatorSet = new AnimatorSet();
                        scale = (targetScale - 1.0F) * 1.5F + 1.0F;
                        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0F, scale, targetScale});
                        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0F, scale, targetScale});
                        animatorSet.setDuration(240L);
                        animatorSet.setInterpolator(new DecelerateInterpolator());
                        animatorSet.play(scaleX).with(scaleY);
                        animatorSet.start();
                        animatorSet.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationCancel(Animator animator) {
                                super.onAnimationCancel(animator);
                            }

                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                targetFocusView = focusedView;
                            }
                        });

                        //字体未放大的bug
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(focusedView, "scaleX", new float[]{1.0F, scale, targetScale});
                        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(focusedView, "scaleY", new float[]{1.0F, scale, targetScale});
                        animatorSet2.setDuration(240L);
                        animatorSet2.setInterpolator(new DecelerateInterpolator());
                        animatorSet2.play(scaleX2).with(scaleY2);
                        animatorSet2.start();
                        animatorSet2.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationCancel(Animator animator) {
                                super.onAnimationCancel(animator);
                            }

                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
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
            //Log.d(TAG, "drawFocusMoveAnim: ==============");
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

            View focusedView = this.tvRecyclerView.getFocusedView();
            if (focusedView == null) {
                return;
            }

            int width = focusedView.getWidth();
            int height = focusedView.getHeight();
            int[] outLocation = new int[2];
            focusedView.getLocationInWindow(outLocation);
//            this.getLocationInWindow(new int[2]);
            if (tvRecyclerView.getFocusDrawable() != null) {
                canvas.save();
                canvas.translate((float) (outLocation[0] - this.left), (float) (outLocation[1] - this.top));
                tvRecyclerView.getFocusDrawable().setBounds(0, 0, width + this.left + this.right, height + this.top + this.bottom);
                tvRecyclerView.getFocusDrawable().draw(canvas);
                canvas.restore();
            }

            canvas.save();
            canvas.translate((float) outLocation[0], (float) outLocation[1]);
            tvRecyclerView.getFocusDrawable().draw(canvas);
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
        if (tvRecyclerView != null) {
            tvRecyclerView.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            int selectPosition = this.tvRecyclerView.getSelectPosition();
            View focusView;
            if (selectPosition >= 0 && selectPosition < this.tvRecyclerView.getAdapter().getItemCount()) {
                focusView = this.tvRecyclerView.getFocusedView();
            } else {
                focusView = null;
            }

            if (focusView != null) {
                if (IS_DEBUG) {
                    Log.d(TAG, "startClickAnim: start click animation");
                }

                this.isClicked = true;
                this.targetFocusView = null;
                this.scroller.abortAnimation();
                this.scroller.startScroll(0, 0, 100, 100, 200);
                this.invalidate();
            }
        }

    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (tvRecyclerView != null && tvRecyclerView.hasFocus()) {
            this.drawGetFocusOrClickScaleAnim(canvas);
            this.drawFocusMoveAnim(canvas);
            this.drawFocus(canvas);
        }

    }

    public void startFocusAnim() {
        if (tvRecyclerView != null) {
            tvRecyclerView.setLayerType(LAYER_TYPE_NONE, (Paint) null);
            if (this.tvRecyclerView.getFocusedView() != null) {
                if (IS_DEBUG) {
                    Log.d(TAG, "startFocusAnim: start focus animation");
                }

                this.getFocusAnim = true;
                this.targetFocusView = null;
                this.scroller.abortAnimation();
                this.scroller.startScroll(0, 0, 100, 100, 245);
                this.invalidate();
            }
        }

    }

    public void setSelectPadding(int l, int t, int r, int b) {
        this.left = l;
        this.top = t;
        this.right = r;
        this.bottom = b;
    }

    public void setTvRecyclerView(TvRecyclerView tvRecyclerView) {
        if (this.tvRecyclerView == null) {
            this.tvRecyclerView = tvRecyclerView;
        }

    }
}

