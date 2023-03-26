package com.codersandeep.inspirepal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.codersandeep.inspirepal.R
import com.codersandeep.inspirepal.data.api.ImageService
import com.codersandeep.inspirepal.data.api.QuotesService
import com.codersandeep.inspirepal.data.api.RetrofitHelper
import com.codersandeep.inspirepal.data.db.QuoteDatabase
import com.codersandeep.inspirepal.data.repository.MainRepository
import com.codersandeep.inspirepal.utils.AppConstants
import com.codersandeep.inspirepal.viewmodels.MainViewModel
import com.codersandeep.inspirepal.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setupWithNavController(navController)

        setUpMVVM()
    }

    private fun setUpMVVM(){
        val quotesService = RetrofitHelper.getInstance(AppConstants.BASE_URL_QUOTES).create(
            QuotesService::class.java)
        val imageService = RetrofitHelper.getInstance(AppConstants.BASE_URL_IMAGE).create(
            ImageService::class.java)
        val quoteDatabase = Room.databaseBuilder(this,QuoteDatabase::class.java,"quote_db.db").build()

        val mainRepository = MainRepository(imageService,quotesService,quoteDatabase)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(mainRepository))[MainViewModel::class.java]
    }
}