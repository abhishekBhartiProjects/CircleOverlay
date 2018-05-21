package com.example.abhishekbharti.circleoverlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by abhishekbharti on 21/05/18.
 */

public class ShapeOverlayView extends LinearLayout {
    private Bitmap bitmap;
    public int radius = 100;
    public float centerX = 100;
    public float centerY = 100;
    public int alpha = 120;
    boolean isCircularOverlay = true;
    int rectLeft, rectRight, rectTop, rectBottom;

    //for circular shape
    public ShapeOverlayView(Context context, int radius, float x, float y) {
        super(context);
        this.radius = radius;
        this.centerX = x;
        this.centerY = y;
        this.isCircularOverlay = true;
    }

    //for rounded rectangular shape
    public ShapeOverlayView(Context context, int radius, int rectLeft, int rectRight, int rectTop, int rectBottom) {
        super(context);
        this.radius = radius;
        this.rectLeft = rectLeft;
        this.rectRight = rectRight;
        this.rectTop = rectTop;
        this.rectBottom = rectBottom;
        this.isCircularOverlay = false;
    }

    public ShapeOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setCenterCoordinate(float x, float y){
        this.centerX = x;
        this.centerY = y;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShapeOverlayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (bitmap == null) {
            if(isCircularOverlay){
                createCircularWindowFrame();
            } else {
                createRoundedRectangularWindowFrame();
            }
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    protected void createCircularWindowFrame() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas osCanvas = new Canvas(bitmap);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setAlpha(alpha);
        osCanvas.drawRect(outerRectangle, paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        osCanvas.drawCircle(centerX, centerY, radius, paint);
    }

    protected void createRoundedRectangularWindowFrame() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas osCanvas = new Canvas(bitmap);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setAlpha(alpha);
        osCanvas.drawRect(outerRectangle, paint);

        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        RectF innerRectangle = new RectF(rectLeft, rectTop, rectRight, rectBottom);
        osCanvas.drawRoundRect(innerRectangle, radius, radius, paint);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        bitmap = null;
    }

}
