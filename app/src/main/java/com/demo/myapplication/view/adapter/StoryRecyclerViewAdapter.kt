package com.demo.myapplication.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.demo.myapplication.R
import com.demo.myapplication.model.StoryModel
/**
 * TODO: Replace the implementation with code for your data type.
 */
class StoryRecyclerViewAdapter(
) : RecyclerView.Adapter<StoryRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<StoryModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.story_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.title
        var url:String = item.img;
    }

    internal fun setStoryList(values: List<StoryModel>) {
        this.values = values
      //  Log.e("Result", "Adapter"+ values.size)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout : ConstraintLayout = view.findViewById(R.id.consMain);
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val image: ImageView = view.findViewById(R.id.img)

    }

}