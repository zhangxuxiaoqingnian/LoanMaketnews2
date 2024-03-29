package com.smileflowpig.money.common.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.ViewGroup;
import android.view.Window;

import com.smileflowpig.money.common.utils.UiTool;

public  class TransStatusBottomSheetDialog extends BottomSheetDialog {


        public TransStatusBottomSheetDialog(@NonNull Context context) {
            super(context);

        }

        public TransStatusBottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
            super(context, theme);
        }

        protected TransStatusBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            final Window window = getWindow();
            if(window==null){
                return;
            }

            //得到屏幕高度
          int screenHeight = UiTool.getScreenHeight(getOwnerActivity());
            //得到状态栏的高度
            int statusHeight = UiTool.getStatusBarHeight(getOwnerActivity());
//计算dialog高度并设置
            int dialogHeight = screenHeight-statusHeight;
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    dialogHeight<=0?ViewGroup.LayoutParams.MATCH_PARENT:dialogHeight);


        }


}