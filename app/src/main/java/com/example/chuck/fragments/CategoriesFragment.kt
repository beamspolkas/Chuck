package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.StringAdapter
import com.example.chuck.databinding.FragmentCategoriesBinding
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.repository.Repository

class CategoriesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StringAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCategories)
        adapter = StringAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        init()
    }

    private fun init() {
        viewModel.getCategories()
        viewModel.myStringResponse.observe(viewLifecycleOwner) { responses ->
            if (responses.isNotEmpty()) {
                adapter.setStringData(responses)
            } else {
                Log.d("Response - error: ", responses.toString())
            }
        }
    }

    //glide tutorial: https://handyopinion.com/how-to-load-multiple-images-from-url-in-android-using-glide-kotlin/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}