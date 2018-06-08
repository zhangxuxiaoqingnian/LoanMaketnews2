package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_AddressBook;
import money.kuxuan.platform.moneyplatfrom.helper.FastFlexBean;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class FastFlexAdapter extends RecyclerView.Adapter<FastFlexAdapter.ViewHolder> {
    protected Context mContext;
    protected List<FastFlexBean> mDatas;
    protected LayoutInflater mInflater;
    private Getnum getnum;

    public FastFlexAdapter(Context mContext, List<FastFlexBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<FastFlexBean> getDatas() {
        return mDatas;
    }

    public FastFlexAdapter setDatas(List<FastFlexBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public FastFlexAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.flaxflexitem, parent, false));
    }

    @Override
    public void onBindViewHolder(final FastFlexAdapter.ViewHolder holder, final int position) {
        final FastFlexBean fastFlexBean = mDatas.get(position);
        holder.tv_platform.setText(fastFlexBean.getPlatform());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getnum.data(position);
            }
        });
        //holder.avatar.setImageResource(R.drawable.why);
        Glide.with(mContext).load(fastFlexBean.getImgurl()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_platform;
        ImageView avatar;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_platform = (TextView) itemView.findViewById(R.id.tv_platform);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            content = itemView.findViewById(R.id.content);
        }
    }
    public interface Getnum{

        void data(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
