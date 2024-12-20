package ru.netology.nmedia.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  val groupEditPostContent = binding.group
        //  groupEditPostContent.visibility = View.GONE

        val viewModel by viewModels<PostViewModel>()

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }
        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
            if (result != null) {
                viewModel.changeContent(result)
                viewModel.save()
            }
            viewModel.clearEdit()

        }

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                viewModel.shareById(post.id)
                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                editPostLauncher.launch(post.content)
            }

            override fun onVideo(post: Post) {
                viewModel.videoById()
                if (post.video.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(intent)
                }
            }

        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = posts.size > adapter.currentList.size && adapter.currentList.isNotEmpty()
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        binding.fab.setOnClickListener() {
            newPostLauncher.launch()
        }

        //    viewModel.edited.observe(this) {
        //      with(binding.content){
        //          if (it.id != 0.toLong()){
        //               binding.group.visibility=View.VISIBLE
        //               requestFocus()
        //               setText(it.content)
        //           }
        //       }
        //    }
//
        //      binding.save.setOnClickListener {
        //        with(binding.content) {
        //          if (text.isNullOrBlank()) {
        //            Toast.makeText(this@MainActivity,
        //                    context.getString(R.string.error_empty_content),
        //                    Toast.LENGTH_SHORT
        ///                ).show()
        //                return@setOnClickListener
        //            }
//
        //              viewModel.changeContent(text.toString())
        //            viewModel.save()
//
        //              setText("")
        //            clearFocus()
        //          AndroidUtils.hideKeyboard(this)
        //              binding.group.visibility = View.GONE
        //         }
        //     }

        //       binding.clear.setOnClickListener {
//            with(binding.content) {
        //               viewModel.clearEdit()
//                setText("")
        //              groupEditPostContent.visibility = View.GONE
        //               clearFocus()
        //               AndroidUtils.hideKeyboard(it)
        //           }
        //       }

        //       binding.content.setOnClickListener{
//            binding.group.visibility=View.VISIBLE
//        }
    }
}