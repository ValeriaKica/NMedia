package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Count
import ru.netology.nmedia.dto.Post

typealias OnLikeListener =(post:Post)->Unit
typealias  OnShareListener =(post:Post)->Unit

class PostAdapter (
    private val onLikeListener:OnLikeListener,
    private val onShareListener: OnShareListener
): ListAdapter<Post,PostViewHolder>(PostsDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding, onLikeListener,onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post=getItem(position)
        holder.bind(post)
    }


}
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post){
        binding.apply {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = Count.formatNumber(post.likes)
            shareCount.text = Count.formatNumber(post.sharing)
            viewsCount.text = Count.formatNumber(post.views)

            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.baseline_favorite_border_24
            )

            like.setOnClickListener {
                if (post.likedByMe)post.likes-- else post.likes++
                onLikeListener(post)
            }

            share.setOnClickListener {
                post.sharing++
              onShareListener(post)
            }
            likeCount.text = Count.formatNumber(post.likes)
            shareCount.text = Count.formatNumber(post.sharing)

            views.setOnClickListener {
              //  viewModel.views()
            }
        }
    }
}
class PostsDiffCallBack:DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}