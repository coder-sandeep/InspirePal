package com.codersandeep.inspirepal.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.api.RetrofitHelper
import com.codersandeep.inspirepal.data.repository.QuoteRepository
import com.codersandeep.inspirepal.utils.AppConstants
import com.codersandeep.inspirepal.viewmodels.MainViewModel
import com.codersandeep.inspirepal.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_fragment)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setupWithNavController(navController)
    }
}