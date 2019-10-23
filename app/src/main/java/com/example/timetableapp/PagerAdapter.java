package com.example.timetableapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
     public PagerAdapter(FragmentManager fm,int numOfTabs) {
         super(fm);
         this.mNoOfTabs = numOfTabs;
     }


    @Override
    public Fragment getItem(int position) {
         switch (position)
         {
             case 0: Tab1 tab1 = new Tab1();
             return tab1;

             case 1: Tab2 tab2 = new Tab2();
             return tab2;


             case 2: Tab3 tab3 = new Tab3();
             return tab3;

             case 3: Tab4 tab4 = new Tab4();
             return tab4;

             default: return null;
         }

    }

    @Override
    public int getCount() {
        return 0;
    }
}
