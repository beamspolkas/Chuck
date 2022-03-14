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
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerCategoriesViewAdapter
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository


class RandomJokeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerCategoriesViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewRandom)
        adapter = RecyclerCategoriesViewAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setOnClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_random, container, false)!!

    private fun setOnClickListener(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        val myNumber = (1..100).random()
        viewModel.getPost(myNumber)
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
                response ->
            if(response.isSuccessful){
                val list = mutableListOf<Post>()
                response.body()?.let { list.add(it) }
                adapter.setData(list)
            } else {
                Log.d("Response - error: ", response.errorBody().toString())
            }
        })
        //zebrac parametr z edit text, wkleic do funkcji api, jezeli funkcja api ma response to wkleic do recyclerView
        //obluzyc wyjatki (brak danych, bledne dane, etc.)
    }
}