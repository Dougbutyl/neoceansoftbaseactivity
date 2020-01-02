package com.app.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.app.mylibrary.R;
import com.app.mylibrary.shimmer.Shimmer;
import com.app.mylibrary.shimmer.ShimmerTextView;
import com.app.mylibrary.tools.DensityUtil;


/**
 * Created by Administrator on 2018/11/8.
 */

public class LoadingDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;
    private Shimmer shimmer;
    ShimmerTextView shimmerTextView;
    float heigtSize;

    //这里的view其实可以替换直接传layout过来的 因为各种原因没传(lan)
    public LoadingDialog(Context context, View view, boolean isCancelable, boolean isBackCancelable, float... heigtSize) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.view = view;
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
        if (heigtSize.length > 0) {
            this.heigtSize = heigtSize[0];
        }

    }

    public LoadingDialog(Context context, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
        view = View.inflate(context, R.layout.dialog_loading, null);
        shimmer = new Shimmer();
        shimmerTextView = (ShimmerTextView) view.findViewById(R.id.shimmertextview);

    }

    public void setLoadingText(String text) {
        shimmerTextView.setText(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Point point = DensityUtil.getScreenMetrics(context);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        //  window.setWindowAnimations(R.style.MyDialogAnimation);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (point.x * 0.65);
        if (heigtSize != 0 && heigtSize > 0) {
            params.height = (int) (point.y * heigtSize);
        } else {
            params.height = (int) (point.y * 0.25);
        }
        window.setAttributes(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        if (shimmer != null)
            shimmer.start(shimmerTextView);
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);

    }

    public void dimiss() {
        if (shimmer != null) {
            shimmer.cancel();
        }
        dismiss();
    }


}
