package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
import com.example.chuck.events.Events
import com.example.chuck.events.GlobalBus.bus
import com.example.chuck.interfaces.OnListItemClicked
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository
import com.example.chuck.util.ImgUrls
import com.example.chuck.util.WhichImage
import kotlinx.coroutines.*
import org.greenrobot.eventbus.Subscribe


class CategoriesFragment : Fragment(), OnListItemClicked {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var stringAdapter: StringAdapter
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private var imgUrls: ArrayList<String> = ArrayList()

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
        bus?.register(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCategories)
        stringAdapter = StringAdapter(mutableListOf(),this)
        imgUrls.addAll(ImgUrls.urlList)
        recyclerViewAdapter = RecyclerViewAdapter(mutableListOf(), requireContext(), imgUrls, WhichImage.CATEGORY)
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
        recyclerView.adapter = stringAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bus?.unregister(this)
        _binding = null
    }

    override fun onClick(data : String) {
        viewModel.getRandomJokeByCategories(data)
        viewModel.myPostResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                runBlocking {//lepiej się wczytuje ale są zwiechy
                    delay(300)
                    val list = mutableListOf<Post>()
                    response.body()?.let { list.add(it) }

                    val fragmentActivityMessageEvent : Events.FragmentToActivityMessage =
                        Events.FragmentToActivityMessage(list[0].value)
                    bus?.post(fragmentActivityMessageEvent)

                    recyclerViewAdapter.setData(list)
                    recyclerView.adapter = recyclerViewAdapter
                }
            } else {
                Log.d("Response - error: ", response.errorBody().toString())
            }
        }
    }

    @Subscribe
    fun getMessage(activityFragmentMessage: Events.ActivityToFragmentMessage) {
        Toast.makeText(
            activity,
            getString(R.string.message_fragment) +
                    " " + activityFragmentMessage.message,
            Toast.LENGTH_SHORT
        ).show()
    }
}