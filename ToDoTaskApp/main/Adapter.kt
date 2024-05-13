package com.example.todotaskapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class Adapter(private var data:List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
   inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var priority = itemView.priority
        var layout = itemView.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val itemView =LayoutInflater.from(parent.context).
                inflate(R.layout.activity_view,parent,false)
        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data.getOrNull(position)
        currentItem?.let { item ->
            when (data[position].priority.lowercase(Locale.getDefault())) {
                "high" -> holder.layout.setBackgroundColor(Color.parseColor("#f05454"))
                "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#edc988"))
                else -> holder.layout.setBackgroundColor(Color.parseColor("#ffffff"))

            }


        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra(" id", position)
            holder.itemView.context.startActivity(intent)
        }
    }
    }

}