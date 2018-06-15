package money.kuxuan.platform.moneyplatfrom.frags;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.factory.model.db.Dialogs;
import money.kuxuan.platform.moneyplatfrom.Adapter.DialogAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.helper.DensityUtil;

/**
 * 底部弹出Fragment
 */
public class AlertFragment extends DialogFragment
 {
     List<Dialogs> data;

     private BottomSheetBehavior mBehavior;
     private DialogAdapter mAdapter;

     private OnSelectedListener mListener;

    public AlertFragment() {
        // Required empty public constructor
    }

    public  void setData(List<Dialogs> data){
        this.data = data;
    }

     @Override
     public void onStart() {
         super.onStart();
         Window window = getDialog().getWindow();
         WindowManager.LayoutParams params = window.getAttributes();
         params.gravity = Gravity.BOTTOM; // 显示在底部
         params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
         params.height = DensityUtil.dip2px(getContext(),200);
         //params.y=-350;
//         params.flags=WindowManager.LayoutParams.FLAG_BLUR_BEHIND;  背景不变暗
         window.setAttributes(params);

         // 这里用透明颜色替换掉系统自带背景
         int color = ContextCompat.getColor(getActivity(), android.R.color.transparent);
         window.setBackgroundDrawable(new ColorDrawable(color));
     }

     public  void setData(String[] datas){
         data = new ArrayList<Dialogs>();
         for(int i=0;i<datas.length;i++){

             data.add(new Dialogs(datas[i],datas[i]));
         }
     }
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // 复用即可
//
//        return new TransStatusBottomSheetDialog(getContext());
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 获取布局中的控件

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 不显示标题栏
        View root = inflater.inflate(R.layout.dialog_listview_layout, container, false);
        ListView listView  = (ListView) root.findViewById(R.id.dialog_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if (mListener != null) {
                    mListener.onSelectedItem(data.get(position).getK(),data.get(position).getV());
                    //取消和唤起者之间的应用，加快内存回收
                    mListener = null;
                }
            }
        });
        if(data!=null){
            mAdapter = new DialogAdapter(getContext(), data);
            listView.setAdapter(mAdapter);
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 界面显示的时候进行刷新

    }


     /**
      * 设置监听，并返回自己
      *
      * @param listener
      * @return
      */
     public AlertFragment setListener(OnSelectedListener listener) {
         mListener = listener;
         return this;
     }


     /**
      * 选中图片的监听器
      */
     public interface OnSelectedListener {
         void onSelectedItem(String k,String v);
     }










}
