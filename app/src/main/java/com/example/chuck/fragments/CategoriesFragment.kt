package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerCategoriesViewAdapter
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.repository.Repository

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        val adapter = RecyclerCategoriesViewAdapter(mutableListOf())
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewCategories)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPosts()
//        Log.d("KLASA2: ", viewModel.getPosts().javaClass.toString())
//        Log.d("KLASA2: ", viewModel.myResponses.javaClass.toString())
        viewModel.myResponses.observe(viewLifecycleOwner, Observer {
            responses ->
            if(responses.isSuccessful){
                responses.body()?.let { adapter.setData(it.toMutableList()) }
            } else {
                Log.d("Response - error: ", responses.errorBody().toString())
            }
        })

    }

    //inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_categories, container, false)!!
}
