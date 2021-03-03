package com.demo.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.demo.myapplication.BaseFragment
import com.demo.myapplication.R
import com.demo.myapplication.model.TabModel
import com.demo.myapplication.utils.Constant
import com.demo.myapplication.view.adapter.ViewerPagerAdapter
import com.demo.myapplication.viewModel.DiscoverViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MusicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun layoutId(): Int = R.layout.fragment_music

    override fun viewPagerId(): Int  = R.id.pager
    override var fragmentName: String
        get() = Constant.MUSIC_FRAGMENT
        set(value) {}


//    override var tabList: ArrayList<TabModel>
//        get() = tabModelList
//        set(value) {}

//    private lateinit var tabLayout:TabLayout;
//    private lateinit var viewPager: ViewPager2;
//    var tabModelList : ArrayList<TabModel> = ArrayList()
    //    lateinit var adapter: ViewerPagerAdapter;

    private lateinit var viewModel: DiscoverViewModel
    var tabTitle: ArrayList<String> = ArrayList()



    /*private var tabViewPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            *//*Toast.makeText(context,
                "Selected position: $position",
                Toast.LENGTH_SHORT).show()*//*
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun setTabList( adapterViewPager: ViewerPagerAdapter, tabLayout: TabLayout, viewPager2: ViewPager2) {
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)


        val obj : JSONObject = JSONObject(Constant.loadJSONFromAsset(requireContext(), "music_tab.json"))

        viewModel.getTabList(obj).observe(viewLifecycleOwner, Observer { list ->
            Log.e("Result","Size"+list.size+":tabList"+tabList.size)
//            tabModelList = list;
//            tabList = tabModelList
            adapterViewPager.setTabList(list.size, list)


            //TODO:10 Connect TabLayout and ViewPager2 here
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                //To get the first name of  tab
                tab.text = list[position].title//.substringBefore(' ')
            }.attach()


        })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MusicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MusicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //override fun layoutId(): Int = R.layout.fragment_music;

/*

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(R.layout.fragment_music, container, false)

        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.pager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        adapter =
            activity?.let {
                ViewerPagerAdapter(
                    it,
                    tabModelList.size,
                    tabModelList, Constant.MUSIC_FRAGMENT
                )
            }!!
        viewPager.setAdapter(adapter)
        //viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tab))
,         viewPager.registerOnPageChangeCallback(tabViewPageChangeCallback)
        viewPager.isNestedScrollingEnabled = true

        return view;
    }
*/


/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)


        val obj : JSONObject = JSONObject(Constant.loadJSONFromAsset(requireContext(), "music_tab.json"))

        viewModel.getTabList(obj).observe(viewLifecycleOwner, Observer { list ->
            Log.e("Result","Size"+list.size)
            tabModelList = list;
            adapter.setTabList(list.size, list)


            //TODO:10 Connect TabLayout and ViewPager2 here
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                //To get the first name of doppelganger celebrities
                tab.text = tabModelList[position].title.substringBefore(' ')
            }.attach()


        })

    }
*/

   /* override fun onDestroy() {
        super.onDestroy()
        //TODO:6 Unregister page change callback here
        viewPager.unregisterOnPageChangeCallback(tabViewPageChangeCallback)
    }
*/

}