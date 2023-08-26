package com.codersandeep.inspirepal.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import kotlin.random.Random

class BookmarksReAdapter(private val quoteEntities: List<QuoteEntity>): Adapter<BookmarksReAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {return quoteEntities.size}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val colors = arrayOf("#fcbf49","#f4acb7","#a7c957","#90e0ef","#ffdab9","#80ed99","#34a0a4","#c8b6ff","#fed9b7","#90a955","#89c2d9","#78c6a3")
        holder.quoteText.text = quoteEntities[position].content
        holder.authorText.text = quoteEntities[position].author
        holder.rootLayout.setBackgroundColor(Color.parseColor(colors[Random.nextInt(0,12)]))
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val quoteText: TextView = itemView.findViewById(R.id.item_view_quote)
        val authorText: TextView = itemView.findViewById(R.id.item_view_author)
        val rootLayout:LinearLayout = itemView.findViewById(R.id.item_view_root_layout)
    }
}