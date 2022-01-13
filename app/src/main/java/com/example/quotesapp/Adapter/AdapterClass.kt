package com.example.quotesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.Model.Result

import com.example.quotesapp.R

class AdapterClass(): RecyclerView.Adapter<AdapterClass.QuoteViewHolder>() {
    lateinit var list: List<Result>
     class QuoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
     {
         val textView:TextView=itemView.findViewById(R.id.text)
         val author:TextView=itemView.findViewById(R.id.authorname)
     }
    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
       return QuoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_preview,parent,false))
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
       val quote=differ.currentList[position]
        //bind only two for now
        holder.textView.text=quote.content
        holder.author.text=quote.author

    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

}