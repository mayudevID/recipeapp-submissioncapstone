package com.my.submission.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.submission.core.data.Resource
import com.my.submission.core.ui.RecipeAdapter
import com.my.submission.R
import com.my.submission.databinding.FragmentHomeBinding
import com.my.submission.detail.DetailRecipeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

//import com.my.submission.detail.DetailRecipeActivity


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val recipeAdapter = RecipeAdapter()
            recipeAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRecipeActivity::class.java)
                intent.putExtra(DetailRecipeActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.recipe.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    when (recipe) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            recipeAdapter.setData(recipe.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                recipe.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvRecipe) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = recipeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
