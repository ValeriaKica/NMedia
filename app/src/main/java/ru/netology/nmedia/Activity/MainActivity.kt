package ru.netology.nmedia.Activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.type.content
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Count
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.focusAndShowKeyboard
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val groupEditPostContent = binding.group
        groupEditPostContent.visibility = View.GONE

        val viewModel by viewModels<PostViewModel>()

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

        })

        binding.list.adapter = adapter
     //   binding.list.itemAnimator = null

        viewModel.data.observe(this) { posts ->
            val newPost = posts.size > adapter.currentList.size && adapter.currentList.isNotEmpty()
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }
        viewModel.edited.observe(this) {
           with(binding.content){
               if (it.id != 0.toLong()){
                   binding.group.visibility=View.VISIBLE
                   requestFocus()
                   setText(it.content)
               }
           }
        }
        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.group.visibility = View.GONE
            }
        }

        binding.clear.setOnClickListener {
            with(binding.content) {
                viewModel.clearEdit()
                setText("")
                groupEditPostContent.visibility = View.GONE
                clearFocus()
                AndroidUtils.hideKeyboard(it)
            }
        }

        binding.content.setOnClickListener{
            binding.group.visibility=View.VISIBLE
        }
//Касательно второй задачи, если пользователь отменит редактирование и попытается создать новый пост,
        // то вместо нового поста будет отредактирован тот, редактирование которого отменяли.
        // Дело в том, что при отмене редактирования вы не сбрасываете поле edited вьюмодели до дефолтного
        // значения и в нём продолжает “лежать” старый пост.


    }
}