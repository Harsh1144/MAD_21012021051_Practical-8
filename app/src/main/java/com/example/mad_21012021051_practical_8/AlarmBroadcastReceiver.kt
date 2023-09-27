package com.example.mad_21012021051_practical_8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {
companion object{
    val ALARMKEY ="KEY"
    val ALARMSTART ="START"
    val ALARMSTOP ="STOP"

}
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val data = intent.getStringExtra(ALARMKEY)
        val intentService = Intent(context, AlarmService::class.java)
        if (data == ALARMSTART) {
            context.startService(intentService)
        }
        else{
            context.stopService(intentService)
        }
    }
}