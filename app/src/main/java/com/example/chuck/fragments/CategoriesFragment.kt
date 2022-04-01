package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerViewAdapter
import com.example.chuck.adapters.StringAdapter
import com.example.chuck.databinding.FragmentCategoriesBinding
import com.example.chuck.interfaces.OnListItemClicked
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository


class CategoriesFragment : Fragment(), OnListItemClicked {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var stringAdapter: StringAdapter
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    recyclerView.adapter = stringAdapter
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCategories)
        stringAdapter = StringAdapter(mutableListOf(),this)
        recyclerViewAdapter = RecyclerViewAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recyclerView.adapter = stringAdapter

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        init()
    }

    private fun init() {
        viewModel.getCategories()
        viewModel.myStringResponse.observe(viewLifecycleOwner) { responses ->
            if (responses.isNotEmpty()) {
                stringAdapter.setStringData(responses)
            } else {
                Log.d("Response - error: ", responses.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Toast.makeText(requireContext(),"on Resume", Toast.LENGTH_SHORT).show()
        recyclerView.adapter = stringAdapter
    }
//glide tutorial: https://handyopinion.com/how-to-load-multiple-images-from-url-in-android-using-glide-kotlin/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(data : String) {
        viewModel.getRandomJokeByCategories(data)
        //Toast.makeText(requireContext(), "click on item $data", Toast.LENGTH_SHORT).show()
        viewModel.myPostResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val list = mutableListOf<Post>()
                response.body()?.let { list.add(it) }
                recyclerViewAdapter.setData(list)
                recyclerView.adapter = recyclerViewAdapter
            } else {
                Log.d("Response - error: ", response.errorBody().toString())
            }
        }
    }
}