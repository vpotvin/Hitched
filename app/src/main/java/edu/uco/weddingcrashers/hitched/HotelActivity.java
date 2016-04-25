package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 作者：Created by rush_yu on 2016/3/18 19:07
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class HotelActivity extends FragmentActivity implements View.OnClickListener{

    ArrayList<HoneymoonItem> honeymoonItems;
    HoneymoonAdapter honeymoonAdapter;
    ListView honeymoonView;

    private AutoScrollViewPager pager;

    private TextView tv;

    private ListView mLv;

    private View v1,v2,v3;

    private Button bt1,bt2,bt3;

    private Fragment mInternation,mHome,mFlat;



    private ImagePage pagerAdapter;

    private FragmentManager manager;

    private CirclePageIndicator indicator;

    private int[] imgs = {R.drawable.yuxia1,R.drawable.yuxia2,R.drawable.yuxia3};

    private List<ImageView> imageViews;

    private Button flight,hotel,area;


    private WebView mWebView;

    private int[] imgs2 = {R.drawable.y1,R.drawable.y2,R.drawable.y3,R.drawable.y4,
    R.drawable.y5,R.drawable.y6,R.drawable.y7};
    private String[] tv1s ={"6 Common Hotel Booking Mistakes (And How to Avoid Them)",
                            "9 Common Pieces of Travel Advice You Should Never Follow",
                             "6 Insider Tips for Choosing a Hotel Loyalty Program",
                             "6 Cutting-Edge Hotel Technologies That Make Your Stay Better",
                              "5 Surprising Ways Hotels Are Catering to Families"
                               ,"The 11 Best Hotels for Book Lovers",
                               "10 Common Pieces of Travel Advice You Should Never Follow"
                              };
    private String[] tv2s ={"by Gwendolyn Shearman | 三月 22, 2016",
                             "by Liz Weiss | 三月 7, 2016",
                              "by Mark Murphy | 三月 21, 2016",
                               "by Lyn Mettler | 三月 18, 2016",
                                "by Kyle McCarthy | 三月 31, 2016"
            ,                   "by Lyn Mettler | 三月 22, 2016",
                                "by Liz Weiss | 三月 25, 2016"
                           };

    private List<Map<String,Object>> mapList;


  private TextView mBack;


    final Context context = this;
    private DisplayMetrics metric;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        honeymoonItems=new ArrayList<>();

        mWebView = (WebView) findViewById(R.id.webView);
        mBack = (TextView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotelActivity.this.finish();
            }
        });
        mLv = (ListView) findViewById(R.id.lv);
        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWebView.loadUrl("http://www.usnews.com/topics/subjects/hotels");

        WebSettings s = mWebView.getSettings();
        mapList = new ArrayList<>();
        for(int i=0;i<imgs2.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("img",imgs2[i]);
            map.put("tv1",tv1s[i]);
            map.put("tv2",tv2s[i]);
            mapList.add(map);
        }
       // s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setRenderPriority(WebSettings.RenderPriority.HIGH);
        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM ;
        switch (screenDensity){
            case DisplayMetrics.DENSITY_LOW :
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break ;
        }
        s.setDefaultZoom(zoomDensity);
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

        //mWebView.setScrollBarStyle(0);
        //http://www.bookingbuddy.com/c/lander/fancy.html?fsOpener=true&mode=hotel&af=11207187&currency=USD&locale=en_US#rooms=1&location2=189433&source=55821&request_id=0d7da4dd-01b1-43d9-9f3c-5571c186d036&travelers=2&traqparam=USNews_SearchLess_Leavebehind_Chrome_Auth&pm_template=o-lBLl1iQcKz3EizTMBxfQ
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
               // iv.setVisibility(View.GONE);

                return true;
            }
        });

        HoneymoonItem temp=new HoneymoonItem("Santorini",R.mipmap.santori,"Known for its brilliant sunsets, rich Greek food and romantic hotels, Santorini is almost tailor-made for those who've just said I do Honeymooners can lounge on red- and black-sand beaches or visit the island's wineries.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Bora Bora",R.mipmap.bora,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Maldives",R.mipmap.maldives,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Maui",R.mipmap.maui,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);
        RelativeLayout content = (RelativeLayout) findViewById(R.id.content);
        manager = getSupportFragmentManager();
        mInternation = new Internationa_FA();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content,mInternation);
        transaction.commit();

        mBack = (TextView) findViewById(R.id.back);

        pager = (AutoScrollViewPager) findViewById(R.id.autopager);
       /* honeymoonAdapter = new HoneymoonAdapter(this, honeymoonItems);
        //gridView.setAdapter(honeymoonAdapter);
        honeymoonView = (ListView) findViewById(R.id.itemizedHoneymoonList);
        honeymoonView.setAdapter(honeymoonAdapter);*/
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        imageViews = new ArrayList<ImageView>();
        for(int i:imgs){
            ImageView iv = new ImageView(this);
            Picasso.with(this).load(i).resize(metric.widthPixels,metric.heightPixels/4).into(iv);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            imageViews.add(iv);
        }
        mLv.setAdapter(new MyAdapter());

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HotelActivity.this,HotelDetails.class);
                intent.putExtra("flag",position+"");
                startActivity(intent);
            }
        });

        pagerAdapter = new ImagePage();
        pager.setPageTransformer(true, new ViewPagerRoation());
        pager.setAdapter(pagerAdapter);
        indicator.setViewPager(pager);
        pager.setAutoScrollDurationFactor(3);
        pager.startAutoScroll();
        indicator.setSnap(true);



    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = manager.beginTransaction();

        switch (v.getId()){
            case R.id.bt1:
                hideAll();
                if(mInternation==null){
                    mInternation = new Internationa_FA();
                }
                if(!mInternation.isAdded()){
                    transaction.add(R.id.content,mInternation);
                }
                transaction.show(mInternation);
                bt1.setTextColor(Color.rgb(121,174,210));
                bt2.setTextColor(Color.BLACK);
                bt3.setTextColor(Color.BLACK);
                v1.setBackgroundResource(R.color.news4);
                v2.setBackground(null);
                v3.setBackground(null);
                break;
            case R.id.bt2:
                hideAll();
                if(mHome==null){
                    mHome = new Home_FA();
                }
                if(!mHome.isAdded())
                    transaction.add(R.id.content,mHome);
                transaction.show(mHome);
                v2.setBackgroundResource(R.color.news4);
                v1.setBackground(null);
                v3.setBackground(null);
                bt2.setTextColor(Color.rgb(121,174,210));
                bt1.setTextColor(Color.BLACK);
                bt3.setTextColor(Color.BLACK);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.bt3:
                hideAll();
                if(mFlat==null)
                    mFlat = new Flat_FA();
                if(!mFlat.isAdded())
                    transaction.add(R.id.content,mFlat);
                transaction.show(mFlat);
                v3.setBackgroundResource(R.color.news4);
                v1.setBackground(null);
                v2.setBackground(null);
                bt3.setTextColor(Color.rgb(121,174,210));
                bt2.setTextColor(Color.BLACK);
                bt1.setTextColor(Color.BLACK);
                break;

            default:
                break;
        }

        transaction.commit();

    }

    class ImagePage extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(imageViews.get(position));
            return  imageViews.get(position);
            /*container.removeView(imageViews.get(position));
            container.addView(imageViews.get(position));*/


//            Bitmap bitmap = getBitmapFromMemCache(imgs[position]);
//            if(bitmap==null){
//                ImageView iv1 = new ImageView(getActivity());



            //imgView.setImageResource(imgs[i]);
            /*Bitmap bm = BitmapFactory.decodeResource(this.getResources(), imgs[i]);
            BitmapDrawable bd = new BitmapDrawable(this.getResources(), bm);
            imgView.setImageDrawable(bd);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);*/
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小

               /* final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                options.inSampleSize = Utils.calculateInSampleSize(options, width, height / 20);
                options.inJustDecodeBounds = false;
                bitmap =  BitmapFactory.decodeResource(getResources(), imgs[position], options);
                BitmapDrawable bd = new BitmapDrawable(getResources(), bm);
                iv1.setImageDrawable(bd);

                 iv1.setScaleType(ImageView.ScaleType.FIT_XY);
                addBitmapToMemoryCache(imgs[position],bitmap);*/

            //}
           /* BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
            Log.v("log", "hhh");
            iv.setImageDrawable(bd);

            container.addView(iv);*/

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));

        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    private void hideAll(){
        FragmentTransaction transaction = manager.beginTransaction();
        if(mHome!=null)
            transaction.hide(mHome);
        if(mFlat!=null)
            transaction.hide(mFlat);
        if(mInternation!=null)
            transaction.hide(mInternation);

        transaction.commit();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mapList.size();
        }

        @Override
        public Object getItem(int position) {
            return mapList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHodler viewHodler;
            if(convertView==null){
                viewHodler = new ViewHodler();
                convertView = LayoutInflater.from(HotelActivity.this).inflate(R.layout.lv_item,null);
                viewHodler.iv = (ImageView) convertView.findViewById(R.id.iv);
                viewHodler.tv1 = (TextView) convertView.findViewById(R.id.tv1);
                viewHodler.tv2 = (TextView) convertView.findViewById(R.id.tv2);
                convertView.setTag(viewHodler);
            }
            else{
                viewHodler = (ViewHodler) convertView.getTag();
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(HotelActivity.this.getResources(),(Integer) mapList.get(position).get("img"),options);
            Picasso.with(HotelActivity.this).load((Integer) mapList.get(position).get("img")).into(viewHodler.iv);
            viewHodler.tv1.setText((String) mapList.get(position).get("tv1"));
            viewHodler.tv2.setText((String) mapList.get(position).get("tv2"));
            return convertView;
        }
    }

    class ViewHodler{

        ImageView iv;
        TextView tv1,tv2;

    }

}
