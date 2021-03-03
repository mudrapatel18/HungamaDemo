package com.demo.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.demo.myapplication.R
import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.presentation.view.adapter.ViewerPagerAdapter
import com.google.android.material.tabs.TabLayout

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int
    abstract  fun viewPagerId(): Int;
    abstract  var fragmentName : String
//    abstract  fun taLayoutId(): Int;
     lateinit var adapterViewPager : ViewerPagerAdapter
    var tabList : ArrayList<TabModel> = ArrayList()

    private lateinit var tabLayout:TabLayout;
    private lateinit var viewPager: ViewPager2;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
        {
            var view: View = inflater.inflate(layoutId(), container, false);
            tabLayout = view.findViewById(R.id.tabs)
            viewPager = view.findViewById(viewPagerId())
//            tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
            viewPager.isNestedScrollingEnabled = true
            viewPager.registerOnPageChangeCallback(tabViewPageChangeCallback)

            adapterViewPager =
                activity?.let {
                    ViewerPagerAdapter(
                        it,
                        tabList.size,
                        tabList, fragmentName
                    )
                }!!
            viewPager.setAdapter(adapterViewPager)

            return view;
        }

    open fun onBackPressed() {}

    abstract fun setTabList(adapterViewPager : ViewerPagerAdapter, tabLayout: TabLayout, viewPager2: ViewPager2)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTabList( adapterViewPager, tabLayout, viewPager)
    }
    private var tabViewPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            /*Toast.makeText(context,
                "Selected position: $position",
                Toast.LENGTH_SHORT).show()*/
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        viewPager.unregisterOnPageChangeCallback(tabViewPageChangeCallback)

    }

}