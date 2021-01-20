package com.americanairlines.jikankotlinapp12.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.americanairlines.jikankotlinapp12.R
import com.americanairlines.jikankotlinapp12.util.Constants.Companion.FRAGMENT_BROADCAST_ACTION
import com.americanairlines.jikankotlinapp12.viewmodel.JikanViewModel
import com.americanairlines.jikankotlinapp12.viewmodel.JikanViewModelFactory

class JikanFragment: Fragment() {

    private lateinit var resultTextView: TextView
    private lateinit var sendBroadcastButton: Button

    private val viewModel: JikanViewModel by viewModels<JikanViewModel>(factoryProducer = {
        JikanViewModelFactory
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =  inflater.inflate(R.layout.jikan_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultTextView = view.findViewById(R.id.response_textview)
        sendBroadcastButton = view.findViewById(R.id.send_message_button)

        Log.d("TAG_X_Fragment", "${viewModel}")

        viewModel.jikanLiveData.observe( viewLifecycleOwner, Observer {
            resultTextView.text = "Result size is ${it.size}"
        })

        sendBroadcastButton.setOnClickListener {
            context?.sendBroadcast(
                Intent(FRAGMENT_BROADCAST_ACTION).also {
                    it.putExtra("nonsense", "cowabanga!")
                }
            )
        }

    }
}










