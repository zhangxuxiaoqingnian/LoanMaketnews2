package money.kuxuan.platform.factory.model.api.search;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class SearchModel {
    public String search;
    public String term;
    public String amount;
    public String user_title;
    public String ranking_list;
    public int page;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getRanking_list() {
        return ranking_list;
    }

    public void setRanking_list(String ranking_list) {
        this.ranking_list = ranking_list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public SearchModel(String search, String term, String amount, String user_title, String ranking_list, int page) {
        this.search = search;
        this.term = term;
        this.amount = amount;
        this.user_title = user_title;
        this.ranking_list = ranking_list;
        this.page = page;
    }

    public SearchModel(String search,int page) {
        this.search = search;
        this.page = page;
    }

}
