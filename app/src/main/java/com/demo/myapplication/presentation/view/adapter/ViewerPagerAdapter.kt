package com.demo.myapplication.view.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.myapplication.model.TabModel
import com.demo.myapplication.view.fragment.ViewPagerFragment


class ViewerPagerAdapter(activity: FragmentActivity, var mNumOfTabs: Int, var tabTitleList: ArrayList<TabModel>, var fragmentName  : String) :
    FragmentStateAdapter(activity) {




    override fun getItemCount(): Int {
        return mNumOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.getInstance(position, tabTitleList.get(position).title, fragmentName)

    }

    internal fun setTabList(size : Int, list: ArrayList<TabModel>) {
        tabTitleList = list
        mNumOfTabs = size
//        Log.e("Result", "AdapterRecent"+ recentList.size)
        notifyDataSetChanged()
    }


}