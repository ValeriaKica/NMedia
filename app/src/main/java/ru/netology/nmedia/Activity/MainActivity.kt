package ru.netology.nmedia.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Count
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)//
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter =PostAdapter({
            viewModel.likeById(it.id)
        },{
          viewModel.shareById(it.id)
        })
        
        binding.list.adapter = adapter 
        viewModel.data.observe(this){posts->
            adapter.list=posts
        }

       // viewModel.data.observe(this) { post ->
       //     with(binding) {
       //         author.text = post.author
       //         content.text = post.content
       //         published.text = post.published
       //         likeCount.text = Count.formatNumber(post.likes)
       //         shareCount.text = Count.formatNumber(post.sharing)
       //         viewsCount.text = Count.formatNumber(post.views)
       //         like.setImageResource(
       //             if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.baseline_favorite_border_24
       //         )
       //         likeCount.text = Count.formatNumber(post.likes)
       //         shareCount.text = Count.formatNumber(post.sharing)
        //        viewsCount.text = Count.formatNumber(post.views)
        //    }
      //  }

      //  binding.like.setOnClickListener {
      //      viewModel.like()
      //  }

      //  binding.share.setOnClickListener {
       //     viewModel.share()
      //  }

      //  binding.views.setOnClickListener {
      //      viewModel.views()
      //  }

    }
}