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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final Map<Integer,String> mStories = new HashMap<Integer,String>() {{
        put(0,"鬼故事");
        put(1,"生活中的佛法");
        put(2,"輪迴小故事");
        put(3,"歷史小故事");
    }};


    @BindView(R.id.storiess_list)
    RecyclerView mStoryList;

    @BindView(R.id.viewPage_topStories)
    ViewPager viewPagerTopStories;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("https://supportive-dharma.jimdo.com/blog/",getApplicationContext()))
                .build().inject(this);

        setRecyclerView();
        //setupViewPager();
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


    }

    private void setupNavigationView(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mMenu = navigationView.getMenu();

        //mMenu.add(Menu.NONE, 1, Menu.FIRST, mStories.get(0));


        //mStories.forEach(item-> mMenu.add(Menu.NONE,Menu.NONE, Menu.NONE, item));

       /* mMenu.add(Menu.NONE, 1, 0, mStories.get(0));
        mMenu.add(Menu.NONE, 1, 1, mStories.get(1));
        mMenu.add(Menu.NONE, 1,2, mStories.get(2));
        mMenu.add(Menu.NONE, 1, 3, mStories.get(3));*/

        for (Map.Entry<Integer,String> entry : mStories.entrySet()) {

            mMenu.add(0, entry.getKey(),entry.getKey(), entry.getValue());
        }

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
            Toast.makeText(this,mStories.get(0),Toast.LENGTH_LONG).show();
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
