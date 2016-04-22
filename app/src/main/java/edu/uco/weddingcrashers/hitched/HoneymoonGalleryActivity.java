package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhouy_000 on 2016/2/19.
 */
public class HoneymoonGalleryActivity extends Activity {
    private Integer[] pic;
    private Integer[] pics={R.drawable.red_beach,R.drawable.caldera_at_sunset,R.drawable.boat_at_kamari_beach};
    private Integer[] pics1 = {R.drawable.borabora,R.drawable.borabora1,R.drawable.borabora2};
    private Integer[] pics2 = {R.drawable.maldives1,R.drawable.maldives2,R.drawable.maldives3};
    private  Integer[] pics3 = {R.drawable.maui1,R.drawable.maui2,R.drawable.maui3};
    private ImageView imageview;

    private boolean isFirstIn = true;

    private String url;
    public static final String URL_ONE = "http://travel.usnews.com/Santorini_Greece/";
    public static final String URL_TWO ="http://travel.usnews.com/Bora_Bora/";
    public static final String URL_THREE ="http://travel.usnews.com/Maldives/";
    public static final String URL_FOUR ="http://travel.usnews.com/Maui_HI/";

    private TextView tv;

    private TextView mBack;
    private ImageView iv;

    private WebView mWebView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Context context=getApplicationContext();
        setContentView(R.layout.honeymoon_gallery);
        Gallery gallery=(Gallery) findViewById(R.id.honeymoongallery);
        mWebView = (WebView) findViewById(R.id.webView);
        tv = (TextView) findViewById(R.id.tv);
        mBack = (TextView) findViewById(R.id.back);
        iv = (ImageView) findViewById(R.id.iv);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoneymoonGalleryActivity.this.finish();
            }
        });
        Intent intent = getIntent();
        int flag = Integer.valueOf(intent.getStringExtra("flag"));
        switch (flag){
            case 1:
                pic = pics;
                url = URL_ONE;
                tv.setText("Your first order of business in Santorini is to hit the colorful beaches — the black and red sands make for a memorable visit. Next up, indulge in the archaeological delights of the impressively preserved Ancient Akrotiri. or climb the ancient stairs that lead to the historic city of Manolas in Thirassia. From there, catch a breathtaking view of the caldera, a brilliant turquoise pool of water that serves as nucleus for the varied isles of ..");
                break;
            case 2:
                pic = pics1;
                url = URL_TWO;
                tv.setText("The small island of Bora Bora (just 6 miles long and more than 2 miles wide) overflows with beauty. Dormant volcanoes rise up at its center and fan out into lush jungle before spilling into an aquamarine lagoon. In fact, author James Michener, who wrote \"Tales of the South Pacific,\" called Bora Bora \"the most beautiful island in the world.\" The 18th-century British explorer James Cook even coined it as the \"pearl of the Pacific");
                break;
            case 3:
                url = URL_THREE;
                pic = pics2;
                tv.setText("Picture it: A private villa stands over crystal blue water; days finish with orange sunsets that make you hungry for local citrus; gourmet room service and a masseuse are on call to avoid any unnecessary trips from your porch; and the only thing to make you want to leave is the breathtaking coral reef and underwater creatures that demand a scuba session for a proper introduction. This is the Maldives");

                break;
            case 4:
                pic = pics3;
                url = URL_FOUR;
                tv.setText("Maui is not nearly as large as the Big Island, nor is it as small as Lanai, as bustling as Oahu or as quiet as Kauai. For many Hawaii vacationers, Maui is just right — offering a taste of just about everything the Aloha State has to offer, from impressive wildlife to intriguing history and culture. While on a visit here, you can shimmy alongside professional hula dancers, golf along coastal fairways, snorkel alongside five different ...");


            default:
                pic = pics;
                tv.setText("Your first order of business in Santorini is to hit the colorful beaches — the black and red sands make for a memorable visit. Next up, indulge in the archaeological delights of the impressively preserved Ancient Akrotiri. or climb the ancient stairs that lead to the historic city of Manolas in Thirassia. From there, catch a breathtaking view of the caldera, a brilliant turquoise pool of water that serves as nucleus for the varied isles of ..");

        }
        mWebView.loadUrl(url);


        WebSettings s = mWebView.getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
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

        iv.setVisibility(View.GONE);



        gallery.setAdapter(new HoneymoonGalleryAdapter(context));
        imageview=(ImageView) findViewById(R.id.honeymoongalleryimage);
        /*gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Picture:"+position,Toast.LENGTH_SHORT).show();
                imageview.setImageResource(pics[position]);
            }
        });*/
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageview.setImageResource(pic[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }




    public class HoneymoonGalleryAdapter extends BaseAdapter{
        private Context context;
        int imageBackground;
        public  HoneymoonGalleryAdapter(Context context){
            this.context= context;
        }

        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           /* ImageView image=new ImageView(context);
            image.setImageResource(pics[position]);
            image.setScaleType(ImageView.ScaleType.FIT_XY);*/
            View view = LayoutInflater.from(HoneymoonGalleryActivity.this).inflate(R.layout.image_item,null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            iv.setImageResource(pic[position]);
            return view;
        }
    }

}
