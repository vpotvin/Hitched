package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * 作者：Created by rush_yu on 2016/3/12 13:07
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
    public class SodukuGridView extends GridView {

        public SodukuGridView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        public SodukuGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
        }

        public SodukuGridView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // TODO Auto-generated method stub
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }

    }

