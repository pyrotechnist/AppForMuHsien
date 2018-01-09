package com.longyuan.appformuhsien;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.longyuan.appformuhsien.injection.DaggerNetworkComponent;
import com.longyuan.appformuhsien.injection.NetworkModule;
import com.longyuan.appformuhsien.pojo.Story;
import com.longyuan.appformuhsien.storydetail.StoryDetailActivity;
import com.longyuan.appformuhsien.utils.OnItemClickListener;
import com.longyuan.appformuhsien.utils.StoryListAdapter;
import com.longyuan.appformuhsien.utils.StoryPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.longyuan.appformuhsien.storydetail.StoryDetailActivity.EXTRA_STORY_ID;
import static com.longyuan.appformuhsien.storydetail.StoryDetailActivity.USE_VOLLEY;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final List<Story> mStories = new ArrayList<Story>() {{
        add(new Story("1","鬼故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/ib8b6bf5a5f687f7c/version/1513689979/image.jpg"));
        add(new Story("2","生活中的佛法","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/if2b89d6d526da9e7/version/1514316612/image.jpg"));
        add(new Story("3","輪迴小故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/i6dfb057ee1a6882c/version/1513693386/image.jpg"));
        add(new Story("4","歷史小故事","https://image.jimcdn.com/app/cms/image/transf/none/path/s15c9ef97e6bf0b66/image/i91b0de0af84a8385/version/1514028055/image.jpg"));
    }};


    @BindView(R.id.storiess_list)
    RecyclerView mStoryList;

    @BindView(R.id.viewPage_topStories)
    ViewPager viewPagerTopStories;

    private Menu mMenu;

    private StoryListAdapter mStoryListAdapter;

    private StoryPagerAdapter mTopStoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        ButterKnife.bind(this);

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://supportive-dharma.jimdo.com/blog/",getApplicationContext()))
                .build().inject(this);

        setRecyclerView();
        setupViewPager();
        setupNavigationView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setRecyclerView() {

        List<Story> storyList= new ArrayList();

        mStoryListAdapter = new StoryListAdapter(this,mStories);


        storyList = mStories;
        mStoryList.setAdapter(mStoryListAdapter);

        mStoryList.setLayoutManager(new LinearLayoutManager(mStoryList.getContext()));

        mStoryList.setNestedScrollingEnabled(false);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(mStoryList.getContext(),
                DividerItemDecoration.VERTICAL);

        mStoryListAdapter.setOnItemClickListener(new OnItemClickListener.OnStoryItemClickListener() {
            @Override
            public void onItemClick(Story item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, false);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(Story item, int position) {

            }
        });

    }

    private void setupViewPager(){

        List<Story> topStories = new ArrayList<>();

        mTopStoriesAdapter = new StoryPagerAdapter(this,mStories);

        mTopStoriesAdapter.setOnItemClickListener(new OnItemClickListener.OnStoryItemClickListener() {
            @Override
            public void onItemClick(Story item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),StoryDetailActivity.class);
                intent.putExtra(EXTRA_STORY_ID, item.getId());

                intent.putExtra(USE_VOLLEY, false);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(Story item, int position) {

            }
        });


        viewPagerTopStories.setAdapter(mTopStoriesAdapter);


    }

    private void setupNavigationView(){

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mMenu = navigationView.getMenu();

        //mMenu.add(Menu.NONE, 1, Menu.FIRST, mStories.get(0));


        //mStories.forEach(item-> mMenu.add(Menu.NONE,Menu.NONE, Menu.NONE, item));

       /* mMenu.add(Menu.NONE, 1, 0, mStories.get(0));
        mMenu.add(Menu.NONE, 1, 1, mStories.get(1));
        mMenu.add(Menu.NONE, 1,2, mStories.get(2));
        mMenu.add(Menu.NONE, 1, 3, mStories.get(3));*/

      /*  for (Map.Entry<Integer,String> entry : mStories.entrySet()) {

            mMenu.add(0, entry.getKey(),entry.getKey(), entry.getValue());
        }*/

       /* mStories.forEach(new Consumer<String>() {
                          @Override
                          public void accept(String story) {

                              mMenu.add(Menu.NONE, 1, Menu.FIRST, story);

                          }
                      }
        );*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            //Toast.makeText(this,mStories.get(0),Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if  (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
