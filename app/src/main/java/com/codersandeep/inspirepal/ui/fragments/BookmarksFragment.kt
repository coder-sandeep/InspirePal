package com.codersandeep.inspirepal.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import com.codersandeep.inspirepal.ui.adapter.BookmarksReAdapter
import com.codersandeep.inspirepal.databinding.FragmentBookmarksBinding
import com.codersandeep.inspirepal.ui.MainActivity
import com.codersandeep.inspirepal.utils.SwipeToDeleteCallback
import com.codersandeep.inspirepal.viewmodels.MainViewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {
    private lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentBookmarksBinding
    lateinit var quotes: List<QuoteEntity>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = (activity as MainActivity).mainViewModel

        binding = FragmentBookmarksBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        mainViewModel.getSavedQuotes().observe(viewLifecycleOwner){
            quotes = it;
            binding.quotesRecyclerView.adapter = BookmarksReAdapter(it)
            binding.quotesRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        val swipeToDeleteCallback = object  : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                mainViewModel.deleteQuote(quotes[position]);
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(binding.quotesRecyclerView)

        return view
    }
}