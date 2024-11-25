package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()
  //  fun like() = repository.like()
   // fun share() = repository.share()
   // fun views() =repository.views()
    fun likeById(id : Long)=repository.likeById(id)
    fun shareById(id: Long)=repository.shareById(id)
}