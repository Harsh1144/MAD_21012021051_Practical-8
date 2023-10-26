package com.example.mad_21012021051_practical_8

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cancelAlarmCard = findViewById<MaterialCardView>(R.id.cancel_card)

        val createAlarm = findViewById<MaterialButton>(R.id.create_alarm_btn)


        createAlarm.setOnClickListener()
        {
            TimePickerDialog(this,{tp,hour,minute -> setAlarmTime(hour,minute)},Calendar.HOUR,Calendar.MINUTE,false).show()
            cancelAlarmCard.visibility = View.VISIBLE
        }

        val cancelAlarm = findViewById<MaterialButton>(R.id.cancel_alarm_btn)
        cancelAlarm.setOnClickListener()
        {
            stop()
            cancelAlarmCard.visibility = View.GONE
        }

    }

    fun setAlarmTime(hour: Int, Minute: Int){
        val alarmTime = Calendar.getInstance()
        val year = alarmTime.get(Calendar.YEAR)
        val month = alarmTime.get(Calendar.MONTH)
        val day = alarmTime.get(Calendar.DATE)
        val hour = alarmTime.get(Calendar.HOUR)
        val minute = alarmTime.get(Calendar.MINUTE)
        alarmTime.set(year,month,day,hour,minute)
        setAlarm(alarmTime.timeInMillis,AlarmBroadcastReceiver.AlARMSTART)
    }
    fun stop(){
        setAlarm(-1,AlarmBroadcastReceiver.ALARMSTOP)
    }

    fun setAlarm(militime:Long,action:String){
        val intentAlarm = Intent(applicationContext,AlarmBroadcastReceiver::class.java)
        intentAlarm.putExtra(AlarmBroadcastReceiver.ALARMKEY,action)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext,4345,intentAlarm,PendingIntent.FLAG_IMMUTABLE or  PendingIntent.FLAG_UPDATE_CURRENT)
        val manager = getSystemService(ALARM_SERVICE) as AlarmManager

        if(action === AlarmBroadcastReceiver.AlARMSTART){
            manager.setExact(AlarmManager.RTC_WAKEUP,militime,pendingIntent)
        }
        else if(action == AlarmBroadcastReceiver.ALARMSTOP){
            manager.cancel(pendingIntent)
            sendBroadcast(intentAlarm)
        }
    }

}