package com.longyuan.appformuhsien.injection;

import com.longyuan.appformuhsien.MainActivity;
import com.longyuan.appformuhsien.storydetail.StoryDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/1/6.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(MainActivity mainActivity);

    void inject(StoryDetailActivity storyDetailActivity);

}
