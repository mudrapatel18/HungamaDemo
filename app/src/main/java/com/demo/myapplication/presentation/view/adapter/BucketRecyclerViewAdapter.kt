package com.demo.myapplication.presentation.view.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.myapplication.R
import com.demo.myapplication.domain.model.BucketModel

/**
 * TODO: Replace the implementation with code for your data type.
 */
class BucketRecyclerViewAdapter(var context: Context
) : RecyclerView.Adapter<BucketRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<BucketModel>()
    private lateinit var adapterSubBucket : SubBucketRecyclerViewAdapter;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_bucket, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.title
        if(item.subTitle.isEmpty()){
            holder.tvSubTitle.visibility = View.GONE
        }
        holder.tvSubTitle.text = item.subTitle
        Log.e("Result", ":ShowMore"+item.showMore)
        if(item.showMore){
            holder.showMoreIv.visibility = View.VISIBLE
        }else{
            holder.showMoreIv.visibility = View.GONE
        }

        adapterSubBucket = SubBucketRecyclerViewAdapter(item.type)
        holder.recyclerView.adapter = adapterSubBucket
        adapterSubBucket.setBucketList(item.contentList)
//        holder.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        //holder.recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
        when(item.type){
            9, 19 ->  holder.recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
            15 ->  holder.recyclerView.layoutManager = GridLayoutManager(context, 5, GridLayoutManager.HORIZONTAL, false)
            12 ,  17, 18-> holder.recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

            else -> holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        }
    }

    internal fun setBucketList(values: List<BucketModel>) {
        this.values = values
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout : ConstraintLayout = view.findViewById(R.id.consMain);
        val tvTitle: TextView = view.findViewById(R.id.title)
        val tvSubTitle: TextView = view.findViewById(R.id.sub_title)
        val showMoreIv: ImageView = view.findViewById(R.id.iv_show_more)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_sub_bucket)

    }

}