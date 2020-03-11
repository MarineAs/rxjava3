package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.API
import com.example.myapplication.model.Post
import com.example.myapplication.model.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    lateinit var observable: Observable<List<Post>>
    val compositeDisposable = CompositeDisposable()
    lateinit var api: API
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val adapter=Adapter()
//
//       recycleView.adapter=adapter
        val retrofit = RetrofitClient.getInstance()

        api = retrofit!!.create(API::class.java)

        fetchData()
    }

    private fun fetchData() {


        compositeDisposable.add(api.getPost().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                object : Consumer<List<Post>> {
                    override fun accept(t: List<Post>?) {
                        displayData(t)
                    }

                }
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun displayData(posts: List<Post>?) {
        Log.d("111", posts.toString())
    }

}
