package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Count
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = Count.formatNumber(post.like)
            shareCount.text = Count.formatNumber(post.sharing)
            viewsCount.text = Count.formatNumber(post.views)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe

                like.setImageResource(
                    if (post.likedByMe) {
                        post.like++
                        R.drawable.ic_liked_24
                    } else {
                        post.like--
                        R.drawable.baseline_favorite_border_24
                    }
                )
                likeCount.text = Count.formatNumber(post.like)
            }

            share.setOnClickListener {
                post.sharing++
                shareCount.text = Count.formatNumber(post.sharing)
            }

            views.setOnClickListener {
                post.views++
                viewsCount.text = Count.formatNumber(post.views)
            }

        }
    }
}