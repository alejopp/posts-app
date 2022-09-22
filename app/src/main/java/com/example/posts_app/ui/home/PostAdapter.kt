package com.example.posts_app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.posts_app.R
import com.example.posts_app.data.models.Post
import com.example.posts_app.databinding.ItemPostBinding

class PostAdapter(private val postList: List<Post>?) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, position: Int) {
        postViewHolder.binding.tvPostPosition.text = (position + 1).toString()
        postViewHolder.binding.tvTilteText.text = postList?.get(position)?.title
        postViewHolder.binding.tvPostDescriptionText.text = postList?.get(position)?.body
        if (postList?.get(position)?.isFavourite == true) postViewHolder.binding.ivFavourite.visibility =
            View.VISIBLE
        postViewHolder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.postDetailFragmentDestination)
        }
    }

    override fun getItemCount() = postList?.size ?: 0

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)
}