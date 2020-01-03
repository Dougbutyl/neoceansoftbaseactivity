package com.app.mylibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.mylibrary.tools.DensityUtil;

/**
 * User weixn
 * Date 2020/1/2
 */
public class Titlebar extends RelativeLayout {
    Context context;
    public Titlebar(Context context) {
        super(context);
        this.context=context;

    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context=context;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childHeight = 88;//得到宽度
        heightMeasureSpec = MeasureSpec
                .makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);




    }


}
