package com.example.blank.data.remote

import com.example.blank.data.remote.dto.PostRequest
import com.example.blank.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.text.get

class PostsApiImpl(
    private val client: HttpClient
) : PostApi {
    override suspend fun getPosts(): List<PostResponse> {
        return client.get{
            url("https://jsonplaceholder.typicode.com/posts")
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        TODO("Not yet implemented")
    }
}