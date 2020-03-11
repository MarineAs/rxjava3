package com.example.myapplication.model


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface API {

    @GET("posts")
    fun getPost(): Observable<List<Post>>
}
