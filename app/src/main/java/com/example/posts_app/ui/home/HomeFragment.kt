package com.example.posts_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts_app.data.models.PostProvider
import com.example.posts_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setComponents()
        return binding.root
    }

    private fun setComponents() {
        // Set Post Adapter
        val postAdapter = PostAdapter(PostProvider.postList)
        //Set Post RecyclerView
        binding.rvPost.layoutManager = LinearLayoutManager(context)
        binding.rvPost.adapter = postAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}