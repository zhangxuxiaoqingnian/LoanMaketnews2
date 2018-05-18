package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import money.kuxuan.platform.common.widget.PortraitView;
import money.kuxuan.platform.factory.model.db.Amount;
import money.kuxuan.platform.moneyplatfrom.R;

/**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<Amount> list;
    private static final String TAG = "GridViewAdapter";
        public GridViewAdapter(Context _context, List<Amount> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.cell_amount_item, null);
            TextView tvCity = (TextView) convertView.findViewById(R.id.tv_title);
            PortraitView portraitView = (PortraitView) convertView.findViewById(R.id.im_portrait);
            Amount amount = list.get(position);
            tvCity.setText(amount.getName());
            portraitView.setup(Glide.with(context),amount.getIcon());

            return convertView;
        }
    }

     class CityItem {
        private String cityName;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }


    }
