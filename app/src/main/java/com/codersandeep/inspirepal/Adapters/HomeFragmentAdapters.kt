package com.codersandeep.inspirepal.Adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.codersandeep.inspirepal.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url:String?){
    if (url != null) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_error)
            .into(this)
    }
}