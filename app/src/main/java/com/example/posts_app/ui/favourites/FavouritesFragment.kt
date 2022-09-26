package com.example.posts_app.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts_app.databinding.FragmentFavouritesBinding
import com.example.posts_app.ui.home.PostAdapter
import com.example.posts_app.ui.home.PostViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        setComponents()
        observeViewModel()
        return binding.root
    }

    private fun setComponents() {
        //Set Recyclerview layout manager
        binding.rvFavouritePosts.layoutManager = LinearLayoutManager(context)
        // Get favourite posts
        postViewModel.getFavouritePosts()
    }

    private fun observeViewModel() {
        postViewModel.favouritePostList.observe(viewLifecycleOwner){ post ->
            binding.rvFavouritePosts.adapter = PostAdapter(post)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}