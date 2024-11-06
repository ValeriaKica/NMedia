package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,

    val sharing: Int = 999,
    val likes: Int = 999,
    val views: Int = 999,
    val likedByMe: Boolean = false,
)

