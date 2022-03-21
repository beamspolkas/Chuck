package com.example.chuck.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chuck.databinding.ActivityMainBinding
import com.example.chuck.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

val tabsArray = arrayOf(
    "Categories",
    "Random Joke",
    "Joke Search"
)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }
}