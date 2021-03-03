package com.demo.myapplication.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.demo.myapplication.R
import com.demo.myapplication.domain.model.BucketContentModel

class SubBucketRecyclerViewAdapter(var type:Int
) : RecyclerView.Adapter<SubBucketRecyclerViewAdapter.ViewHolder>() {
    private var values = emptyList<BucketContentModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_sub_bucket, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitleType9.text = item.title

//        holder.tvTitleType10To12.text = item.title
//        holder.tvSubTitleType10To12.text = item.subTitle

        holder.tvTitleType15.text = item.title
        holder.tvSubTitleType15.text = item.subTitle

//        holder.tvTitleType14.text = item.title
//        holder.tvSubTitleType14.text = item.subTitle

        holder.tvTitleType18.text = item.title
        holder.tvSubTitleType18.text = item.subTitle

        holder.tvTitleType19.text = item.title


        when(type){
            9 -> {
                holder.layoutType9.visibility = View.VISIBLE
            }

            10 -> {
                holder.layoutType18.visibility = View.VISIBLE
                holder.tvSubTitleType18.visibility = View.GONE
                holder.tvTitleType18.visibility = View.GONE
                holder.cardViewType10to12.visibility = View.VISIBLE

            }
            11, 12 , 13 -> {
                holder.layoutType18.visibility = View.VISIBLE
                holder.cardViewType10to12.visibility = View.VISIBLE

            }

          /*  13 -> {
                holder.layoutType10To12.visibility = View.VISIBLE
                holder.imgType10To12.setBackgroundResource(R.drawable.bg_square_elevation)
            }*/

            14,17 -> {
//                holder.layoutType14.visibility = View.VISIBLE
                holder.layoutType18.visibility = View.VISIBLE
                holder.cardView14.visibility = View.VISIBLE
            }

            15 -> {
                holder.layoutType15.visibility = View.VISIBLE
//                holder.tvSubTitleType9.visibility = View.VISIBLE
            }

            16 -> {
                holder.layoutType18.visibility = View.VISIBLE
                holder.cardViewCircle.visibility = View.VISIBLE
            }

            18 -> {
                holder.layoutType18.visibility = View.VISIBLE
                holder.cardViewRectangle18.visibility = View.VISIBLE
            }

            19 -> {
                holder.layoutType19.visibility = View.VISIBLE
            }

            else -> holder.layoutType9.visibility = View.GONE
        }

    }

    internal fun setBucketList(values: List<BucketContentModel>) {
        this.values = values
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layoutType9 : ConstraintLayout = view.findViewById(R.id.type_9_layout);
        val cardView9: CardView = view.findViewById(R.id.card_image_type9);

        val tvTitleType9: TextView = view.findViewById(R.id.title_type_9)
        val imgType9: ImageView = view.findViewById(R.id.img_type_9)

//        val layoutType10To12 : ConstraintLayout = view.findViewById(R.id.type_10_to_12_layout);
//        val tvTitleType10To12: TextView = view.findViewById(R.id.title_10_to_12)
//        val tvSubTitleType10To12: TextView = view.findViewById(R.id.sub_title_10_to_12)
        val imgType10To12: ImageView = view.findViewById(R.id.img_10_to_12)
        val cardViewType10to12: CardView = view.findViewById(R.id.card_image_type10_12);

        val layoutType15 : ConstraintLayout = view.findViewById(R.id.type_15_layout);
        val tvTitleType15: TextView = view.findViewById(R.id.title_type_15)
        val tvSubTitleType15: TextView = view.findViewById(R.id.sub_title_type_15)
        val imgType15: ImageView = view.findViewById(R.id.img_type_15)

//        val layoutType14 : ConstraintLayout = view.findViewById(R.id.type_14_layout);
//        val tvTitleType14: TextView = view.findViewById(R.id.title_14)
//        val tvSubTitleType14: TextView = view.findViewById(R.id.sub_title_14)
        val cardView14: CardView = view.findViewById(R.id.card_image_type14);
        val imgType14: ImageView = view.findViewById(R.id.img_14)


//      Type 18 is common layout for rectangle , circle and square
        val layoutType18 : ConstraintLayout = view.findViewById(R.id.type_18_layout);
        val tvTitleType18: TextView = view.findViewById(R.id.title_18)
        val tvSubTitleType18: TextView = view.findViewById(R.id.sub_title_18)
        val imgType18: ImageView = view.findViewById(R.id.img_18)

        val cardViewRectangle18: CardView = view.findViewById(R.id.card_image_type18);

        val cardViewCircle: CardView = view.findViewById(R.id.cardImage_circle);
        val imgTypeCircle: ImageView = view.findViewById(R.id.img_circle)

//only title with bg
        val layoutType19 : ConstraintLayout = view.findViewById(R.id.type_19_layout);
        val tvTitleType19: TextView = view.findViewById(R.id.title_type_19)


    }

}