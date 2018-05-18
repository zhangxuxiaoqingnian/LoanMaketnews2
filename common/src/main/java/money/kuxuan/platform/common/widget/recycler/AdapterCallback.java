package money.kuxuan.platform.common.widget.recycler;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
