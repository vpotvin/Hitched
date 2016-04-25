package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者：Created by rush_yu on 2016/4/6 15:05
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class HotelDetails extends Activity {

    private WebView mWebView;
    private boolean isFirstIn = true;
    private String url;
    private String url1="http://travel.usnews.com/features/6-common-hotel-booking-mistakes-and-how-to-avoid-them/";
    private String url2="http://travel.usnews.com/features/9-common-pieces-of-travel-advice-you-should-never-follow/";
    private String url3 ="http://travel.usnews.com/features/6-insider-tips-for-choosing-a-hotel-loyalty-program/";
    private String url4 ="http://travel.usnews.com/features/6-cutting-edge-hotel-technologies-that-make-your-stay-better/";
    private  String url5="http://travel.usnews.com/features/5-surprising-ways-hotels-are-catering-to-families/";
    private String url6="http://travel.usnews.com/features/the-11-best-hotels-for-book-lovers/";
    private String url7 ="http://travel.usnews.com/gallery/10-common-pieces-of-travel-advice-you-should-never-follow/";
    private String url8 ="http://travel.usnews.com/Santorini_Greece/";
    private String url9 ="http://travel.usnews.com/Bora_Bora/";
    private String url10 ="http://travel.usnews.com/Maldives/";
    private String url11 ="http://travel.usnews.com/Maui_HI/";
    private String url12="http://travel.usnews.com/Tahiti_French_Polynesia/";
    private String url13="http://travel.usnews.com/Bali_Indonesia/";
    private String url14="http://travel.usnews.com/Fiji/";
    private String url15="http://travel.usnews.com/British_Virgin_Islands/";
    private String url16="http://travel.usnews.com/Paris_France/";
    private String url17 ="http://travel.usnews.com/Martinique/";
    private String url18="http://travel.usnews.com/Kauai_HI/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoteldetail);

        initUI();
    }

    private void initUI() {
        mWebView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();
        int a = Integer.valueOf(intent.getStringExtra("flag"));
        switch (a){
            case 0:
                url=url1;
                break;
            case 1:
                url=url2;
                break;
            case 2:
                url = url3;
                break;
            case 3:
                url =url4;
                break;
            case 4:
                url = url5;
                break;
            case 5:
                url =url6;
                break;
            case 6:
                url = url7;
                break;
            case 7:
                url = url8;
                break;
            case 8:
                url =url9;
                break;

             case 9:
                 url =url10;
                 break;
            case 10:
                url=url11;
                break;
            case 11:
                url=url2;
                break;
            case 12:
                url = url3;
                break;
            case 13:
                url= url4;
                break;
            case 14:
                url =url5;
                break;
            case 15:
                url =url6;
                break;
            case 16:
                url =url17;
                break;
            case 17:
                url=url18;
                break;
            case 18:
                url="http://travel.usnews.com/Rankings/best_usa_vacations/";
                break;
            case 19:
                url="http://travel.usnews.com/Rankings/Best_Caribbean_Vacations/";
                break;
            case 20:
                url="http://travel.usnews.com/Rankings/Best_Europe_Vacations/";
                break;
            case 21:
                url="http://travel.usnews.com/Rankings/Best_Mexico_Vacations/";
                break;
            case 22:
                url="http://travel.usnews.com/Rankings/Best_Canada_Vacations/";
                break;
            case 23:
                url="http://travel.usnews.com/Rankings/Best_Asian_Vacations/";
                break;
            default:
                url = url2;

        }

        mWebView.loadUrl(url);


        WebSettings s = mWebView.getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
        s.setJavaScriptEnabled(true);     // enable navigator.geolocation
        // s.setGeolocationEnabled(true);
        //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        //mWebView.setWebChromeClient(new WebChromeClient());// enable Web Storage: localStorage, sessionStorage     s.setDomStorageEnabled(true);  wb.requestFocus();  wb.setScrollBarStyle(0);
        //s.setJavaScriptEnabled(true);
        //s.setBuiltInZoomControls(true);
        //s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //s.setUseWideViewPort(true);
        // s.setLoadWithOverviewMode(true);
        //s.setSavePassword(true);
        //s.setSaveFormData(true);
        // s.setJavaScriptEnabled(true);
        // enable navigator.geolocation
        //s.setGeolocationEnabled(true);
        //s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        // enable Web Storage: localStorage, sessionStorage
        // s.setDomStorageEnabled(true);
        mWebView.requestFocus();
        final SVProgressHUD svProgressHUD = new SVProgressHUD(this);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(isFirstIn){
                    isFirstIn = false;
                    svProgressHUD.getProgressBar().setProgress(newProgress);
                    svProgressHUD.showWithProgress("progress " + newProgress + "%", SVProgressHUD.SVProgressHUDMaskType.Black);
                }
                else if(newProgress>80)
                    svProgressHUD.dismiss();
                else {
                    svProgressHUD.getProgressBar().setProgress(newProgress);
                    svProgressHUD.setText("progress "+newProgress+"%");
                }
            }
        });

        //mWebView.setScrollBarStyle(0);
        //http://www.bookingbuddy.com/c/lander/fancy.html?fsOpener=true&mode=hotel&af=11207187&currency=USD&locale=en_US#rooms=1&location2=189433&source=55821&request_id=0d7da4dd-01b1-43d9-9f3c-5571c186d036&travelers=2&traqparam=USNews_SearchLess_Leavebehind_Chrome_Auth&pm_template=o-lBLl1iQcKz3EizTMBxfQ
        mWebView.setWebViewClient(new WebViewClient(){
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          // TODO Auto-generated method stub
                                          //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                                          view.loadUrl(url);
                                          //iv.setVisibility(View.GONE);

                                          return true;
                                      }

                                  }

        );


    }
}
