package com.example.posts_app.ui.home

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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
        val post = postList?.get(position)
        if (post != null) {
            postViewHolder.render(post, position)
        }
    }

    override fun getItemCount() = postList?.size ?: 0

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun render(post: Post, position: Int){
            binding.tvPostPosition.text = (position + 1).toString()
            binding.tvTilteText.text = postList?.get(position)?.title
            binding.tvPostDescriptionText.text = postList?.get(position)?.body
            if (postList?.get(position)?.isFavourite == true) binding.ivFavourite.visibility =
                View.VISIBLE
            itemView.setOnClickListener {
                val bundle =  bundleOf("post" to post)
                it.findNavController().navigate(R.id.postDetailFragmentDestination, bundle)
            }
            if (post?.isRead == false) {
                binding.tvPostPosition.setBackgroundResource(R.drawable.circle_background_blue)
                binding.tvPostPosition.setTextColor(Color.WHITE)
            }
            else{
                binding.tvPostPosition.setBackgroundResource(R.drawable.circle_background_white)
                binding.tvPostPosition.setTextColor(Color.BLACK)
            }
        }
    }
}