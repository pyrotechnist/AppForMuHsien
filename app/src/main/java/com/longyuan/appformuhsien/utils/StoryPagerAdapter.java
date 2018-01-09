package com.longyuan.appformuhsien.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.appformuhsien.R;
import com.longyuan.appformuhsien.pojo.Story;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/1/9.
 */

public class StoryPagerAdapter extends PagerAdapter {
    Context mContext;

    List<Story> mTopStories;

    private OnItemClickListener.OnStoryItemClickListener mOnItemClickListener;

    public StoryPagerAdapter(Context context, List<Story> imageList) {
        this.mContext = context;
        mTopStories = imageList;
    }


    @Override
    public int getCount() {
        return mTopStories.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((View) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {

        View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.topstory_item,container,false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_topStory);

        TextView textView  = (TextView) itemView.findViewById(R.id.title_topStory);

        Story topStory = mTopStories.get(i);

        Glide.with(mContext).load(topStory.getImageUrl()).centerCrop().into(imageView);

        ((ViewPager) container).addView(itemView, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(topStory);
            }
        });

        textView.setText(topStory.getTitle());

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((View) obj);
    }

    public void updateData(List<Story> topStories){

        mTopStories = topStories;

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener.OnStoryItemClickListener onItemClickListener) {

        this.mOnItemClickListener = onItemClickListener;
    }
}