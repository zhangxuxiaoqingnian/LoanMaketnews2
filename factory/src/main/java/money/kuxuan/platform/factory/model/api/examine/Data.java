package money.kuxuan.platform.factory.model.api.examine;

import java.util.List;

import money.kuxuan.platform.factory.model.db.DataE;
import money.kuxuan.platform.factory.model.db.Page;

public class Data{
    private List<DataE> data;
    private Page pageinfo;

    public List<DataE> getData() {
        
        return data;
    }

    public void setData(List<DataE> data) {
        this.data = data;
    }

    public Page getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Page pageinfo) {
        this.pageinfo = pageinfo;
    }
}