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
import com.example.chuck.adapters.RecyclerViewAdapter
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository
import retrofit2.Response

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        val adapter = RecyclerViewAdapter(mutableListOf())
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewCategories)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        Log.d("KLASA1: ", viewModel.javaClass.toString())
        viewModel.getPosts()
        Log.d("KLASA2: ", viewModel.getPosts().javaClass.toString())
        viewModel.myResponses.observe(viewLifecycleOwner, Observer {
            Log.d("KLASA3: ", viewModel.myResponses.javaClass.toString())
            for (response in it) {
                val list = mutableListOf<Post>()
//                response.body()?.let { list.add() }
//                adapter.setData(list)
//                TUTAJ BRAK CZEGOS
            }
        })
//viewModel.getPost()
//          viewModel.myResponse.observe(
//            viewLifecycleOwner,
//            Observer { response ->
//                if(response.isSuccessful){
////                    Log.d("Response - user ID: ", response.body()?.userId.toString())
////                    Log.d("Response - body: ", response.body()?.body!!)
//                    val list = mutableListOf<Post>()
//                    response.body()?.let { list.add(it) }
//                    adapter.setData(list)
//                } else {
//                    Log.d("Response - error: ", response.errorBody().toString())
////                    textView.text = response.code().toString()
//                }
//            },
//        )
    }

    //inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_categories, container, false)!!
}
