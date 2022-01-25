package com.example.blank.data.remote

import com.example.blank.data.remote.dto.PostRequest
import com.example.blank.data.remote.dto.PostResponse

interface PostApi {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?
}