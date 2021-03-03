package com.demo.myapplication.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.demo.myapplication.presentation.BaseFragment
import com.demo.myapplication.R
import com.demo.myapplication.createFactory
import com.demo.myapplication.data.repository.AppRepoImp
import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.domain.respository.AppRepository
import com.demo.myapplication.domain.usecase.GetDiscoverUseCase
import com.demo.myapplication.utils.Constant
import com.demo.myapplication.presentation.view.adapter.ViewerPagerAdapter
import com.demo.myapplication.presentation.viewModel.DiscoverViewModel
import com.demo.myapplication.utils.Log
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DiscoverFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun layoutId(): Int  = R.layout.fragment_discover

    override fun viewPagerId(): Int =  R.id.pager;
    override var fragmentName: String
        get() = Constant.DISCOVER_FRAGMENT
        set(value) {}

//    override fun taLayoutId(): Int =  R.id.tabs;

    private lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun setTabList(adapterViewPager: ViewerPagerAdapter, tabLayout: TabLayout, viewPager2: ViewPager2)
    {
        val obj : JSONObject = JSONObject(Constant.loadJSONFromAsset(requireContext(), "discover_tab.json"))
        val discoverUseCase = GetDiscoverUseCase(obj, AppRepoImp())
        val factory = DiscoverViewModel(discoverUseCase).createFactory()
        viewModel = ViewModelProvider(this, factory).get(DiscoverViewModel::class.java)

        viewModel.tabListLiveDataClean.observe(viewLifecycleOwner, Observer { list ->
            Log.LOG_D("DiscoverFragment", "Title:Obj "+ list.size);

            adapterViewPager.setTabList(list.size, list)
            // Connect TabLayout and ViewPager2 here
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
            DiscoverFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//

}