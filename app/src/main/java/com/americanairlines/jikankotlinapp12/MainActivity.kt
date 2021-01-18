package com.americanairlines.jikankotlinapp12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.americanairlines.jikankotlinapp12.viewmodel.JikanViewModel

class MainActivity : AppCompatActivity() {

    //implementation "androidx.activity:activity-ktx:1.1.0"
    private val viewModel: JikanViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.jikanLiveData.observe(this, Observer{
            Log.d("TAG_X", "Results obtained...${it.size}")
        })

        //
        viewModel.getSearchResults("goku")
    }
}