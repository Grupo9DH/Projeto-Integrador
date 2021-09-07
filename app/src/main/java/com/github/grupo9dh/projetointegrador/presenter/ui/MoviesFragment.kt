package com.github.grupo9dh.projetointegrador.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.grupo9dh.projetointegrador.R
import com.github.grupo9dh.projetointegrador.databinding.FragmentMoviesBinding
import com.github.grupo9dh.projetointegrador.presenter.viewmodel.MoviesViewModel
import com.github.grupo9dh.projetointegrador.util.createProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val dialog by lazy { requireContext().createProgressDialog() }
    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!
    private val viewModel by viewModel<MoviesViewModel>()
    private val adapter by lazy { MoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        viewModel.getMoviesList()

        viewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                MoviesViewModel.State.Loading -> {}
                is MoviesViewModel.State.Error -> {}
                is MoviesViewModel.State.Success -> {
                    adapter.submitList(it.moviesList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
