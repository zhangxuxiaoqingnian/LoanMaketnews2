package money.kuxuan.platform.moneyplatfrom.helper;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Created by Allence on 2018/5/17 0017.
 */

public class FastFlexBean extends BaseIndexPinyinBean {

    private String platform;//贷款平台的名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的
    



    @Override
    public String getTarget() {
        return platform;
    }

    public FastFlexBean() {
    }

    public FastFlexBean(String platform) {
        this.platform = platform;
    }

    public FastFlexBean setPlatform(String city) {
        this.platform = city;
        return this;
    }


    public String getPlatform() {
        return platform;
    }


    public boolean isTop() {
        return isTop;
    }

    public FastFlexBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }

    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }

}
