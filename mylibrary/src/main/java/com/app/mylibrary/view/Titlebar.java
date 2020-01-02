package com.app.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.mylibrary.tools.DensityUtil;

/**
 * User weixn
 * Date 2020/1/2
 */
public class Titlebar extends RelativeLayout {
    public Titlebar(Context context) {
        super(context);
        setHeight(context);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHeight(context);
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHeight(context);
    }

    private void setHeight(Context context) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = 128 - DensityUtil.getBarHeight(context);
        setLayoutParams(params);
    }

}
