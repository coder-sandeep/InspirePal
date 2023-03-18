package com.codersandeep.inspirepal.ui.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.api.RetrofitHelper
import com.codersandeep.inspirepal.data.repository.ImageRepository
import com.codersandeep.inspirepal.data.repository.QuoteRepository
import com.codersandeep.inspirepal.utils.AppConstants
import com.codersandeep.inspirepal.viewmodels.MainViewModel
import com.codersandeep.inspirepal.viewmodels.MainViewModelFactory
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var mainViewModel:MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home,container,false)
        var quoteTextView = view.findViewById<TextView>(R.id.quoteText);
        var authorTextView = view.findViewById<TextView>(R.id.authorText)
        var backgroundImage = view.findViewById<ImageView>(R.id.mainImage);
        var attributeTextView = view.findViewById<TextView>(R.id.attribute_textView)

        makeAttributeStyles(attributeTextView)

        val quotesService = RetrofitHelper.getInstance(AppConstants.BASE_URL_QUOTES).create(QuotesService::class.java)
        val quoteRepository = QuoteRepository(quotesService)

        val imageService = RetrofitHelper.getInstance(AppConstants.BASE_URL_PHOTO).create(ImageService::class.java)
        val imageRepository = ImageRepository(imageService)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(quoteRepository,imageRepository)).get(MainViewModel::class.java)
        mainViewModel.quotes.observe(viewLifecycleOwner, Observer {
            quoteTextView.text = it.content
            authorTextView.text = it.author
        })

        mainViewModel.images.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it[0].urls.regular).into(backgroundImage);
        })
        return view
    }
    private fun makeAttributeStyles(attributeTextView: TextView){
        val spannableString = SpannableString(attributeTextView.text)
        val clickableSpanPhoto:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {

            }
        }
        val clickableSpanAuthor:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {

            }
        }
        val clickableSpanUnsplash:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {

            }
        }

        spannableString.setSpan(clickableSpanPhoto,0,5,Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(clickableSpanAuthor,9,13,Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(clickableSpanUnsplash,17,25,Spanned.SPAN_INCLUSIVE_INCLUSIVE)


        attributeTextView.text = spannableString
        attributeTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}