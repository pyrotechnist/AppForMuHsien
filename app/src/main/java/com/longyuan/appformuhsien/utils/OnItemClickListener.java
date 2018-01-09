package com.longyuan.appformuhsien.utils;

import com.longyuan.appformuhsien.pojo.Story;

/**
 * Created by loxu on 20/10/2017.
 */



public interface OnItemClickListener {

    interface OnStoryItemClickListener{

    void onItemClick(Story item);

    void onItemLongClick(Story item, int position);

    }

   /* interface OnCommentItemClickListener{

        void onItemClick(CommentItem item);

        void onItemLongClick(CommentItem item, int position);
    }*/
}
