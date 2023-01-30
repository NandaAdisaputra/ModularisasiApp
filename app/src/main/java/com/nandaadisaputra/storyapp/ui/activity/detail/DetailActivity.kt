package com.nandaadisaputra.storyapp.ui.activity.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.nandaadisaputra.storyapp.R
import com.nandaadisaputra.storyapp.core.base.activity.BaseActivity
import com.nandaadisaputra.storyapp.core.data.local.constant.Const
import com.nandaadisaputra.storyapp.core.data.remote.story.StoryEntity
import com.nandaadisaputra.storyapp.core.utils.ForGlide
import com.nandaadisaputra.storyapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(R.layout.activity_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeRefresh()
        darkMode()
        initView()
        setActionBar()
        binding.lifecycleOwner = this
        binding.activity = this
        val user = intent.getParcelableExtra<StoryEntity>(EXTRA_USER) as StoryEntity
        val id = user.id
        val name = user.name.toString()
        val photoUrl= user.photoUrl.toString()
        val createdAt= user.createdAt.toString()
        val description = user.description.toString()
        val lat= user.lat
        val lon= user.lon
        var isFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = user.id.let { viewModel.checkUser(it) }
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        setStatusFavorite(true)
                        isFavorite = true
                    } else {
                        setStatusFavorite(false)
                    }
                }
            }
        }
        binding.fabFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                user.id.let { it1 -> viewModel.addToFavorite(it1,name,photoUrl,createdAt,description,lat,lon)}
                    loadingDialog.setResponse(Const.LIKES.LOADING_LIKE, R.drawable.ic_favorite)
                } else {
                    loadingDialog.setResponse(Const.LIKES.LOADING_UNLIKE, R.drawable.ic_favorite_border)
                    user.id.let { it1 -> viewModel.removeFromFavorite(it1) }
                }
                setStatusFavorite(isFavorite)
            }
        }
        private fun setStatusFavorite(isFavorite: Boolean) {
            if (isFavorite) {
                binding.fabFavorite.setIconResource(R.drawable.ic_favorite)
                binding.fabFavorite.text = Const.LIKES.LIKE
            } else {
                binding.fabFavorite.setIconResource(R.drawable.ic_favorite_border)
                binding.fabFavorite.text = Const.LIKES.UN_LIKE
            }
        }

        private fun initView() {
            val story = intent.getParcelableExtra<StoryEntity>(EXTRA_USER) as StoryEntity
            val desc = story.description
            val photo = story.photoUrl
            val name = story.name
            val date = story.createdAt
            val lat = story.lat
            val lon = story.lon

            binding.apply {
                ForGlide.loadImage(ivDetailImage, photo)
                tvDetailName.text = name
                tvDetailDescription.text = desc
                tvDetailDate.text = date
                if (lat != null || lon != null) {
                    tvLatLon.text = "$lat, $lon"
                    layLatLon.visibility = View.VISIBLE
                }
            }
        }

        private fun darkMode() {
            viewModel.getTheme.observe(this) { isDarkMode ->
                checkDarkMode(isDarkMode)
            }
        }

        private fun checkDarkMode(isDarkMode: Boolean) {
            when (isDarkMode) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        private fun swipeRefresh() {
            binding.swipeLayout.setOnRefreshListener {
                initView()
                binding.swipeLayout.isRefreshing = false
            }
        }

        private fun setActionBar() {
            supportActionBar?.title = Const.TITLE.DETAIL
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        override fun onSupportNavigateUp(): Boolean {
            onBackPressedDispatcher.onBackPressed()
            return true
        }

        companion object {
            const val EXTRA_USER = "extra_user"
        }
    }