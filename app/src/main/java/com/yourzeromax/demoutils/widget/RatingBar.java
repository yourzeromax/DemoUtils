package com.yourzeromax.demoutils.widget;

/* *
 * Created by yourzeromax
 * on 2019/1/16
 *
 *
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yourzeromax.demoutils.R;


public class RatingBar extends View {
    private int starDistance = 0;
    private int maxScore = 10;
    private int starCount = 5;
    private int starSize;
    private float starMark = 0.0F;
    private Bitmap starFillBitmap;
    private Drawable starEmptyDrawable;
    private OnStarChangeListener onStarChangeListener;
    private Paint paint;
    private boolean integerMark = false;
    private boolean markable;

    private float scaleScore = (float) starCount / maxScore;

    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * init
     *
     * @param context context
     * @param attrs   attrs
     */
    private void init(Context context, AttributeSet attrs) {
        setClickable(true);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        this.starDistance = (int) mTypedArray.getDimension(R.styleable.RatingBar_starDistance, 0);
        this.starSize = (int) mTypedArray.getDimension(R.styleable.RatingBar_starSize, 20);
        this.starCount = mTypedArray.getInteger(R.styleable.RatingBar_starCount, 5);
        this.maxScore = mTypedArray.getInteger(R.styleable.RatingBar_maxScore, 10);
        this.starEmptyDrawable = mTypedArray.getDrawable(R.styleable.RatingBar_starEmpty);
        this.starFillBitmap = drawableToBitmap(mTypedArray.getDrawable(R.styleable.RatingBar_starFill));
        this.scaleScore = (float) starCount / maxScore;
        mTypedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(starFillBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    }

    /**
     * @param integerMark is the mark need to be integer.
     */
    public void setIntegerMark(boolean integerMark) {
        this.integerMark = integerMark;
    }

    /**
     * set is this view can be mark  and this need to be call after first mark.
     *
     * @param markable markable
     */
    public void setMarkable(boolean markable) {
        this.markable = markable;
    }

    /**
     * set the mark
     *
     * @param mark score
     */
    public void setStarMark(float mark) {
        if (integerMark) {
            starMark = (int) (Math.ceil(mark) * scaleScore);
        } else {
            starMark = Math.round(mark) * 1.0f * scaleScore;
        }
        if (this.onStarChangeListener != null) {
            this.onStarChangeListener.onStarChange(starMark);  //调用监听接口
        }
        invalidate();
    }

    /**
     * @return starMark
     */
    public float getStarMark() {
        return starMark;
    }


    /**
     * callback
     */
    public interface OnStarChangeListener {
        void onStarChange(float mark);
    }

    /**
     * set the star change listener
     *
     * @param onStarChangeListener
     */
    public void setOnStarChangeListener(OnStarChangeListener onStarChangeListener) {
        this.onStarChangeListener = onStarChangeListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(starSize * starCount + starDistance * (starCount - 1), starSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (starFillBitmap == null || starEmptyDrawable == null) {
            return;
        }
        for (int i = 0; i < starCount; i++) {
            starEmptyDrawable.setBounds((starDistance + starSize) * i, 0, (starDistance + starSize) * i + starSize, starSize);
            starEmptyDrawable.draw(canvas);
        }
        if (starMark > 1) {
            canvas.drawRect(0, 0, starSize, starSize, paint);
            if (starMark - (int) (starMark) == 0) {
                for (int i = 1; i < starMark; i++) {
                    canvas.translate(starDistance + starSize, 0);
                    canvas.drawRect(0, 0, starSize, starSize, paint);
                }
            } else {
                for (int i = 1; i < starMark - 1; i++) {
                    canvas.translate(starDistance + starSize, 0);
                    canvas.drawRect(0, 0, starSize, starSize, paint);
                }
                canvas.translate(starDistance + starSize, 0);
                canvas.drawRect(0, 0, starSize * (Math.round((starMark - (int) (starMark)) * 10) * 1.0f / 10), starSize, paint);
            }
        } else {
            canvas.drawRect(0, 0, starSize * starMark, starSize, paint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!markable) {
            return super.onTouchEvent(event);
        }
        int x = (int) event.getX();
        if (x < 0) x = 0;
        if (x > getMeasuredWidth()) x = getMeasuredWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                setStarMark(x * 1.0f / (getMeasuredWidth() * 1.0f / starCount));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                setStarMark(x * 1.0f / (getMeasuredWidth() * 1.0f / starCount));
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    /**
     * drawable to bitmap
     *
     * @param drawable drawable
     * @return bitmap
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(starSize, starSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, starSize, starSize);
        drawable.draw(canvas);
        return bitmap;
    }
}


