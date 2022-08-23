package com.example.consultasgithub.ui.gitrepositories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consultasgithub.adapters.RepositoriesAdapter
import com.example.consultasgithub.databinding.RepositoriesFragBinding


class RepositoriesFragment : Fragment() {

    private lateinit var binding: RepositoriesFragBinding

    private val viewModel: RepoViewModel by viewModels()
    private val repositoriesAdapter by lazy { RepositoriesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoriesFragBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setupRecyclerView()
        viewModel.getRepo()

    }

    private fun setObserver() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RepositoryScreenState.Loading -> {
                    showProgressBar()
                }
                is RepositoryScreenState.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, "Algo deu errado", Toast.LENGTH_SHORT).show()
                }
                is RepositoryScreenState.Success -> {
                    hideProgressBar()
                    repositoriesAdapter.submitList(state.repositories)
                }
                is RepositoryScreenState.PullsSuccess -> {
                    hideProgressBar()
                    findNavController().navigate(
                        com.example.consultasgithub.ui.gitrepositories.RepositoriesFragmentDirections.actionRepositoriesFragmentToPullRequestsFragment(
                            state.repo
                        )
                    )
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.pageProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pageProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        binding.rvRepositories.apply {
            adapter = repositoriesAdapter
            layoutManager = LinearLayoutManager(activity)

            repositoriesAdapter.onItemClicked = {
                viewModel.getPulls(repositoryItem = it)
            }
        }
    }
}