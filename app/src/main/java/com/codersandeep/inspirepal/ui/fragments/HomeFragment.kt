package com.codersandeep.inspirepal.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.api.RetrofitHelper
import com.codersandeep.inspirepal.data.repository.ImageRepository
import com.codersandeep.inspirepal.data.repository.QuoteRepository
import com.codersandeep.inspirepal.databinding.FragmentHomeBinding
import com.codersandeep.inspirepal.utils.AppConstants
import com.codersandeep.inspirepal.viewmodels.HomeViewModel
import com.codersandeep.inspirepal.viewmodels.HomeViewModelFactory
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var homeViewModel:HomeViewModel
    var photoURL:String = ""
    var userURL:String = ""
    var userName:String = "name"

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)


        val view = fragmentHomeBinding.root
        val attributeTextView = view.findViewById<TextView>(R.id.attribute_textView)

        val quotesService = RetrofitHelper.getInstance(AppConstants.BASE_URL_QUOTES).create(QuotesService::class.java)
        val quoteRepository = QuoteRepository(quotesService)

        val imageService = RetrofitHelper.getInstance(AppConstants.BASE_URL_PHOTO).create(ImageService::class.java)
        val imageRepository = ImageRepository(imageService)

        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(quoteRepository,imageRepository))[HomeViewModel::class.java]
        fragmentHomeBinding.vmodel = homeViewModel
        homeViewModel.quotes.observe(viewLifecycleOwner, Observer {fragmentHomeBinding.quoteItem = it})

        homeViewModel.images.observe(viewLifecycleOwner, Observer {

            fragmentHomeBinding.imageItem = it[0]

            photoURL = it[0].links.html
            userURL = it[0].user.links.html
            userName =  it[0].user.name
            attributeTextView.text = attributeTextView.text.toString().replace("name",userName)
            makeAttributeStyles(attributeTextView)
        })
        return view
    }
    private fun makeAttributeStyles(attributeTextView: TextView){
        val spannableString = SpannableString(attributeTextView.text)
        val clickableSpanPhoto:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(photoURL)))
            }
        }
        val clickableSpanUser:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(userURL)))
            }
        }
        val clickableSpanUnsplash:ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://unsplash.com/?utm_source=inspirepal&utm_medium=referral")))
            }
        }

        val attributeLength = attributeTextView.text.length
        spannableString.setSpan(clickableSpanPhoto,0,5,Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(clickableSpanUser,9,9+userName.length,Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableString.setSpan(clickableSpanUnsplash,attributeLength-8,attributeLength,Spanned.SPAN_INCLUSIVE_INCLUSIVE)


        attributeTextView.text = spannableString
        attributeTextView.movementMethod = LinkMovementMethod.getInstance()

        attributeTextView.visibility = View.VISIBLE;
    }

    fun handleNextQuote(view: View) {
        homeViewModel.getNextQuote()
    }
}