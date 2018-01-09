package com.longyuan.appformuhsien.storydetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.longyuan.appformuhsien.R;
import com.longyuan.appformuhsien.injection.DaggerNetworkComponent;
import com.longyuan.appformuhsien.injection.NetworkModule;
import com.longyuan.appformuhsien.pojo.Story;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LONGYUAN on 2018/1/9.
 */

public class StoryDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STORY_ID = "STORY_ID";

    public static final String SHORT_COMMENTS_NUMBER = "SHORT_COMMENTS_NUMBER";

    public static final String LONG_COMMENTS_NUMBER = "LONG_COMMENTS_NUMBER";

    public static final String USE_VOLLEY = "USE_VOLLEY";

    static final List<Story> mStories = new ArrayList<Story>() {{
        add(new Story("鬼故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/ib8b6bf5a5f687f7c/version/1513689979/image.jpg"));
        add(new Story("生活中的佛法","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/if2b89d6d526da9e7/version/1514316612/image.jpg"));
        add(new Story("輪迴小故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/i6dfb057ee1a6882c/version/1513693386/image.jpg"));
        add(new Story("歷史小故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/i91b0de0af84a8385/version/1514028055/image.jpg"));
    }};

/*    @Inject
    StoryService mStoryService;*/

    @BindView(R.id.image_storydetail)
    ImageView mImageView;

    @BindView(R.id.webview_storydetail)
    WebView mWebView;

    @BindView(R.id.text_storydetail)
    TextView mTextView;

    //@BindView(R.id.action_comments)
    MenuItem mMenuItemComments;

    //@BindView(R.id.action_fav)
    MenuItem mMenuItemFav;

    //@BindView(R.id.action_like)
    MenuItem mMenuItemLike;

    //@BindView(R.id.action_share)
    MenuItem mMenuItemShare;

    @BindView(R.id.storydetail_comments_counter)
    TextView mTextViewComments;

    @BindView(R.id.storydetail_popularity)
    TextView mTextViewCommentsPopularity;

    @BindView(R.id.storydetail_short_comments_counter)
    TextView mTextViewShortComments;

    @BindView(R.id.storydetail_long_comments_counter)
    TextView mTextViewLongComments;


    @Inject
    RequestQueue mRequestQueue;


    private String storyId;

    private String mShortCommentsNumber;

    private String mLongCommentsNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storydetail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);

        storyId = getIntent().getStringExtra(EXTRA_STORY_ID);

        boolean useVolley = getIntent().getBooleanExtra(USE_VOLLEY,false);


        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://news-at.zhihu.com/api/4/",getApplicationContext()))
                .build().inject(this);


        if(useVolley){

            getDataWithVolley(storyId);

        }else
        {
            getDataWithRetrofit(storyId);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     /*   getMenuInflater().inflate(R.menu.menu_storydetail, menu);

        mMenuItemComments = menu.findItem(R.id.action_comments);

        mMenuItemLike = menu.findItem(R.id.action_like);*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_comments) {

            Intent intent = new Intent(getApplicationContext(),CommentActivity.class);
            intent.putExtra(EXTRA_STORY_ID, storyId);
            intent.putExtra(SHORT_COMMENTS_NUMBER, mShortCommentsNumber);
            intent.putExtra(LONG_COMMENTS_NUMBER, mLongCommentsNumber);
            startActivity(intent);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    private void getDataWithVolley(String storyId){

        String url = "https://news-at.zhihu.com/api/4/news/"+storyId;
        // Volley use customized Request to parse json
       /* GsonRequest<StoryDetail> gsonRequest = new GsonRequest<>(url, StoryDetail.class, null, new Response.Listener<StoryDetail>() {
            @Override
            public void onResponse(StoryDetail response) {

                Glide.with(getApplicationContext()).load(response.getImage()).into(mImageView);

                mTextView.setText(response.getTitle());

                mWebView.loadData(response.getBody(),"text/html",null);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(gsonRequest);*/

    }

    private void getDataWithRetrofit(String storyId){

       /* mStoryService.getStoryDetail(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processData(data),
                        throwable -> processError(throwable));
*/

    /*    mStoryService.getStoryExtraInfo(storyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> processExtraInfo(data),
                        throwable -> processError(throwable));*/

        processData(mStories.get(Integer.parseInt(storyId)-1 ));

    }

    private void processData(Story storyDetail){

        Glide.with(getApplicationContext()).load(storyDetail.getImageUrl()).into(mImageView);

        mTextView.setText(storyDetail.getTitle());

       // mWebView.loadData(storyDetail.getBody(),"text/html",null);

        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl("https://supportive-dharma.jimdo.com/blog/" + storyDetail.getTitle());
    }

  /*  private void processExtraInfo(StoryExtraInfo storyExtraInfo){

        mTextViewComments.setText(storyExtraInfo.getComments());

        mTextViewCommentsPopularity.setText(storyExtraInfo.getPopularity());

        mMenuItemComments.setTitle(storyExtraInfo.getComments());

        mMenuItemLike.setTitle(storyExtraInfo.getPopularity());

        mShortCommentsNumber = storyExtraInfo.getShortComments();

        mLongCommentsNumber = storyExtraInfo.getLongComments();

        mTextViewShortComments.setText(mShortCommentsNumber);

        mTextViewLongComments.setText(mLongCommentsNumber);

    }*/

    private void processError(Throwable e) {

        Log.e("Test", e.getLocalizedMessage(), e);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().startsWith("supportive-dharma.jimdo.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

}

