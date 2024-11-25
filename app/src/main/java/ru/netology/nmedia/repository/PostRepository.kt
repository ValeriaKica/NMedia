package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
   // fun get(): LiveData<Post>//пост который изменяется в реальном времению, за изменением которого можно следить
   // fun like()
   // fun share()
   // fun views()
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)

}