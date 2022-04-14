package com.example.chuck.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck.R
import com.example.chuck.adapters.RecyclerViewAdapter
import com.example.chuck.databinding.FragmentRandomBinding
import com.example.chuck.events.Events
import com.example.chuck.events.GlobalBus.bus
import com.example.chuck.model.MainViewModel
import com.example.chuck.model.MainViewModelFactory
import com.example.chuck.model.Post
import com.example.chuck.repository.Repository
import com.example.chuck.util.ImgUrls
import com.example.chuck.util.WhichImage
import org.greenrobot.eventbus.Subscribe

class RandomJokeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private var imgUrls: ArrayList<String> = ArrayList()

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        bus?.register(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewRandom)
        imgUrls.addAll(ImgUrls.urlList)
        adapter = RecyclerViewAdapter(mutableListOf(), requireContext(), imgUrls, WhichImage.RANDOM)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        refreshDataFunction()
    }

    fun refreshDataFunction() {
        viewModel.getRandom()
        viewModel.myPostResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val list = mutableListOf<Post>()
                response.body()?.let { list.add(it) }
                val fragmentActivityMessageEvent : Events.FragmentToActivityMessage =
                    Events.FragmentToActivityMessage(list[0].value)
                bus?.post(fragmentActivityMessageEvent)
                adapter.setData(list)
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

    override fun onDestroyView() {
        super.onDestroyView()
        bus?.unregister(this)
        _binding = null
    }
}