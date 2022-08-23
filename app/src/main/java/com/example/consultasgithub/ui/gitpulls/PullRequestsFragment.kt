package com.example.consultasgithub.ui.gitpulls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consultasgithub.adapters.PullsAdapter
import com.example.consultasgithub.databinding.PullRequestFragBinding
import com.example.consultasgithub.util.loadCustomTabForSite


class PullRequestsFragment : Fragment() {
    //todo mudar as classes
    private lateinit var binding: PullRequestFragBinding

    private val viewModel: PullRequestViewModel by viewModels()
    private val pullsAdapter by lazy { PullsAdapter() }

    private val args: com.example.consultasgithub.ui.gitpulls.PullRequestsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PullRequestFragBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setupRecyclerView()
        viewModel.getPulls(repositoryItem = args.repo)

    }

    private fun setObserver() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PullsScreenState.Loading -> {
                    showProgressBar()
                }
                is PullsScreenState.Error -> {
                    hideProgressBar()
                    Toast.makeText(context, "Algo deu errado", Toast.LENGTH_SHORT).show()
                }
                is PullsScreenState.Success -> {
                    hideProgressBar()
                    pullsAdapter.submitList(state.pulls)
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.pullProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pullProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        binding.rvPullRequest.apply {
            adapter = pullsAdapter
            layoutManager = LinearLayoutManager(activity)

            pullsAdapter.onItemClicked = {
                context.loadCustomTabForSite(it.htmlUrl.orEmpty())
            }
        }
    }
}