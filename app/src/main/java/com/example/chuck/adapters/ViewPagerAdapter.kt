package com.example.chuck.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chuck.fragments.CategoriesFragment
import com.example.chuck.fragments.FavouritesFragment
import com.example.chuck.fragments.JokeSearcherFragment
import com.example.chuck.fragments.RandomJokeFragment

private const val NUM_TABS = 4

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CategoriesFragment()
            1 -> return RandomJokeFragment()
            2 -> return FavouritesFragment()
        }
        return JokeSearcherFragment()
    }
}