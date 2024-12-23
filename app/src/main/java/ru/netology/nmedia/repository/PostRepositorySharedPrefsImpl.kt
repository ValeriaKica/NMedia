package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post

class PostRepositorySharedPrefsImpl (context: Context): PostRepository{

    companion object{
        private const val KEY ="posts"
        private  val gson = Gson()
    }

    private  val prefs =context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private  val typeToken = TypeToken.getParameterized(List::class.java,Post:: class.java).type
    private var nextId = 1L
    private var posts = emptyList<Post>()
        set(value){
            field=value
            sync()
        }
    private val data = MutableLiveData(posts)

    init {
        prefs.getString(KEY,null)?.let {
            posts = gson.fromJson(it,typeToken)
            nextId = posts.maxOfOrNull { it.id }?.inc() ?:1
            data.value = posts
        }
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharing = it.sharing + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun videoById() {
        data.value = posts
    }

    private fun sync(){
        with(prefs.edit()){
            putString(KEY,gson.toJson(posts))
            apply()
        }
    }
}