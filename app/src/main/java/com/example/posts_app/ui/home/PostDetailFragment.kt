package com.example.posts_app.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.posts_app.R
import com.example.posts_app.data.api.responses.ApiResponseStatus
import com.example.posts_app.data.models.Post
import com.example.posts_app.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        loadDataIntoScreen()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        homeViewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    binding.pbPostDetailLoading.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> binding.pbPostDetailLoading.visibility =
                    View.VISIBLE
                is ApiResponseStatus.Success -> binding.pbPostDetailLoading.visibility = View.GONE
                else -> TODO()
            }
        }
        homeViewModel.user.observe(viewLifecycleOwner) { user ->
            with(binding) {
                tvPostDetailAuthorName.text = context?.getString(R.string.name, user?.name)
                tvPostDetailUsername.text = context?.getString(R.string.username, user?.username)
                tvPostDetailEmail.text = context?.getString(R.string.email, user?.email)
                tvPostDetailStreet.text = context?.getString(R.string.email, user?.address?.street)
                tvPostDetailSuite.text = context?.getString(R.string.suite, user?.address?.suite)
                tvPostDetailCity.text = context?.getString(R.string.city, user?.address?.city)
                tvPostDetailPhone.text = context?.getString(R.string.phone, user?.phone)
                tvPostDetailWebsite.text = context?.getString(R.string.website, user?.website)
                tvPostDetailCompanyCatchPhrase.text = context?.getString(
                    R.string.catch_phrase,
                    user?.company?.catchPhrase
                )
                tvPostDetailCompany.text = context?.getString(
                    R.string.company_name, user?.company?.name
                )
            }
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

    private fun loadDataIntoScreen() {
        val post = arguments?.getParcelable<Post>("post")
        homeViewModel.getUser(post!!)
        binding.tvPostTitlePostDetail.text = post.title
        binding.tvPostBody.text = post.body
    }


}