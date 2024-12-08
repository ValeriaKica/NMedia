package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val emty = Post(
    id = 0L,
    author = "",
    content = "",
    published = "",
    likedByMe = false
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()
    val edited = MutableLiveData(emty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = emty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun clearEdit() {
        edited.value = emty
    }


    //  fun applyChangesAndSave(newText: String){
    //   edited.value?.let {
    //       val  text = newText.trim()
    //        if(text != it.content){
    //           repository.save(it.copy(content = text))
    //            return
    //        }
    //    }
    //    edited.value = emty
    // }
    // fun edit(post: Post){
    //     edited.value= post
    // }

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
}