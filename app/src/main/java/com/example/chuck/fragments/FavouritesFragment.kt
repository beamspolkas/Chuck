package com.example.chuck.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerViewAdapter
import com.example.chuck.databinding.FragmentFavouritesBinding
import com.example.chuck.databinding.FragmentRandomBinding
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.preferences.App.Companion.prefs
import com.example.chuck.repository.Repository

class FavouritesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewFavourites)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        loadData()
    }

    private fun loadData() {
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences?.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences?.getBoolean("BOOLEAN_KEY", true)
        val textView = view?.findViewById<TextView>(R.id.favsTextView)
        textView?.text = savedString.toString()
        val textViewValue = textView?.text
        Log.d("savedString",textViewValue.toString())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}