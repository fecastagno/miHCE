package com.example.mihce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.farmacos.view.*

class FarmacosAdapter(
    private val exampleList: List<F_item>,
    private val listener: OnItemClickListener) :

    RecyclerView.Adapter<FarmacosAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.farmacos, parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textView1.text = "Medicación: "+currentItem.text1
        holder.textView2.text = "Dosis: "+currentItem.text2
        holder.textView3.text = "Desde: "+currentItem.text3
        holder.textView4.text = "Hasta: "+currentItem.text4
    }
    override fun getItemCount() = exampleList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val textView3: TextView = itemView.text_view_3
        val textView4: TextView = itemView.text_view_4
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface  OnItemClickListener{
        fun onItemClick(position: Int)
    }
}