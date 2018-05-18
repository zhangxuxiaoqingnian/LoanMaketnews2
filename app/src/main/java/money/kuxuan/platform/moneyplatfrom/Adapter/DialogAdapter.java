package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Dialogs;
import money.kuxuan.platform.moneyplatfrom.R;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 17/6/11
 */
public class DialogAdapter extends BaseAdapter {
    /**
     * Common
     */


    private LayoutInflater mInflate;
    private Context mContext;
    private List<Dialogs> mData;
    private ViewHolder mViewHolder;



    public DialogAdapter(Context context, List<Dialogs> data) {
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //获取数据的type类型

        final Dialogs value = (Dialogs) getItem(position);
        //无tag时
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_dialog_layout, parent, false);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.text);

            //为对应布局创建播放器
            convertView.setTag(mViewHolder);
        }

        //有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.title.setText(mData.get(position).getV());

        return convertView;
    }



    private static class ViewHolder {

        private TextView title;


    }
}
