package edu.uco.weddingcrashers.hitched;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by rush_yu on 2016/3/19 16:46
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class AreaActivity extends FragmentActivity implements View.OnClickListener{

    ArrayList<HoneymoonItem> honeymoonItems;
    HoneymoonAdapter honeymoonAdapter;
    ListView honeymoonView;

    private AutoScrollViewPager pager;

    private TextView tv;
    private TextView mBack;

    private View v1,v2,v3;

    private Button bt1,bt2,bt3;

    private int[] imgs2={R.drawable.ya1,R.drawable.ya2,R.drawable.ya3,R.drawable.ya4
            ,R.drawable.ya5,R.drawable.ya6,R.drawable.ya7,R.drawable.ya8,R.drawable.ya9,R.drawable.ya10
            ,R.drawable.ya11};

    private Fragment mInternation,mHome,mFlat;
     private ImagePage pagerAdapter;
    private TextView back;

    private CirclePageIndicator indicator;

    private int[] imgs = {R.drawable.yuxia2,R.drawable.yuxia6,R.drawable.yuxia7};

    private List<ImageView> imageViews;

    private Button flight,hotel,area;
    private DisplayMetrics metric;

    private TextView mUsa,mCaribbean,mEurope,mMexico,mCanada,mAsia;

    private RelativeLayout content;
    private ListView lv;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);
        lv = (ListView) findViewById(R.id.lv);
        pager = (AutoScrollViewPager) findViewById(R.id.autopager);
       /* honeymoonAdapter = new HoneymoonAdapter(this, honeymoonItems);
        //gridView.setAdapter(honeymoonAdapter);
        honeymoonView = (ListView) findViewById(R.id.itemizedHoneymoonList);
        honeymoonView.setAdapter(honeymoonAdapter);*/
        content = (RelativeLayout) findViewById(R.id.content);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        imageViews = new ArrayList<ImageView>();
        mUsa = (TextView) findViewById(R.id.USA);
        mUsa.setOnClickListener(this);
        mCaribbean = (TextView) findViewById(R.id.Caribbean);
        mCaribbean.setOnClickListener(this);
        mEurope = (TextView) findViewById(R.id.Europe);
        mEurope.setOnClickListener(this);
        mMexico = (TextView) findViewById(R.id.Mexico);
        mMexico.setOnClickListener(this);
        mCanada = (TextView) findViewById(R.id.Canada);
        mCanada.setOnClickListener(this);
        mAsia = (TextView) findViewById(R.id.Asia);
        mAsia.setOnClickListener(this);
        back = (TextView) findViewById(R.id.back);
        manager = getSupportFragmentManager();
        back.setOnClickListener(this);
        for(int i:imgs){
            ImageView iv = new ImageView(this);
            Picasso.with(this).load(i).into(iv);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            imageViews.add(iv);
        }
        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        lv.setAdapter(new MyAdapter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag",(position+7)+"");
                startActivity(intent);
            }
        });

        v1=findViewById(R.id.v1);
        v2=findViewById(R.id.v2);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        mBack = (TextView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaActivity.this.finish();
            }
        });



        bt2.setOnClickListener(this);
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
        Intent intent;
        switch (v.getId()){
            case R.id.back:
                this.finish();
                break;
            case R.id.bt1:
                bt1.setTextColor(Color.rgb(121,174,210));
                v1.setBackgroundResource(R.color.news4);
                bt2.setTextColor(Color.BLACK);
                v2.setBackground(null);
                transaction.replace(R.id.content,new Near_Fa());
                break;
            case R.id.bt2:
                bt1.setTextColor(Color.BLACK);
                v1.setBackground(null);
                bt2.setTextColor(Color.rgb(121,174,210));
                v2.setBackgroundResource(R.color.news4);
                transaction.replace(R.id.content,new Near_Fa());
                 break;
            case R.id.USA:
                 intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","18");
                startActivity(intent);
                break;
            case R.id.Caribbean:
                 intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","19");
                startActivity(intent);
                break;
            case R.id.Europe:
                intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","20");
                startActivity(intent);
                break;
            case R.id.Mexico:
                intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","21");
                startActivity(intent);
                break;
            case R.id.Canada:
                intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","22");
                startActivity(intent);
                break;
            case R.id.Asia:
                //Toast.makeText(this,"hh",Toast.LENGTH_LONG).show();
                intent = new Intent(AreaActivity.this,HotelDetails.class);
                intent.putExtra("flag","23");
                startActivity(intent);
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

    class  MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return imgs2.length;
        }

        @Override
        public Object getItem(int position) {
            return imgs2[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(AreaActivity.this).inflate(R.layout.image_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Picasso.with(AreaActivity.this).load(imgs2[position]).resize(metric.widthPixels,metric.heightPixels/6).into(viewHolder.iv);
            return convertView;

        }
    }

    class ViewHolder{
        ImageView iv;
    }
    public static int px2dip(Context context,float value){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value/scale + 0.5f);
    }
}
