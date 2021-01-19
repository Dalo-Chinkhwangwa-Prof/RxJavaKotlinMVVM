package com.americanairlines.jikankotlinapp12.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.americanairlines.jikankotlinapp12.view.MainActivity

class BootUpReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG_X", "The phone boot is complete..")


        try {
            val intent2 = Intent(context, MainActivity::class.java)
            intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent2)

            Log.d("TAG_X", "Starting!!!")
        } catch (e: Exception) {
            Log.d("TAG_X", "${e.localizedMessage}")

        }
    }
}