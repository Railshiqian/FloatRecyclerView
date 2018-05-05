package com.example.shiqian.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.shiqian.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3 0003.
 * E-Mail：543441727@qq.com
 */

public class FloatingItemDecoration1 extends RecyclerView.ItemDecoration {
    private static final String TAG = "FloatingItemDecoration";
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private int dividerHeight;
    private int dividerWidth;
    private Map<Integer, String> keys = new HashMap<>();
    private int mTitleHeight;
    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private float mTextHeight;
    private float mTextBaselineOffset;
    private Context mContext;
    private int whiteSpaceHeight = 10;
    private Paint whiteSpacePaint;
    /**
     * 滚动列表的时候是否一直显示悬浮头部
     */
    private boolean showFloatingHeaderOnScrolling = true;

    private int headHeight;
    private View headView;
    private String headStr = "Select Your City";
    private Paint headTextPaint, headBgPaint;
    private Rect mBound;


    public FloatingItemDecoration1(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.dividerHeight = mDivider.getIntrinsicHeight();
        this.dividerWidth = mDivider.getIntrinsicWidth();
        init(context);
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param drawableId 分割线图片
     */
    public FloatingItemDecoration1(Context context, @DrawableRes int drawableId) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
        this.dividerHeight = mDivider.getIntrinsicHeight();
        this.dividerWidth = mDivider.getIntrinsicWidth();
        init(context);
    }

    /**
     * 自定义分割线
     * 也可以使用{@link Canvas#drawRect(float, float, float, float, Paint)}或者{@link Canvas#drawText(String, float, float, Paint)}等等
     * 结合{@link Paint}去绘制各式各样的分割线
     *
     * @param context
     * @param color         整型颜色值，非资源id
     * @param dividerWidth  单位为dp
     * @param dividerHeight 单位为dp
     */
    public FloatingItemDecoration1(Context context, @ColorInt int color, @Dimension float dividerWidth, @Dimension float dividerHeight) {
        mDivider = new ColorDrawable(color);
        this.dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, context.getResources().getDisplayMetrics());
        this.dividerHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerHeight, context.getResources().getDisplayMetrics());
        init(context);
    }

    private void init(Context mContext) {
        this.mContext = mContext;
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, mContext.getResources().getDisplayMetrics()));
        mTextPaint.setColor(Color.WHITE);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        mTextPaint.setTypeface( font );
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTextHeight = fm.bottom - fm.top;//计算文字高度
        mTextBaselineOffset = fm.bottom;

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(Color.parseColor("#998cb9"));


        //
        headView = LayoutInflater.from(mContext).inflate(R.layout.float_head_layout, null);
        headView.measure(0, 0);
        headHeight = mContext.getResources().getDimensionPixelSize(R.dimen.title_height);

        headTextPaint = new Paint();
        headTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, mContext.getResources().getDisplayMetrics()));
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        headTextPaint.getTextBounds(headStr, 0, headStr.length(), mBound);
        headTextPaint.setColor(Color.WHITE);

        headBgPaint = new Paint();
        headBgPaint.setAntiAlias(true);
        headBgPaint.setColor(Color.BLUE);

        whiteSpacePaint = new Paint();
        whiteSpacePaint.setAntiAlias(true);
        whiteSpacePaint.setColor(Color.WHITE);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
    }

    private String nextTitle = null;
    private String currentTitle = null;

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (!showFloatingHeaderOnScrolling) {
            return;
        }
        int firstVisiblePos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisiblePos == RecyclerView.NO_POSITION) {
            return;
        }

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        View firstView = parent.getChildAt(0);
        int firstViewBottom = firstView.getBottom();

//do not show title
        if (firstVisiblePos == 0 && firstViewBottom >= headHeight+whiteSpaceHeight) {//only draw "Select Your City"
            int floatTop = firstView.getBottom() - headHeight-whiteSpaceHeight;
            c.drawRect(left, floatTop, right, firstViewBottom-whiteSpaceHeight, headBgPaint);
            c.drawText(headStr, (right - left) / 2 - mBound.width() / 2, firstViewBottom-whiteSpaceHeight - headHeight / 2 + mBound.height() / 2, headTextPaint);
            c.drawRect(left,firstView.getBottom()-whiteSpaceHeight,right,firstView.getBottom(),whiteSpacePaint);
            return;
        }

        View v = parent.findChildViewUnder(left + 1, headHeight + mTitleHeight+whiteSpaceHeight + 1);
        int currentTitlePosition = parent.getChildPosition(v);
        if (currentTitlePosition == -1) {
            v = parent.findChildViewUnder(left + 1, headHeight + 2 * mTitleHeight +whiteSpaceHeight+ 1);
            currentTitlePosition = parent.getChildPosition(v) - 1;
        }
        Log.w("tag", "currentTitlePosition:" + currentTitlePosition);

        int nextTitlePosition = findNextTitlePosition(currentTitlePosition);
        currentTitle = getTitle(currentTitlePosition);
        nextTitle = keys.get(nextTitlePosition);


        View nextView = parent.findViewHolderForAdapterPosition(nextTitlePosition).itemView;
        float nextViewTop = nextView.getTop();

        if (nextViewTop > headHeight + mTitleHeight * 2+whiteSpaceHeight) {//drow "A"
            Log.d("tag", "flag1");
            int top = parent.getPaddingTop() + headHeight+whiteSpaceHeight;
            int bottom = top + mTitleHeight;
            c.drawRoundRect(left+whiteSpaceHeight, top, right-whiteSpaceHeight, bottom,5,5, mBackgroundPaint);
            float x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());//left 10dp
            float y = bottom - (mTitleHeight - mTextHeight) / 2 - mTextBaselineOffset;//计算文字baseLine
            c.drawText(currentTitle, x, y, mTextPaint);

        } else if (nextViewTop <= 2 * mTitleHeight + headHeight+whiteSpaceHeight && nextViewTop > mTitleHeight + headHeight+whiteSpaceHeight) {
            Log.d("tag", "flag2");
            //drow "A" and "B"
            int top = nextView.getTop() - 2 * mTitleHeight;
            int bottom = top + mTitleHeight;
            c.drawRoundRect(left+whiteSpaceHeight, top, right-whiteSpaceHeight, bottom,5,5, mBackgroundPaint);
            float x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());//left 10dp
            float y = bottom - (mTitleHeight - mTextHeight) / 2 - mTextBaselineOffset;//计算文字baseLine
            c.drawText(currentTitle, x, y, mTextPaint);

            int top2 = nextView.getTop() - mTitleHeight;
            int bottom2 = nextView.getTop();
            c.drawRoundRect(left+whiteSpaceHeight, top2, right-whiteSpaceHeight, bottom2,5,5, mBackgroundPaint);
//            float x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());//left 10dp
            y = bottom2 - (mTitleHeight - mTextHeight) / 2 - mTextBaselineOffset;//计算文字baseLine
            c.drawText(nextTitle, x, y, mTextPaint);
        }

//draw "Select Your City"
        int floatTop = parent.getPaddingTop();
        c.drawRect(left, floatTop, right, headHeight + floatTop, headBgPaint);
        c.drawText(headStr, (right - left) / 2 - mBound.width() / 2, floatTop + headHeight / 2 + mBound.height() / 2, headTextPaint);

        c.drawRect(left,floatTop+headHeight,right,floatTop+headHeight+whiteSpaceHeight,whiteSpacePaint);

//        c.drawRound

    }

    private int findNextTitlePosition(int currentPosition) {

        for (int i = currentPosition + 1; i <= maxTitlePosition; i++) {
            if (keys.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxTitlePosition() {
        int maxTitlePosition = -1;
        Set<Integer> values = keys.keySet();// 得到全部的key
        Iterator<Integer> iter = values.iterator();
        while (iter.hasNext()) {
            int temp = iter.next();
            maxTitlePosition = maxTitlePosition >= temp ? maxTitlePosition : temp;
        }
        return maxTitlePosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildViewHolder(view).getAdapterPosition();
        if (keys.containsKey(pos)) {//留出头部偏移
            outRect.set(0, mTitleHeight, 0, 0);
        }
    }

    /**
     * *如果该位置没有，则往前循环去查找标题，找到说明该位置属于该分组
     *
     * @param position
     * @return
     */
    private String getTitle(int position) {
        while (position >= 0) {
            if (keys.containsKey(position)) {
                return keys.get(position);
            }
            position--;
        }
        return null;
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft()+whiteSpaceHeight;
        int right = parent.getWidth() - parent.getPaddingRight()-whiteSpaceHeight;
        int top = 0;
        int bottom = 0;


        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (keys.containsKey(params.getViewLayoutPosition())) {
                top = child.getTop() - params.topMargin - mTitleHeight;
                bottom = top + mTitleHeight;
                c.drawRoundRect(left, top, right, bottom,5,5, mBackgroundPaint);
                float x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
                float y = bottom - (mTitleHeight - mTextHeight) / 2 - mTextBaselineOffset;//计算文字baseLine
                c.drawText(keys.get(params.getViewLayoutPosition()), x, y, mTextPaint);
            }

        }

    }

    public void setShowFloatingHeaderOnScrolling(boolean showFloatingHeaderOnScrolling) {
        this.showFloatingHeaderOnScrolling = showFloatingHeaderOnScrolling;
    }

    private int maxTitlePosition = -1;

    public void setKeys(Map<Integer, String> keys) {
        this.keys.clear();
        this.keys.putAll(keys);
        dealKeyList();
        maxTitlePosition = getMaxTitlePosition();
    }

    private ArrayList<Integer> keyList = new ArrayList<>();

    private void dealKeyList() {
        keyList.clear();
        Set<Integer> values = keys.keySet();// 得到全部的key
        Iterator<Integer> iter = values.iterator();
        while (iter.hasNext()) {
            keyList.add(iter.next());
        }
        Collections.sort(keyList);
    }

    public void setmTitleHeight(int titleHeight) {
        this.mTitleHeight = titleHeight;
    }
}
