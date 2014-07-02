package com.pluralsight.coursebrowser;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by morrisonw on 6/30/2014.
 */
public class CoursePagerAdapter extends FragmentPagerAdapter {
    String[] mCourseTitles;
    String[] mCourseTitlesShort;
    String[] mCourseDescriptions;

    public CoursePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        Resources resources = context.getResources();
        mCourseTitles = resources.getStringArray(R.array.course_titles);
        mCourseTitlesShort = resources.getStringArray(R.array.course_titles_short);
        mCourseDescriptions = resources.getStringArray(R.array.course_descriptions);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putString(CourseFragment.COURSE_TITLE, mCourseTitles[position]);
        arguments.putString(CourseFragment.COURSE_DESCRIPTION, mCourseDescriptions[position]);
        arguments.putInt(CourseFragment.TOP_CARD, translateTopCardIndex(position));
        arguments.putInt(CourseFragment.COURSE_TYPE_LOGO, R.drawable.pagefit_logo_small);

        CourseFragment courseFragment = new CourseFragment();
        courseFragment.setArguments(arguments);
        return courseFragment;
    }

    private int translateTopCardIndex(int position) {
        int resourceId = 0;

        switch (position) {
            case 0:
                resourceId = R.drawable.shutterstock_1;
                break;
            case 1:
                resourceId = R.drawable.shutterstock_2;
                break;
            case 2:
                resourceId = R.drawable.shutterstock_3;
                break;
            case 3:
                resourceId = R.drawable.shutterstock_4;
                break;
            case 4:
                resourceId = R.drawable.shutterstock_5;
                break;
            case 5:
                resourceId = R.drawable.shutterstock_6;
                break;
            case 6:
                resourceId = R.drawable.shutterstock_7;
                break;
        }

        return resourceId;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCourseTitlesShort[position];
    }

    @Override
    public int getCount() {
        return mCourseTitles.length;
    }
}
