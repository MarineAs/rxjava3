package com.example.myapplication.model




import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val url = "https://jsonplaceholder.typicode.com/"
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit? {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
            }
            return instance
        }
    }
}


