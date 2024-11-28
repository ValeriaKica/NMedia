package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.w3c.dom.Text
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

private val emty =Post(
    id = 0L,
    author = "",
    content = "",
    published = "",
    likedByMe = false
)
class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()
    val edited =MutableLiveData(emty)

    fun applyChangesAndSave(newText: String){
        edited.value?.let {
            val  text = newText.trim()
            if(text != it.content){
                repository.save(it.copy(content = text))
            }
        }
        edited.value = emty
    }
    fun edit(post: Post){
        edited.value= post
    }

    fun likeById(id : Long)=repository.likeById(id)
    fun shareById(id: Long)=repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
}