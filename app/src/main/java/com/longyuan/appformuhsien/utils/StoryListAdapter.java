package com.longyuan.appformuhsien.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longyuan.appformuhsien.R;
import com.longyuan.appformuhsien.pojo.Story;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LONGYUAN on 2018/1/9.
 */

public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.StoryListAdapterViewHolder> {

    List<Story> mStoryList;

    Context mContext;

    private OnItemClickListener.OnStoryItemClickListener mOnItemClickListener;

    public StoryListAdapter(Context context,List<Story> storyList){

        mContext = context;
        mStoryList = storyList;

    }


    @Override
    public StoryListAdapter.StoryListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item,parent,false);

        return new StoryListAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoryListAdapter.StoryListAdapterViewHolder holder, int position) {

        Story story = mStoryList.get(position);

        holder.textViewTitle.setText(story.getTitle());

        if(story.getImageUrl() != null && story.getImageUrl().length()>0)
        {
            Glide.with(mContext).load(story.getImageUrl()).into(holder.imageView);
        }else
        {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(story);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStoryList.size() ;
    }

    public static class StoryListAdapterViewHolder extends RecyclerView.ViewHolder{


       @BindView(R.id.story_title)
        TextView textViewTitle;

        @BindView(R.id.image)
        ImageView imageView;

        public StoryListAdapterViewHolder(View itemView) {
            super(itemView);

            //textViewTitle = (TextView) itemView.findViewById(R.id.news_title);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener.OnStoryItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
