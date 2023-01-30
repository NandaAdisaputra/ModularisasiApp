package com.nandaadisaputra.storyapp.ui.activity.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.crocodic.core.extension.openActivity
import com.nandaadisaputra.storyapp.R
import com.nandaadisaputra.storyapp.core.base.activity.BaseActivity
import com.nandaadisaputra.storyapp.core.data.local.constant.Const
import com.nandaadisaputra.storyapp.core.data.remote.story.StoryEntity
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteEntity
import com.nandaadisaputra.storyapp.databinding.ActivityFavoriteBinding
import com.nandaadisaputra.storyapp.databinding.ItemStoryBinding
import com.nandaadisaputra.storyapp.ui.activity.detail.DetailActivity
import com.nandaadisaputra.storyapp.ui.activity.setting.SettingActivity
import com.nuryazid.core.base.adapter.CoreListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity :
    BaseActivity<ActivityFavoriteBinding, FavoriteViewModel>(R.layout.activity_favorite) {
    private val favoriteUser = ArrayList<StoryEntity?>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.activity = this
        swipeRefresh()
        initAppbar()
        observeApp()
        darkMode()
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
        binding.swiftLayout.setOnRefreshListener {
            swipeRefresh()
            initAppbar()
            observeApp()
            binding.swiftLayout.isRefreshing = false
        }
    }

    private fun observeApp() {
        showLoading(true)
        viewModel.getAllFavorites()?.observe(this) {
            binding.adapter = CoreListAdapter<ItemStoryBinding, StoryEntity>(
               R.layout.item_story
            )
                .initItem(favoriteUser) { _, data ->
                    openActivity<DetailActivity> {
                        putExtra(DetailActivity.EXTRA_USER, data)
                    }
                }
        }
        fun setList(user: ArrayList<StoryEntity>) {
            favoriteUser.clear()
            favoriteUser.addAll(user)
            binding.rvFavoriteUser.adapter?.notifyDataSetChanged()
            showLoading(false)
        }

        viewModel.getAllFavorites()?.observe(this) { favoriteList ->
            if (favoriteList != null) {
                if (favoriteList.isNotEmpty()) {
                    val user = mapList(favoriteList)
                    setList(user)
                    showEmpty(false)
                } else {
                    showEmpty(true)
                    showLoading(false)
                }
            }
        }
    }

    private fun mapList(listFavorites: List<FavoriteEntity>): ArrayList<StoryEntity> {
        val listUser = ArrayList<StoryEntity>()
        for (user in listFavorites) {
            val userMapped = user.name?.let {
                user.photoUrl?.let { it1 ->
                    StoryEntity(
                        id = user.id,
                        name = it,
                        photoUrl= it1,
                        createdAt = user.createdAt.toString(),
                        description= user.description.toString(),
                        lat= user. lat,
                        lon= user. lon,
                    )
                }
            }
            userMapped?.let { listUser.add(it) }
        }
        return listUser
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                openActivity<SettingActivity> { }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initAppbar() {
        val actionBar = supportActionBar
        actionBar?.title = Const.CONS.FAVORITE_USER
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun showEmpty(state: Boolean) {
        binding.favoriteEState.root.isVisible = state
        binding.rvFavoriteUser.isGone = state
    }
    private fun showLoading(state: Boolean) {
        binding.progressbar.isVisible = state
    }
}