package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.model.api.examine.InfoModel;
import money.kuxuan.platform.factory.presenter.statehome.InfoContract;
import money.kuxuan.platform.factory.presenter.statehome.InfoPresenter;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * 过审界面的详情页
 */
public class InfoActivity extends PresenterActivity<InfoContract.Presenter>
implements InfoContract.View{
    private static final String INFOID = "INFOID";
       @BindView(R.id.content)
        WebView webview;
    @BindView(R.id.title)
    TextView title;


    String id;
    public static final void show(Context context,String id){
        Intent intent = new Intent(context,InfoActivity.class);
        intent.putExtra(INFOID,id);
        context.startActivity(intent);

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @Override
    protected boolean initArgs(Bundle bundle) {
        id = bundle.getString(INFOID);
        return !TextUtils.isEmpty(id);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.start();
    }


    @Override
    public void getData(InfoModel infoModel) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        String content = infoModel.getContent();

//        String subString = content.substring(0,content.indexOf("</p>")+4);
//
//        if(subString.contains("-")&&subString.contains("2017")){
//            content = content.substring(content.indexOf("</p>")+4);
//        }

        webview.loadDataWithBaseURL(null, getNewContent(content), "text/html", "UTF-8", null);

        title.setText(infoModel.getTitle());

        hideLoading();

    }

    @Override
    protected InfoContract.Presenter initPresenter() {
        return new InfoPresenter(this,id);
    }




    private String getNewContent(String htmltext){

        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        return doc.toString();
    }


}
