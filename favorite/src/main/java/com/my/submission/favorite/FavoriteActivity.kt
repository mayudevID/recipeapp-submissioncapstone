package com.my.submission.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.submission.core.ui.RecipeAdapter
import com.my.submission.detail.DetailRecipeActivity
import com.my.submission.favorite.databinding.ActivityFavoriteBinding
import com.my.submission.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite Recipe"

        val recipeAdapter = RecipeAdapter()
        recipeAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailRecipeActivity::class.java)
            intent.putExtra(DetailRecipeActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteRecipe.observe(this) { dataRecipe ->
            recipeAdapter.setData(dataRecipe)
            binding.viewEmpty.root.visibility =
                if (dataRecipe.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvRecipe) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recipeAdapter
        }
    }
}