package com.example.posts_app.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts_app.R
import com.example.posts_app.data.api.ApiResponseStatus
import com.example.posts_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setComponents()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        homeViewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                is ApiResponseStatus.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> binding.pbLoading.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> binding.pbLoading.visibility = View.GONE
                else -> TODO()
            }
        }
        homeViewModel.postList.observe(viewLifecycleOwner){ postList ->
            binding.rvPost.adapter = PostAdapter(postList)
        }
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(context)
            .setTitle(R.string.network_error)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dissmiss dialog **/ }
            .create()
            .show()
    }

    private fun setComponents() {
        //Fetch posts data from internet
        homeViewModel.getPostList()
        homeViewModel.getUserList()
        //Set Post RecyclerView
        binding.rvPost.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}