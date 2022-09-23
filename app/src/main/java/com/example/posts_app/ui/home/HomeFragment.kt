package com.example.posts_app.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts_app.R
import com.example.posts_app.databinding.FragmentHomeBinding
import com.example.posts_app.utils.ResponseStatus
import com.example.posts_app.utils.SwipeToDeleteCallback

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val postViewModel: PostViewModel by viewModels()

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
        postViewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                is ResponseStatus.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ResponseStatus.Loading -> binding.pbLoading.visibility = View.VISIBLE
                is ResponseStatus.Success -> binding.pbLoading.visibility = View.GONE
                else -> TODO()
            }
        }
        postViewModel.postList.observe(viewLifecycleOwner){ postList ->
            binding.rvPost.adapter = PostAdapter(postList)
            val swipeToDeleteCallback = object: SwipeToDeleteCallback(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    postList?.get(position)?.let { postViewModel.deletePost(it.id) }
                    binding.rvPost.adapter?.notifyItemRemoved(position)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(binding.rvPost)
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
        //Fetch posts data from api
        postViewModel.getPosts()
        //Set Post RecyclerView
        binding.rvPost.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}