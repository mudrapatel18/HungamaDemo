package com.demo.myapplication.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.demo.myapplication.R
import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.utils.Constant
import com.demo.myapplication.presentation.view.adapter.ViewerPagerAdapter
import com.demo.myapplication.presentation.viewModel.DiscoverViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoverFragmentOld : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var tabLayout:TabLayout;
    private lateinit var viewPager:ViewPager2;
    var tabTitle: ArrayList<String> = ArrayList()
//    private lateinit var doppelgangerNamesArray: Array<String>
    var tabModelList : ArrayList<TabModel> = ArrayList()
    private lateinit var viewModel: DiscoverViewModel
    lateinit var adapter: ViewerPagerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var tabViewPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            /*Toast.makeText(context,
                "Selected position: $position",
                Toast.LENGTH_SHORT).show()*/
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_discover, container, false)

        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.pager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

//        for (l in 0..5) {
//            tabLayout.addTab(tabLayout.newTab().setText("Pitch-$l"))
//            tabTitle.add("P - $l")
//        }

       // doppelgangerNamesArray = resources.getStringArray(R.array.doppelganger_names)

       /* val obj = JSONObject(loadJSONFromAsset(requireContext(), "discover_tab.json"))
        val isSuccess = obj.getString("message")

        if(isSuccess.equals("Success", true)) {
            val arrayTab = obj.getJSONArray("data")
            for (i in 0 until arrayTab.length()) {
                val detail = arrayTab.getJSONObject(i)
                var tabModel: TabModel =
                    TabModel(
                        detail.getInt("id"),
                        detail.getString("title")
                    )
                tabModelList.add(tabModel)
            }
        }*/

        adapter =
            activity?.let {
                ViewerPagerAdapter(
                    it,
                    tabModelList.size,
                    tabModelList, Constant.DISCOVER_FRAGMENT
                )
            }!!
        viewPager.setAdapter(adapter)
        //viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tab))
        viewPager.registerOnPageChangeCallback(tabViewPageChangeCallback)
        viewPager.isNestedScrollingEnabled = true
        //viewPager.isUserInputEnabled = false

        //TODO:7 Change ViewPager2 orientation here
//    viewPager.orientation = ORIENTATION_VERTICAL

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)


        val obj : JSONObject = JSONObject(Constant.loadJSONFromAsset(requireContext(), "discover_tab.json"))

        viewModel.tabListLiveDataClean.observe(viewLifecycleOwner, Observer { list ->
            Log.e("Result","Size"+list.size)
            tabModelList = list;
            adapter.setTabList(list.size, list)


            //TODO:10 Connect TabLayout and ViewPager2 here
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                //To get the first name of tab
                tab.text = tabModelList[position].title.substringBefore(' ')
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
         * @return A new instance of fragment DiscoverFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscoverFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO:6 Unregister page change callback here
        viewPager.unregisterOnPageChangeCallback(tabViewPageChangeCallback)
    }
}