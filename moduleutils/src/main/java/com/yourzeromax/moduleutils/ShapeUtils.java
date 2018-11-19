package com.yourzeromax.moduleutils;

/* *
 * Created by yourzeromax
 * on 2018/10/22
 *
 *
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

public class ShapeUtils {
    private static final int RADUIS_DEFAULT = 5;
    private static final int ZONE_LEFT = 1;
    private static final int ZONE_RIGHT = 2;
    private static final int ZONE_TOP = 3;
    private static final int ZONE_BOTTOM = 4;
    private static final int ZONE_ALL = 5;
    private static final int ZONE_BOTTOMRIGHT = 6;
    private static final int ZONE_BOTTOMLEFT = 7;
    private static final int ZONE_TOPRIGHT = 8;
    private static final int ZONE_TOPLEFT = 9;

    /**
     * @param context
     * @param colorId
     * @param roundRadiusDp //	top-left(x1,y1), top-right(x2,y2), bottom-right(x3,y3), bottom-left(x4,y4)
     *                      float[]{x1,y1,x2,y2,x3,y3,x4,y4}
     * @return
     */
    public static GradientDrawable getRectShape(Context context, int colorId, float[] roundRadiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        float[] roundRadiusPx = new float[roundRadiusDp.length];
        for (int i = 0; i < roundRadiusDp.length; i++) {
            roundRadiusPx[i] = ViewUtils.dp2px(context, roundRadiusDp[i]);
        }
        drawable.setCornerRadii(roundRadiusPx);
        if (colorId != -1) {
            drawable.setColor(context.getResources().getColor(colorId));
        }
        return drawable;
    }

    /**
     * @param context
     * @param colorId       内容填充颜色(id)
     * @param roundRadiusDp 4个角的圆角半径(dp)
     * @return
     */
    public static GradientDrawable getRectShape(Context context, int colorId, float roundRadiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        float roundRadiusPx = ViewUtils.dp2px(context, roundRadiusDp);
        drawable.setCornerRadius(roundRadiusPx);
        if (colorId != -1) {
            drawable.setColor(context.getResources().getColor(colorId));
        }
        return drawable;
    }

    /**
     * @param context
     * @param color         内容填充颜色
     * @param roundRadiusDp 4个角的圆角半径(dp)
     * @return
     */
    public static GradientDrawable getRectShapeByColor(Context context, int color, float roundRadiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        float roundRadiusPx = ViewUtils.dp2px(context, roundRadiusDp);
        drawable.setCornerRadius(roundRadiusPx);
        drawable.setColor(color);
        return drawable;
    }


    /**
     * @param context
     * @param colorId 内容填充颜色(id)
     * @return
     */
    public static GradientDrawable getRectShape(Context context, int colorId) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        float roundRadiusPx = ViewUtils.dp2px(context, RADUIS_DEFAULT);
        drawable.setCornerRadius(roundRadiusPx);
        if (colorId != -1) {
            drawable.setColor(context.getResources().getColor(colorId));
        }
        return drawable;
    }

    public static GradientDrawable getRectShapeBottom(Context context, int corlorId, float buttomRadiusDp) {
        return getRectShape(context, corlorId, getSideFloats(buttomRadiusDp, ZONE_BOTTOM));
    }

    public static GradientDrawable getRectShapeBottom(Context context, int corlorId) {
        return getRectShapeBottom(context, corlorId, RADUIS_DEFAULT);
    }

    public static GradientDrawable getRectShapeTop(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_TOP));
    }

    public static GradientDrawable getRectShapeTop(Context context, int colorId) {
        return getRectShapeTop(context, colorId, RADUIS_DEFAULT);
    }

    public static GradientDrawable getRectShapeLeft(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_LEFT));
    }

    public static GradientDrawable getRectShapeLeft(Context context, int colorId) {
        return getRectShapeLeft(context, colorId, RADUIS_DEFAULT);
    }

    public static GradientDrawable getRectShapeRight(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_RIGHT));
    }

    public static GradientDrawable getRectShapeRight(Context context, int colorId) {
        return getRectShapeRight(context, colorId, RADUIS_DEFAULT);
    }

    public static GradientDrawable getRectShapeBottomRight(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_BOTTOMRIGHT));
    }

    public static GradientDrawable getRectShapeBottomLeft(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_BOTTOMLEFT));
    }

    public static GradientDrawable getRectShapeTopRight(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_TOPRIGHT));
    }

    public static GradientDrawable getRectShapeTopLeft(Context context, int colorId, float topRadiusDp) {
        return getRectShape(context, colorId, getSideFloats(topRadiusDp, ZONE_TOPLEFT));
    }

    private static float[] getSideFloats(float radius, int zone) {
        float x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;
        switch (zone) {
            case ZONE_LEFT:
                x1 = y1 = x4 = y4 = radius;
                break;
            case ZONE_RIGHT:
                x2 = y2 = x3 = y3 = radius;
                break;
            case ZONE_TOP:
                x1 = y1 = x2 = y2 = radius;
                break;
            case ZONE_BOTTOM:
                x3 = y3 = x4 = y4 = radius;
                break;
            case ZONE_BOTTOMLEFT:
                x4 = y4 = radius;
                break;
            case ZONE_BOTTOMRIGHT:
                x3 = y3 = radius;
                break;
            case ZONE_TOPLEFT:
                x1 = y1 = radius;
                break;
            case ZONE_TOPRIGHT:
                x2 = y2 = radius;
                break;
            case ZONE_ALL:
                x1 = y1 = x2 = y2 = x3 = y3 = x4 = y4 = radius;
                break;
            default:
        }

        return new float[]{x1, y1, x2, y2, x3, y3, x4, y4};
    }


    /**
     * @param shape         targetShape
     * @param context
     * @param strokeColorId 描边的颜色id
     * @param strokeWithdDp 描边线的宽度
     * @return
     */
    public static GradientDrawable getStroke(GradientDrawable shape, Context context, int strokeColorId, float strokeWithdDp) {
        if (strokeColorId != -1) {
            shape.setStroke(ViewUtils.dp2px(context, strokeWithdDp), context.getResources().getColor(strokeColorId));
        }
        return shape;
    }

    public static GradientDrawable getStroke(Context context, int strokeColorId, float strokeWithdDp) {
        GradientDrawable rectShape = getRectShape(context, R.color.transparent, strokeWithdDp);
        getStroke(rectShape, context, strokeColorId, strokeWithdDp);
        return rectShape;
    }

    public static GradientDrawable getStroke(Context context, int strokeColorId) {
        GradientDrawable rectShape = getRectShape(context, R.color.transparent, RADUIS_DEFAULT);
        getStroke(rectShape, context, strokeColorId, RADUIS_DEFAULT);
        return rectShape;
    }
}
