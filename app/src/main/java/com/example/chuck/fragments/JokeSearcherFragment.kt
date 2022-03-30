package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerViewAdapter
import com.example.chuck.databinding.FragmentSearcherBinding
import com.example.chuck.model.DialogCallback
import com.example.chuck.model.InfoDialog
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.repository.Repository

class JokeSearcherFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)

    private var _binding: FragmentSearcherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearcherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewSearcher)
        adapter = RecyclerViewAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setOnClickListener()
    }

    private fun setOnClickListener(){
        val searchButton = view?.findViewById<ImageButton>(R.id.btn_search)
        val numberEditText = view?.findViewById<TextView>(R.id.text_editText)
        searchButton?.setOnClickListener{
            val myText = numberEditText?.text.toString()
            viewModel.getJokes(myText)
            viewModel.myResponse.observe(viewLifecycleOwner) { responses ->
                Log.d("Responses: ", responses.body().toString())
                if (responses.isSuccessful) {
                    if(responses.body()?.result?.isEmpty() == true) {
                        InfoDialog().build(
                            requireContext(),
                            "Error",
                            "File not supported, incorrect URL",
                            object : DialogCallback {
                                override fun onClose() {}
                            }
                        )
                    } else {
                        responses.body()?.result?.let { adapter.setData(it.toMutableList()) }
                    }
                } else {
                    Log.d("Response - error: ", responses.errorBody().toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}