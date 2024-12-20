package ru.netology.nmedia.dto

data class Post(
    var id: Long,
    var author: String,
    var content: String,
    var published: String,

    var sharing: Int = 999,
    var likes: Int = 999,
    var views: Int = 999,
    var video:String="",
    var likedByMe: Boolean = false,
)

