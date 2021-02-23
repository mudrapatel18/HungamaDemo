package com.demo.myapplication.view.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.myapplication.R
import com.demo.myapplication.utils.Constant
import com.demo.myapplication.utils.Constant.Companion.loadJSONFromAsset
import com.demo.myapplication.view.adapter.BucketRecyclerViewAdapter
import com.demo.myapplication.view.adapter.StoryRecyclerViewAdapter
import com.demo.myapplication.viewModel.DiscoverViewModel
import com.demo.myapplication.viewModel.PagerViewModel
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

class ViewPagerFragment : Fragment() {

  companion object {
    const val ARG_POSITION = "position"
    const val ARG_TITLE = "title"
    const val ARG_FRAGMENT = "fragment"

    fun getInstance(position: Int, tabTitle: String, fragmentName: String): Fragment {
      val ViewPagerFragment =
        ViewPagerFragment()
      val bundle = Bundle()
      bundle.putInt(ARG_POSITION, position)
      bundle.putString(ARG_TITLE, tabTitle)
      bundle.putString(ARG_FRAGMENT, fragmentName)
      ViewPagerFragment.arguments = bundle
      return ViewPagerFragment
    }
  }

  private lateinit var adapterStory : StoryRecyclerViewAdapter
  private lateinit var adapterBucket : BucketRecyclerViewAdapter
  private lateinit var viewModel: PagerViewModel

  private lateinit var viewDivider: View;
  private lateinit var recyclerView : RecyclerView
  private lateinit var recyclerViewBucket : RecyclerView
  var position : Int = 0;

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    var view = inflater.inflate(R.layout.fragment_view_pager, container, false)

    viewDivider = view.findViewById(R.id.viewDivider)

    recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_story)
    adapterStory = StoryRecyclerViewAdapter()
    recyclerView.adapter = adapterStory
    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

    recyclerViewBucket = view.findViewById<RecyclerView>(R.id.recyclerview_bucket)
    adapterBucket = context?.let { BucketRecyclerViewAdapter(it) }!!
    recyclerViewBucket.adapter = adapterBucket
    recyclerViewBucket.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)

    return view;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     position = requireArguments().getInt(ARG_POSITION)
    val title = requireArguments().getString(ARG_TITLE)
    tab_title.text = title

  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(PagerViewModel::class.java)

    val obj : JSONObject = JSONObject(loadJSONFromAsset(requireContext(), "story_bucket.json"))
    viewModel.getStoryList(obj).observe(viewLifecycleOwner, Observer {
        list ->
      //set data
      adapterStory.setStoryList(list)
    })

    val fragmentType: String? =  requireArguments().getString(ARG_FRAGMENT)
    var passUrl: String
    if(fragmentType.equals(Constant.DISCOVER_FRAGMENT)){
      passUrl = "discover_bucket.json"
    }else{
      passUrl = "music_bucket.json"
      if(position == 1)
         passUrl = "music_chart_bucket.json"

      recyclerView.visibility = View.GONE
      viewDivider.visibility = View.GONE
    }

    val objBucket : JSONObject = JSONObject(loadJSONFromAsset(requireContext(), passUrl))
    viewModel.getBucketList(objBucket).observe(viewLifecycleOwner, Observer {
        list ->
      Log.e("Result", "SIZE: "+list.size )

      //set data
      adapterBucket.setBucketList(list)
    })

  }



}
