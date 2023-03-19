package com.codersandeep.inspirepal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codersandeep.inspirepal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_fragment)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setupWithNavController(navController)
    }
}