package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,

    var sharing:Int=1200,
    var like:Int =1000000,
    var views:Int=1100000,
    var likedByMe: Boolean = false,
)

