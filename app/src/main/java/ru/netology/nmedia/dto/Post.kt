package ru.netology.nmedia.dto

data class Post(
    var id: Long,
    var author: String,
    var content: String,
    var published: String,

    var sharing: Int = 0,
    var likes: Int = 0,
    var views: Int = 0,
    var video: String = "",
    val likedByMe: Boolean = false,

    )



