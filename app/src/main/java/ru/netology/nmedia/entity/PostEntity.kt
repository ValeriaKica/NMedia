package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var author: String,
    var content: String,
    var published: String,

    var sharing: Int,
    var likes: Int,
    var views: Int,
    var video: String,
    val likedByMe: Boolean = false,

    ) {
    fun toDto() = Post(id, author, content, published, sharing, likes, views, video, likedByMe)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.content,
                dto.published,
                dto.sharing,
                dto.likes,
                dto.views,
                dto.video,
                dto.likedByMe
            )
    }
}
