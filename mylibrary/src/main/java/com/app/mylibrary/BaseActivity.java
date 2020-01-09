package com.app.mylibrary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.app.mylibrary.assistpackage.SwipeBackActivity;
import com.app.mylibrary.dialog.LoadingDialog;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Administrator on 2018/10/12.
 */

public abstract class BaseActivity extends SwipeBackActivity {

    private ImmersionBar immersionBar;
    TextView loadingText;
    public Context currentContext;
    private LoadingDialog loadingDialog;


    //Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (isHideTitleBar()) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//标题栏就没有了。
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        //}
        setContentView(layoutId());

        currentContext = this;
        if (isRegistEventBus()) {
            EventBus.getDefault().register(this);
        } else {

        }
        //unbinder = ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isImmersion()) {
                immersionBar = ImmersionBar.with(this);
                immersionBar.statusBarColor(setImmersionColor())
                        .init();
            }

            if (isScreen() && !isPicScreen()) {
                immersionBar = ImmersionBar.with(this);
                immersionBar.transparentBar()
                        .hideBar(BarHide.FLAG_HIDE_BAR)
                        .init();
            } else if (isScreen() && isPicScreen()) {
                immersionBar = ImmersionBar.with(this);
                //顺序不能乱
                immersionBar.transparentStatusBar()
                        .transparentBar()
                        .hideBar(BarHide.FLAG_HIDE_BAR)
                        .init();
            }
        }
        setSwipeBackEnable(isSwipebackActivity());
        //TODO 初始化 loading
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(this, true, true);
        initData();

    }


    protected abstract void initData();


    public void setLoadingText(String text) {
        if (loadingDialog != null) {
            loadingDialog.setLoadingText(text);
        }

    }

    public void showLoading() {
        Log.e("xxx", "showLoading");
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
            Log.e("xxx", "showLoading-show");

        }
    }

    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  unbinder.unbind();
        if (isRegistEventBus())
            EventBus.getDefault().unregister(this);
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }


    protected abstract int layoutId();

    //是否开启沉浸栏4.4以上,需要设置属性 android:fitsSystemWindows="true"
    protected abstract boolean isImmersion();

    //是否全屏
    //隐藏状态栏
    protected abstract boolean isScreen();

    //针对全屏引导页
    //不能设置android:fitsSystemWindows="true"
    protected abstract boolean isPicScreen();

    //是否隐藏标题栏
//    protected abstract boolean isHideTitleBar();

    //沉浸式需要设置颜色
    //在布局文件的根节点使用android:fitsSystemWindows="true"属性
    protected abstract int setImmersionColor();

    protected abstract boolean isSwipebackActivity();

    protected abstract boolean isExit();

    protected abstract boolean isRegistEventBus();


    Long mExitTime = 0l;

    @Override
    public void onBackPressed() {
        if (isExit()) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {

                System.exit(0);
            }
        } else {

            super.onBackPressed();
            overridePendingTransition(0, R.anim.translate_dialog_out);
        }
    }

    public void showToast(String str) {
        Toast.makeText(currentContext, str, Toast.LENGTH_LONG).show();
    }

    public void finishAc(View view) {
        finish();
        overridePendingTransition(0, R.anim.translate_dialog_out);
    }

   public void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }


}
