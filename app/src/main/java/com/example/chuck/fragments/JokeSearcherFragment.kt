package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.R.id.recyclerViewSearcher
import com.example.chuck.adapters.RecyclerCategoriesViewAdapter
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository

class JokeSearcherFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val recyclerView = view?.findViewById<RecyclerView>(recyclerViewSearcher)
    private val adapter = RecyclerCategoriesViewAdapter(mutableListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setOnClickListener()

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
     }

    //inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_searcher, container, false)!!

    private fun setOnClickListener(){
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        val searchButton = view?.findViewById<ImageButton>(R.id.btn_search)
        val numberEditText = view?.findViewById<TextView>(R.id.number_editText)
//        val bodyTextView = view?.findViewById<TextView>(R.id.body)
//        val userIdTextView = view?.findViewById<TextView>(R.id.userId)
//        val idTextView = view?.findViewById<TextView>(R.id.id)
//        val titleTextView = view?.findViewById<TextView>(R.id.title)
        searchButton?.setOnClickListener{
            val myNumber = numberEditText?.text.toString()
            viewModel.getPost(Integer.parseInt(myNumber))

            //zebrac parametr z edit text, wkleic do funkcji api, jezeli funkcja api ma response to wkleic do recyclerView
            //obluzyc wyjatki (brak danych, bledne dane, etc.)
        }
    }

}
