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

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        //part 1:
        //part 2: https://www.youtube.com/watch?v=Dw_BIR5K82Q
        //part 3: https://www.youtube.com/watch?v=uCJuprbXJk4
        //przeniesienie do fragmentu: https://www.youtube.com/watch?v=HGrFPWUCFNg

        val adapter = RecyclerViewAdapter(mutableListOf())
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewCategories)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(
            viewLifecycleOwner,
            Observer { response ->
                if(response.isSuccessful){
//                    Log.d("Response - user ID: ", response.body()?.userId.toString())
//                    Log.d("Response - ID: ", response.body()?.id.toString())
//                    Log.d("Response - title: ", response.body()?.title!!)
//                    Log.d("Response - body: ", response.body()?.body!!)
                    val list = mutableListOf<Post>()
                    response.body()?.let { list.add(it) }
                    adapter.setData(list)
                } else {
                    Log.d("Response - error: ", response.errorBody().toString())
                    //textView.text = response.code().toString()
                }
            },
        )

    }

    //inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_categories, container, false)!!
}
