package com.example.chuck.fragments

import android.annotation.SuppressLint
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
import com.example.chuck.interfaces.DialogCallback
import com.example.chuck.model.InfoDialog
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.repository.Repository
import com.example.chuck.util.ImgUrls


class JokeSearcherFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private var imgUrls: ArrayList<String> = ArrayList()

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
        imgUrls.addAll(ImgUrls.urlList)
        Log.d("Responses: ", imgUrls.toString())
        adapter = RecyclerViewAdapter(mutableListOf(), requireContext(), imgUrls)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setOnClickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClickListener(){
        val searchButton = view?.findViewById<ImageButton>(R.id.btn_search)
        val textEditText = view?.findViewById<TextView>(R.id.text_editText)
        searchButton?.setOnClickListener{
            val myText = textEditText?.text.toString()
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
                                override fun onClose() {
                                    textEditText?.text = ""
                                }
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