package com.example.islamictrustorganization;

import android.content.Context;

import com.example.iosprogressbarforandroid.IOSProgressHUD;

public class LoadingDialog {

    private IOSProgressHUD dialog;
    private static final LoadingDialog ourInstance = new LoadingDialog();

    public static LoadingDialog getInstance() {
        return ourInstance;
    }

    private LoadingDialog() { }

    public void show(Context context) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = new IOSProgressHUD(context);
        dialog.setSize(80,80);
        dialog.setCancellable(true);
        dialog.show();
    }
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
