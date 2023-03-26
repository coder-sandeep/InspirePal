package com.codersandeep.inspirepal.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import com.codersandeep.inspirepal.data.models.quote.Quote
import com.codersandeep.inspirepal.databinding.FragmentHomeBinding
import com.codersandeep.inspirepal.ui.MainActivity
import com.codersandeep.inspirepal.viewmodels.MainViewModel
import com.squareup.picasso.Picasso
import kotlin.random.Random

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var mainViewModel: MainViewModel
    lateinit var quote: Quote
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mainViewModel = (activity as MainActivity).mainViewModel
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        binding.shareButton.setOnClickListener { handleShare() }
        binding.saveButton.setOnClickListener { handleSave() }
        binding.nextQuoteButton.setOnClickListener { handleNextQuote() }

        setUpObserver()
        return view
    }

    private fun handleSave(){
        mainViewModel.saveQuote(QuoteEntity(quote._id, quote.content,quote.author))
    }
    private fun handleNextQuote(){
        binding.progressBarView.visibility = View.VISIBLE
        mainViewModel.getNextQuote()
    }
    private fun handleShare(){
        val shareBody = quote.content + "\n-" + quote.author
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, getString(R.string.share_text)));
    }
    private fun setUpObserver(){
        mainViewModel.quotes.observe(viewLifecycleOwner) {
            binding.progressBarView.visibility = View.GONE
            binding.quoteText.text = it.content
            binding.authorText.text = it.author

            quote = it
        }

        mainViewModel.image.observe(viewLifecycleOwner)  {
            val randomPos = Random.nextInt(0,80)
            Picasso.get()
                .load(it.photos[randomPos].src.large)
                .error(R.drawable.ic_error)
                .into(binding.mainImage)
        }
    }
}