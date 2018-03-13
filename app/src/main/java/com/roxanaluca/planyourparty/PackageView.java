package com.roxanaluca.planyourparty;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * TODO: document your custom view class.
 */
public class PackageView extends View {
    private String musicString;
    private String venueString;
    private String foodString;
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private static int predefinedHeight = 400;

    public PackageView(Context context) {
        super(context);
        init(null, 0);
    }

    public PackageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PackageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PackageView, defStyle, 0);

        mExampleColor = a.getColor(
                R.styleable.PackageView_exampleColor,
                mExampleColor);
        musicString = a.getString(
                R.styleable.PackageView_music);
        foodString = a.getString(
                R.styleable.PackageView_food);
        venueString = a.getString(
                R.styleable.PackageView_venue);

        if (foodString == null)
            foodString = "Hello from food String";
        if (musicString == null)
            musicString = "Hello from music String";
        if (venueString == null)
            venueString = "Hello from venue String";

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.PackageView_exampleDimension,
                mExampleDimension);

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(musicString);
        if (mTextWidth < mTextPaint.measureText(foodString))
            mTextWidth = mTextPaint.measureText(foodString);
        if (mTextWidth < mTextPaint.measureText(venueString))
            mTextWidth = mTextPaint.measureText(venueString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    //suspect here ??? need to investigate
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // here is the width and height of the application according to the padding of the element
        int paddingLeft = 20;//getPaddingLeft();
        int paddingTop = -40;//getPaddingTop();
        int paddingRight = 0;//getPaddingRight();
        int paddingBottom = 40;//getPaddingBottom();

        int contentWidth = Resources.getSystem().getDisplayMetrics().widthPixels - paddingLeft - paddingRight;
        int contentHeight = predefinedHeight - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(musicString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 3,
                mTextPaint);
        canvas.drawText(foodString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + 2*(contentHeight + mTextHeight) / 3,
                mTextPaint);
        canvas.drawText(venueString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight),
                mTextPaint);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getMusicString() {
        return musicString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setMusicString(String exampleString) {
        musicString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getFoodString() {
        return foodString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setFoodString(String exampleString) {
        foodString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getVenueString() {
        return musicString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setVenueString(String exampleString) {
        venueString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }
}
