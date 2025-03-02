package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Count
import ru.netology.nmedia.dto.Post

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onRemove(post: Post) {}
    fun onEdit(post: Post) {}
    fun onVideo(post: Post) {}
    fun onPost(id: Long) {}
}


class PostAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        val bigClick = View.OnClickListener { onInteractionListener.onPost(post.id) }

        binding.apply {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            share.text = Count.formatNumber(post.sharing)
            views.text = Count.formatNumber(post.views)
            like.text = Count.formatNumber(post.likes)

            like.isChecked = post.likedByMe

            like.setOnClickListener {
                if (post.likedByMe) post.likes-- else post.likes++
                onInteractionListener.onLike(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
//                            R.id.fab -> {
//                                val posts = Post (
//                                    id = -1L,
//                                    author = it.context.getString(R.string.add_post),
//                                    content = "",
//                                    published = "",
//                                    likes = 0,
//                                    sharing = 0,
//                                    views = 0,
//                                    video = ""
//                                )
//                                onInteractionListener.onEdit(posts)
//                                true

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
            share.setOnClickListener {
               // post.sharing++
                onInteractionListener.onShare(post)
            }

            video.visibility = if (post.video.isNotEmpty())
                View.VISIBLE else View.GONE
            video.setOnClickListener {
                onInteractionListener.onVideo(post)
            }
            cardlayout.setOnClickListener(bigClick)

        }
    }
}

class PostsDiffCallBack : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}