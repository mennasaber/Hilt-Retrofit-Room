package com.example.hiltapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltapplication.R
import com.example.hiltapplication.model.Post
import com.example.hiltapplication.ui.MainStateEvent.GetPostsEvent
import com.example.hiltapplication.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setStateEvent(GetPostsEvent)
    }


    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Post>> -> {
                    displayProgress(false)
                    appendPostTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message)
                    displayProgress(false)
                }
                is DataState.Loading -> {
                    displayProgress(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) findViewById<TextView>(R.id.postLV).text =
            message else findViewById<TextView>(R.id.postLV).text = "Unknown error."
    }

    private fun displayProgress(display: Boolean) {
        if (display) findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        else findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
    }

    private fun appendPostTitles(posts: List<Post>) {
        val sb = StringBuilder()
        for (post in posts) {
            sb.append(post.postTitle + "\n")
        }
        findViewById<TextView>(R.id.postLV).text = sb.toString()
    }

}