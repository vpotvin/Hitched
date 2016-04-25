package edu.uco.weddingcrashers.hitched;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作者：Created by rush_yu on 2016/3/21 13:13
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class Near_Fa extends Fragment implements XListView.IXListViewListener{
    private SwipeMenuAdapter mAdapter;
    ArrayList<HoneymoonItem> honeymoonItems;

    HoneymoonAdapter honeymoonAdapter;

    private Handler mHandler = new Handler();

    private List<Map<String,Object>> mapList;

    private View view;


    private SwipeMenuListView swipeMenuListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.near_fa,container,false);
        initUI();
        return view;
    }

    private void initUI() {
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.lv);
        swipeMenuListView.setPullLoadEnable(true);
        swipeMenuListView.setXListViewListener(this);
        honeymoonItems=new ArrayList<>();

        HoneymoonItem temp=new HoneymoonItem("Santorini",R.mipmap.santori,"Known for its brilliant sunsets, rich Greek food and romantic hotels, Santorini is almost tailor-made for those who've just said I do Honeymooners can lounge on red- and black-sand beaches or visit the island's wineries.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Bora Bora",R.mipmap.bora,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Maldives",R.mipmap.maldives,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);
        temp=new HoneymoonItem("Maui",R.mipmap.maui,"This is a good place to travel when you are about dieing.");
        honeymoonAdapter = new HoneymoonAdapter(getActivity(), honeymoonItems);
        swipeMenuListView.setAdapter(honeymoonAdapter);    honeymoonItems.add(temp);

       /* temp=new HoneymoonItem("Tahiti",R.mipmap.tahiti,"This is a good place to travel when you are about dieing.");
        honeymoonItems.add(temp);*/


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(Utils.dp2px(getActivity(),90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(Utils.dp2px(getActivity(),90));
                // set a icon
                deleteItem.setIcon(R.mipmap.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        swipeMenuListView.setMenuCreator(creator);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // geneItems();
                honeymoonAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // geneItems();
                honeymoonAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);

    }

    private void onLoad() {
        swipeMenuListView.stopRefresh();
        swipeMenuListView.stopLoadMore();
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        swipeMenuListView.setRefreshTime(df.format(day));
    }
}
