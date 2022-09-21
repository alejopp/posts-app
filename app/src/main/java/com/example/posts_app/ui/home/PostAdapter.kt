package com.example.posts_app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts_app.data.models.Post
import com.example.posts_app.databinding.ItemPostBinding

class PostAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, position: Int) {
        postViewHolder.binding.tvPostPosition.text = (position + 1).toString()
        postViewHolder.binding.tvTilteText.text = postList[position].title
        postViewHolder.binding.tvPostDescriptionText.text = (position + 1).toString()
        if (postList[position].isFavourite) postViewHolder.binding.ivFavourite.visibility =
            View.VISIBLE
    }

    override fun getItemCount() = postList.size

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)
}