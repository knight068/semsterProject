package com.example.semesterproject.myRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semesterproject.R

class FoodsAdapter (val foodList: ArrayList<Foods>):RecyclerView.Adapter<FoodsAdapter.FoodViwHolder>() {
    var onItemClick :((Foods) -> Unit)?= null
    class FoodViwHolder (itemView: View) : RecyclerView.ViewHolder (itemView){
        val rcImageView :ImageView=itemView.findViewById(R.id.rvImageView)
        val textView : TextView =itemView.findViewById(R.id.textView)
        val imageButton : ImageButton=itemView.findViewById(R.id.btnDeleteRecycler)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViwHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item,parent,false)
        return FoodViwHolder(view)

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViwHolder, position: Int) {
        val food =foodList[position]
        holder.rcImageView.setImageResource(food.image)
        holder.textView.text=food.name
        holder.itemView.setOnClickListener {
           onItemClick?.invoke(food)

            }
        holder.imageButton.setOnClickListener {
            foodList.removeAt(position)
            notifyItemRemoved(position)


        }
}
}