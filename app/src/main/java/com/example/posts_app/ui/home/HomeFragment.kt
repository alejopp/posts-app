package com.example.posts_app.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts_app.R
import com.example.posts_app.databinding.FragmentHomeBinding
import com.example.posts_app.utils.ResponseStatus
import com.example.posts_app.utils.SwipeToDeleteCallback

class HomeFragment : Fragment(), MenuProvider {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        super.onViewCreated(view, savedInstanceState)
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

    private fun setComponents() {
        //Fetch posts data from api
        postViewModel.getPosts()
        //Set Post RecyclerView
        binding.rvPost.layoutManager = LinearLayoutManager(context)
        //Set Toolbar

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
        return when (menuItem.itemId) {
            R.id.action_delete_posts -> {
                showConfirmDialog()
                true
            }
            else -> false
        }
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(context)
            .setTitle(R.string.error_message)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dissmiss dialog **/ }
            .create()
            .show()
    }

    private fun showConfirmDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.confirm_message)
            .setMessage(R.string.confirm_delete_message)
            .setPositiveButton(android.R.string.ok) { _, _ -> deleteAllPosts() }
            .setNegativeButton(android.R.string.cancel){_,_ -> /** Dismiss dialog **/}
            .create()
            .show()
    }

    private fun deleteAllPosts() {
        try {
            postViewModel.deleteAllPosts()
            Toast.makeText(context, R.string.posts_deleted_correctly, Toast.LENGTH_LONG).show()
        }catch (e: Exception){}
    }
}