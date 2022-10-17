package com.my.submission.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.my.submission.core.domain.model.Recipe
import com.my.submission.R
import com.my.submission.core.data.Resource
import com.my.submission.core.domain.model.Results
import com.my.submission.databinding.ActivityDetailRecipeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecipeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailRecipeViewModel: DetailRecipeViewModel by viewModel()
    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailRecipe = intent.getParcelableExtra<Recipe>(EXTRA_DATA)
        showDetailRecipe(detailRecipe)
    }

    private fun showDetailRecipe(detailRecipe: Recipe?) {
        detailRecipe?.let {
            supportActionBar?.title = detailRecipe.title
            binding.content.tvName.text = detailRecipe.title
            Glide.with(this@DetailRecipeActivity)
                .load(detailRecipe.thumb)
                .into(binding.ivDetailImage)

            var statusFavorite = detailRecipe.isFavorite
            Log.d("DetailRecipeActivity", statusFavorite.toString())
            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRecipeViewModel.setFavoriteRecipe(detailRecipe, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            detailRecipeViewModel.getDetailRecipe(detailRecipe.key).observe(this) {
                result ->
                    when (result) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            binding.content.tvDesc.text = result.data?.results?.desc
                        }
                        is Resource.Error -> {

                        }
                    }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
