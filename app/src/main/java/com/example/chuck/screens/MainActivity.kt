package com.example.chuck.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chuck.databinding.ActivityMainBinding
import com.example.chuck.adapters.ViewPagerAdapter
import com.example.chuck.fragments.RandomJokeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

val tabsArray = arrayOf(
    "Categories",
    "Random Joke",
    "Joke Search"
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,tab?.text,Toast.LENGTH_LONG).show()
                val allFragments = supportFragmentManager.fragments
                for (fragment in allFragments){
                    if(fragment is RandomJokeFragment){
                        fragment.refreshDataFunction()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }
}