package com.example.consultasgithub.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consultasgithub.R
import com.example.consultasgithub.databinding.PullItemPreviewBinding
import com.example.consultasgithub.models.PullResponseItem


class PullsAdapter :
    ListAdapter<PullResponseItem, PullsAdapter.PullsViewHolder>(DIFF_UTIL) {

    var onItemClicked: ((PullResponseItem) -> Unit)? = null

    inner class PullsViewHolder(val binding: PullItemPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pullResponseItem: PullResponseItem) {
            pullResponseItem.let { item ->
                binding.run {
                    tvPullTitle.text = item.title
                    tvBody.text = item.body
                    tvPullUserName.text = item.user?.login
                    tvPullData.text = item.createdAt
                    Glide
                        .with(binding.root.context)
                        .load(item.user?.avatarUrl)
                        .centerInside()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.ivPullUserImage)

                    pullItemContainer.setOnClickListener{
                        onItemClicked?.invoke(item)
                    }
                }
                item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        val view = PullItemPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        val selectedUser = getItem(position)
        holder.bind(selectedUser)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PullResponseItem>() {
            override fun areItemsTheSame(
                oldItem: PullResponseItem,
                newItem: PullResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PullResponseItem,
                newItem: PullResponseItem
            ): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}