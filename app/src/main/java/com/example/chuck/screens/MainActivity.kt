package com.example.chuck.screens

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chuck.R
import com.example.chuck.adapters.ViewPagerAdapter
import com.example.chuck.databinding.ActivityMainBinding
import com.example.chuck.events.GlobalBus.bus
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

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom)}

    private var clicked = false

    override fun onStart() {
        super.onStart()
        bus?.register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val fab = binding.fab
        val save = binding.saveBtn
        val send = binding.sendBtn

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val allFragments = supportFragmentManager.fragments
                for (fragment in allFragments){
                    if(fragment is RandomJokeFragment){
                        fragment.refreshDataFunction()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        fab.setOnClickListener{
            onFabButtonClicked()
        }

        save.setOnClickListener {
            Toast.makeText(this, "Save button clicked", Toast.LENGTH_SHORT).show()

        }

        send.setOnClickListener {
            Toast.makeText(this, "Send button clicked", Toast.LENGTH_SHORT).show()
        }

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }

    private fun onFabButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.saveBtn.visibility = View.VISIBLE
            binding.sendBtn.visibility = View.VISIBLE
        } else {
            binding.saveBtn.visibility = View.INVISIBLE
            binding.sendBtn.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            binding.saveBtn.startAnimation(fromBottom)
            binding.sendBtn.startAnimation(fromBottom)
            binding.fab.startAnimation(rotateOpen)
        } else {
            binding.saveBtn.startAnimation(toBottom)
            binding.sendBtn.startAnimation(toBottom)
            binding.fab.startAnimation(rotateClose)
        }
    }

    override fun onStop() {
        super.onStop()
        bus?.unregister(this)
    }
}