package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.Adapter.CYBChangeCityGridViewAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.ContactAdapter;
import money.kuxuan.platform.moneyplatfrom.Bean.UserEntity;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.QGridView;

public class CityotherActivity extends PresenterActivity {

    private ContactAdapter mAdapter;
    private BannerHeaderAdapter mBannerHeaderAdapter;
    private String[] city = {"北京","上海","深圳","广州","郑州","杭州"};
    private IndexableLayout indexableLayout;
    private CYBChangeCityGridViewAdapter cybChangeCityGridViewAdapter;
    private ArrayList<String> list;
    private ImageView pic_contact_back;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        initAdapter();
        onlisten();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_pick_contact;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    public void initAdapter(){
        mAdapter = new ContactAdapter(this);
        indexableLayout.setAdapter(mAdapter);
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());
//        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
//        indexableLayout.addHeaderAdapter(new SimpleHeaderAdapter<>(mAdapter, "☆",null, null));

//         构造函数里3个参数,分别对应 (IndexBar的字母索引, IndexTitle, 数据源), 不想显示哪个就传null, 数据源传null时,代表add一个普通的View
//        mMenuHeaderAdapter = new MenuHeaderAdapter("↑", null, initMenuDatas());
//        indexableLayout.addHeaderAdapter(mMenuHeaderAdapter);

        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mBannerHeaderAdapter = new BannerHeaderAdapter("↑", null, bannerList);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);
    }

    public void initview(){
        intent = getIntent();
        pic_contact_back = (ImageView) findViewById(R.id.pic_contact_back);
        indexableLayout = (IndexableLayout) findViewById(R.id.indexableLayout);
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onlisten(){

        pic_contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if (originalPosition >= 0) {
                    intent.putExtra("city", entity.getNick());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(CityotherActivity.this, "选中Header/Footer:" + entity.getNick() + "  当前位置:" + currentPosition,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 自定义的Banner Header
     */
    class BannerHeaderAdapter extends IndexableHeaderAdapter {
        private static final int TYPE = 1;

        public BannerHeaderAdapter(String index, String indexTitle, List datas) {
            super(index, indexTitle, datas);
        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(CityotherActivity.this).inflate(R.layout.item_city_header, parent, false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
            // 数据源为null时, 该方法不用实现
            final VH vh = (VH) holder;
            list=new ArrayList<>();
            for(int i = 0; i<city.length; i++){
                list.add(city[i]);
            }

            cybChangeCityGridViewAdapter=new CYBChangeCityGridViewAdapter(CityotherActivity.this, list);
            vh.head_home_change_city_gridview.setAdapter(cybChangeCityGridViewAdapter);
            vh.head_home_change_city_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent.putExtra("city", list.get(position));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
//            vh.item_header_city_dw.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    intent.putExtra("city", vh.item_header_city_dw.getText().toString());
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });

        }

        private class VH extends RecyclerView.ViewHolder {
            GridView head_home_change_city_gridview;
//            TextView item_header_city_dw;
            public VH(View itemView) {
                super(itemView);
                head_home_change_city_gridview =(QGridView)itemView.findViewById(R.id.item_header_city_gridview);
            }
        }
    }

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(getResources().getStringArray(R.array.provinces));
        List<String> mobileStrings = Arrays.asList(getResources().getStringArray(R.array.provinces));
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }
}
