package com.americanairlines.jikankotlinapp12.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.americanairlines.jikankotlinapp12.R
import com.americanairlines.jikankotlinapp12.model.JikanResult
import com.americanairlines.jikankotlinapp12.util.Constants.Companion.FRAGMENT_BROADCAST_ACTION
import com.americanairlines.jikankotlinapp12.view.adapter.JikanAdapter
import com.americanairlines.jikankotlinapp12.viewmodel.JikanViewModel
import com.americanairlines.jikankotlinapp12.viewmodel.JikanViewModelFactory
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    //implementation "androidx.activity:activity-ktx:1.1.0"
    private val viewModel: JikanViewModel by viewModels<JikanViewModel>(factoryProducer = {
        JikanViewModelFactory
    })

    var bgColor = AtomicBoolean(true)
    private lateinit var mainLayout:ConstraintLayout

    private val broadcastReceiver:BroadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {

            intent.getStringExtra("nonsense")?.let {

                Log.d("TAG_X", "MODE CHANGED")
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()

                if(bgColor.getAndSet(false))
                    mainLayout.setBackgroundColor(Color.GRAY)
                else{
                    mainLayout.setBackgroundColor(Color.WHITE)
                    bgColor.set(true)

                }
            }
        }
    }


    private lateinit var searchEditText: EditText
    private lateinit var jikanRecyclerView: RecyclerView

    private val jikanFragment: JikanFragment = JikanFragment()

    private val jikanAdapter: JikanAdapter = JikanAdapter(mutableListOf())
    var searchTimer = Timer()


    override fun onRestart() {
        super.onRestart()
        Log.d("TAG_X", "onRestart")
    }


    override fun onResume() {
        super.onResume()
        Log.d("TAG_X", "onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG_X", "onCreate")

        mainLayout = findViewById(R.id.main_layout)
        loadJikanFragment()
        Log.d("TAG_X_Main", "${viewModel}")
        searchEditText = findViewById(R.id.search_edittext)

        jikanRecyclerView = findViewById(R.id.jikan_recylerview)
        jikanRecyclerView.adapter = jikanAdapter

        val itemSnapHelper: SnapHelper = LinearSnapHelper()
        itemSnapHelper.attachToRecyclerView(jikanRecyclerView)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not used
                Log.d("TAG_X", "before: $p0")
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Not used
                Log.d("TAG_X", "onTextChanged: ${text}")
                searchTimer.cancel()
                searchTimer = Timer()

                searchTimer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            Log.d("TAG_X", "Hello....")
                            viewModel.getSearchResults(text.toString())
                        }

                    }, 3000
                )
            }
        })
        //Java
        //searchEditText.addTextChangedListener(new TextWatcher(){});

        //call.enqueue(new Callback<T>...)
        //call.enqueue(object: Calllback<T>{})

        viewModel.jikanLiveData.observe(this, Observer {
            Log.d("TAG_X", "Results obtained...${it.size}")
            jikanAdapter.updateJikanList(it)
        })
        //
        viewModel.getSearchResults("Goku")
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun loadJikanFragment() {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame, jikanFragment)
            .commit()

    }
}














