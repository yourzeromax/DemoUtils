package com.yourzeromax.moduleutils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.reflect.Method;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

public class ViewUtils {

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static float getTextWidth(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        return paint.measureText(string);
    }

    public static float getTextHeight(Paint paint, String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }
        Rect bonuds = new Rect();
        paint.getTextBounds(string, 0, string.length(), bonuds);
        return bonuds.height();
    }

    /**
     * 左上角的标签
     *
     * @param size            标签所占空间的边长
     * @param backgroundColor 标签颜色
     * @param text            标签文本
     * @param typeface        typeface
     * @param textSize        标签文本大小
     * @param textColor       标签文本颜色
     * @param textPadding     标签本文内边距
     * @return 标签Drawable
     */
    public static Drawable getTagDrawable(final int size, final int backgroundColor,
                                          final String text, final Typeface typeface,
                                          final int textSize, final int textColor,
                                          final int textPadding) {
        return new Drawable() {

            private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            private RectF mRect = new RectF();

            @Override
            public void draw(Canvas canvas) {
                mPaint.setTextSize(textSize);
                mPaint.setTypeface(typeface);
                int tagHeight = (int) (mPaint.descent() - mPaint.ascent() + textPadding);
                mPaint.setColor(backgroundColor);
                mPaint.setStyle(Paint.Style.FILL);
                final float halfLineHeight = (mPaint.ascent() + mPaint.descent()) / 2f;
                int saved = canvas.save();
                canvas.rotate(-45, size / 2, size / 2);
                float offset = (float) ((Math.sqrt(2) - 1) * size / 2);
                mRect.set(getBounds().left - offset, size / 2 - tagHeight, getBounds().right + offset, size / 2);
                canvas.drawRect(mRect, mPaint);
                mPaint.setColor(textColor);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(text, size / 2, size / 2 - tagHeight / 2 - halfLineHeight + textPadding, mPaint);
                canvas.restoreToCount(saved);
            }

            @Override
            public void setAlpha(int alpha) {
                mPaint.setAlpha(alpha);
            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {
                mPaint.setColorFilter(colorFilter);
            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
            }

            @Override
            public int getIntrinsicWidth() {
                return size;
            }

            @Override
            public int getIntrinsicHeight() {
                return size;
            }
        };
    }

    /**
     * 右上角的书角Drawable
     *
     * @param size            标签所占空间的边长
     * @param backgroundColor 标签颜色
     * @param text            标签文本
     * @param typeface        typeface
     * @param textSize        标签文本大小
     * @param textColor       标签文本颜色
     * @param textPadding     标签本文内边距
     * @return 右上角的书角Drawable
     */
    public static Drawable getCornerDrawable(final int size, final int backgroundColor,
                                             final String text, final Typeface typeface,
                                             final int textSize, final int textColor,
                                             final int textPadding) {
        return new Drawable() {

            private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            private RectF mRect = new RectF();

            @Override
            public void draw(Canvas canvas) {
                mPaint.setTextSize(textSize);
                mPaint.setTypeface(typeface);
                mPaint.setFakeBoldText(true);
                int tagHeight = (int) (mPaint.descent() - mPaint.ascent() + textPadding);
                mPaint.setColor(backgroundColor);
                mPaint.setStyle(Paint.Style.FILL);
                final float halfLineHeight = (mPaint.ascent() + mPaint.descent()) / 2f;
                int saved = canvas.save();
                canvas.rotate(45, size / 2, size / 2);
                float offset = (float) ((Math.sqrt(2) - 1) * size / 2);
                mRect.set(getBounds().left - offset, getBounds().top - offset, getBounds().right + offset,
                        size / 2);
                canvas.drawRect(mRect, mPaint);
                mPaint.setColor(textColor);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(text, size / 2, size / 2 - tagHeight / 2 - halfLineHeight + textPadding, mPaint);
                canvas.restoreToCount(saved);
            }

            @Override
            public void setAlpha(int alpha) {
                mPaint.setAlpha(alpha);
            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {
                mPaint.setColorFilter(colorFilter);
            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
            }

            @Override
            public int getIntrinsicWidth() {
                return size;
            }

            @Override
            public int getIntrinsicHeight() {
                return size;
            }
        };
    }

    /**
     * 圆角矩形Drawable
     *
     * @param radius                 圆角半径
     * @param normalBackgroundColor  normalBackgroundColor
     * @param pressedBackgroundColor pressedBackgroundColor
     * @return 圆角矩形Drawable
     */
    public static Drawable getRoundedRectDrawable(int radius, int normalBackgroundColor, int pressedBackgroundColor) {
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setColor(pressedBackgroundColor);
        pressedDrawable.setCornerRadius(radius);
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normalBackgroundColor);
        normalDrawable.setCornerRadius(radius);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        drawable.addState(new int[]{}, normalDrawable);
        return drawable;
    }

    /**
     * 圆角矩形Drawable
     *
     * @param radius                 Specify radii for each of the 4 corners. For each corner, the array
     *                               contains 2 values, <code>[X_radius, Y_radius]</code>. The corners are ordered
     *                               top-left, top-right, bottom-right, bottom-left.
     * @param normalBackgroundColor  normalBackgroundColor
     * @param pressedBackgroundColor pressedBackgroundColor
     * @return 圆角矩形Drawable
     */
    public static Drawable getRoundedRectDrawable(float[] radius, int normalBackgroundColor, int pressedBackgroundColor) {
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setColor(pressedBackgroundColor);
        pressedDrawable.setCornerRadii(radius);
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normalBackgroundColor);
        normalDrawable.setCornerRadii(radius);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        drawable.addState(new int[]{}, normalDrawable);
        return drawable;
    }

    public static Drawable getGradientRectDrawable(final int width, final int height,
                                                   final GradientDrawable.Orientation orientation,
                                                   int startBackgroundColor, int endBackgroundColor) {
        return new GradientDrawable(orientation, new int[]{startBackgroundColor, endBackgroundColor}) {
            @Override
            public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
            }

            @Override
            public int getIntrinsicWidth() {
                return width;
            }

            @Override
            public int getIntrinsicHeight() {
                return height;
            }
        };
    }

    /**
     * 从左到右渐变的圆角矩形Drawable
     *
     * @param radius               圆角半径
     * @param startBackgroundColor 渐变开始颜色
     * @param endBackgroundColor   渐变结束颜色
     * @return 从左到右渐变的圆角矩形Drawable
     */
    public static Drawable getLTRGradientRoundedRectDrawable(int radius, int startBackgroundColor, int endBackgroundColor) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{startBackgroundColor, endBackgroundColor});
        drawable.setCornerRadius(radius);
        return drawable;
    }

    /**
     * 从上到下渐变的圆角矩形Drawable
     *
     * @param radius               圆角半径
     * @param startBackgroundColor 渐变开始颜色
     * @param endBackgroundColor   渐变结束颜色
     * @return 从上到下渐变的圆角矩形Drawable
     */
    public static Drawable getTTBGradientRoundedRectDrawable(int radius, int startBackgroundColor, int endBackgroundColor) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{startBackgroundColor, endBackgroundColor});
        drawable.setCornerRadius(radius);
        return drawable;
    }

    /**
     * 圆形Drawable
     *
     * @param radius 半径
     * @param color  颜色
     * @return 圆形Drawable
     */
    public static Drawable getCircleDrawable(float radius, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setSize((int) (radius * 2 + 0.5), (int) (radius * 2 + 0.5));
        drawable.setColor(color);
        return drawable;
    }

    /**
     * 描边Drawable
     *
     * @param radius 半径
     * @param width  描边大小
     * @param color  描边颜色
     * @return 描边Drawable
     */
    public static Drawable getStrokeDrawable(float radius, int width, int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(radius);
        drawable.setStroke(width, color);
        return drawable;
    }


    /**
     * 直角三角形Drawable
     *
     * @param width     宽
     * @param height    高
     * @param direction 直角位置：0:左上，1:右上，2:右下，3:左下
     * @param color     颜色
     * @return 直角三角形Drawable
     */
    public static Drawable getRightTriangleDrawable(final int width, final int height,
                                                    final int direction, final int color) {
        return new Drawable() {

            private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            private Path mPath = new Path();

            @Override
            public void draw(Canvas canvas) {
                switch (direction) {
                    case 0:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, 0);
                        mPath.lineTo(width, 0);
                        mPath.lineTo(0, height);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 1:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, 0);
                        mPath.lineTo(width, 0);
                        mPath.lineTo(width, height);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 2:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, height);
                        mPath.lineTo(width, height);
                        mPath.lineTo(width, 0);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 3:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, 0);
                        mPath.lineTo(0, height);
                        mPath.lineTo(width, height);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void setAlpha(int alpha) {
                mPaint.setAlpha(alpha);
            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {
                mPaint.setColorFilter(colorFilter);
            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
            }

            @Override
            public int getIntrinsicWidth() {
                return width;
            }

            @Override
            public int getIntrinsicHeight() {
                return height;
            }
        };
    }

    /**
     * 三角形Drawable
     *
     * @param width     宽
     * @param height    高
     * @param direction 箭头朝向：0:左，1:上，2:右，3:下
     * @param color     颜色
     * @return 三角形Drawable
     */
    public static Drawable getTriangleDrawable(final int width, final int height,
                                               final int direction, final int color) {
        return new Drawable() {

            private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
            private Path mPath = new Path();

            @Override
            public void draw(Canvas canvas) {
                switch (direction) {
                    case 0:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(width, 0);
                        mPath.lineTo(width, height);
                        mPath.lineTo(0, height / 2f);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 1:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, height);
                        mPath.lineTo(width, height);
                        mPath.lineTo(width / 2f, 0);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 2:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, 0);
                        mPath.lineTo(0, height);
                        mPath.lineTo(width, height / 2f);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    case 3:
                        mPaint.setColor(color);
                        mPath.reset();
                        mPath.moveTo(0, 0);
                        mPath.lineTo(width, 0);
                        mPath.lineTo(width / 2f, height);
                        mPath.close();
                        canvas.drawPath(mPath, mPaint);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void setAlpha(int alpha) {
                mPaint.setAlpha(alpha);
            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {
                mPaint.setColorFilter(colorFilter);
            }

            @Override
            public int getOpacity() {
                return PixelFormat.TRANSLUCENT;
            }

            @Override
            public int getIntrinsicWidth() {
                return width;
            }

            @Override
            public int getIntrinsicHeight() {
                return height;
            }
        };
    }

    public static int getViewWidth(View view) {
        if (view.getWidth() > 0) {
            return view.getWidth();
        } else if (view.getLayoutParams() != null && view.getLayoutParams().width > 0) {
            return view.getLayoutParams().width;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            return view.getMeasuredWidth();
        }
    }

    public static int getViewHeight(View view) {
        if (view.getHeight() > 0) {
            return view.getHeight();
        } else if (view.getLayoutParams() != null && view.getLayoutParams().height > 0) {
            return view.getLayoutParams().height;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            return view.getMeasuredHeight();
        }
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    /**
     * 获取是否存在NavigationBar，是否有虚拟按钮
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;
    }

    /**
     * 获取虚拟按钮NavigationBar的高度
     *
     * @param Activity activity
     * @return ActionBar高度
     */
    public static int getNavigationBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }


    public static void showAsDropDown(PopupWindow popupWindow, View anchor) {
        showAsDropDown(popupWindow, anchor, 0, 0);
    }

    /**
     * hack: 修复Android7.0以上 PopupWindow显示异常的Bug
     *
     * @param popupWindow popup window
     * @param anchor      the view on which to pin the popup window
     * @param xOffset     A horizontal offset from the anchor in pixels
     * @param yOffset     A vertical offset from the anchor in pixels
     */
    public static void showAsDropDown(PopupWindow popupWindow, View anchor, int xOffset, int yOffset) {
        if (Build.VERSION.SDK_INT == 24 || Build.VERSION.SDK_INT == 25) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1] + anchor.getHeight();

            WindowManager wm = (WindowManager) anchor.getContext().getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                int screenHeight = wm.getDefaultDisplay().getHeight();
                popupWindow.setHeight(screenHeight - y);
            }

            popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x + xOffset, y + yOffset);
        } else if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            popupWindow.setHeight(height);
            popupWindow.showAsDropDown(anchor, xOffset, yOffset);
        } else {
            popupWindow.showAsDropDown(anchor, xOffset, yOffset);
        }
    }


    public static void showDropDownAndUpdate(PopupWindow popupWindow, View anchor, int xOffset, int yOffset) {

        if (Build.VERSION.SDK_INT == 24 || Build.VERSION.SDK_INT == 25) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1] + anchor.getHeight();
            if (popupWindow.isShowing()) {
                popupWindow.update(anchor, xOffset, yOffset, WindowManager.LayoutParams.MATCH_PARENT, popupWindow.getMaxAvailableHeight(anchor));
            } else {
                WindowManager wm = (WindowManager) anchor.getContext().getSystemService(Context.WINDOW_SERVICE);
                if (wm != null) {
                    int screenHeight = popupWindow.getMaxAvailableHeight(anchor);
                    popupWindow.setHeight(screenHeight);
                }
                popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, x + xOffset, y + yOffset);
            }
        } else if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = popupWindow.getMaxAvailableHeight(anchor);
            popupWindow.setHeight(height);
            if (popupWindow.isShowing()) {
                popupWindow.update(anchor, xOffset, yOffset, WindowManager.LayoutParams.MATCH_PARENT, height);
            } else {
                popupWindow.showAsDropDown(anchor, xOffset, yOffset);
            }
        } else {
            if (!popupWindow.isShowing()) {
                popupWindow.showAsDropDown(anchor, xOffset, yOffset);
            }
        }
    }
}
