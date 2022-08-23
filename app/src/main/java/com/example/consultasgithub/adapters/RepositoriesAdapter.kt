package com.example.consultasgithub.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consultasgithub.R
import com.example.consultasgithub.databinding.RepositoriesItensPreviewBinding
import com.example.consultasgithub.models.RepositoryItem

class RepositoriesAdapter :
    ListAdapter<RepositoryItem, RepositoriesAdapter.RepositoryViewHolder>(DIFF_UTIL) {

    var onItemClicked: ((RepositoryItem) -> Unit)? = null

   inner class RepositoryViewHolder(val binding: RepositoriesItensPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryItem: RepositoryItem?) {
            repositoryItem?.let { item ->
                binding.run {
                    tvTitle.text = item.name
                    tvDescription.text = item.description
                    tvUserName.text = item.owner?.login
                    tvForks.text = item.forksCount.toString()
                    tvStars.text = item.stargazersCount.toString()
                    Glide
                        .with(binding.root.context)
                        .load(item.owner?.avatarUrl)
                        .centerInside()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.ivUserImage)

                    itemContainer.setOnClickListener{
                        onItemClicked?.invoke(item)
                    }
                }
                item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = RepositoriesItensPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val selectedUser = getItem(position)
        holder.bind(selectedUser)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<RepositoryItem>() {
            override fun areItemsTheSame(
                oldItem: RepositoryItem,
                newItem: RepositoryItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RepositoryItem,
                newItem: RepositoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}