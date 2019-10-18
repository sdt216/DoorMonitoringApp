package com.example.doormonitoringapp

import android.app.AlertDialog
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val CHANNEL_ID = 213213123123
    }
    var m_bluetoothAdapter: BluetoothAdapter? = null
    lateinit var m_pairedDevices: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1
    var adapter : MyBluetoothAdapter? = null
    val handler: Handler = Handler {
        true
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Door Monitoring"
            val descriptionText = "Door Monitoring Notification Channel"
            val soundUri = RingtoneManager.getDefaultUri((RingtoneManager.TYPE_ALARM))
            val r = RingtoneManager.getRingtone(applicationContext, soundUri)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID.toString(), name, importance).apply {
                description = descriptionText
            }
            channel.setVibrationPattern(longArrayOf(1000,1000,1000,1000))
            channel.enableVibration(true)
            channel.enableLights(true)

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun test() {
        val soundUri = RingtoneManager
            .getDefaultUri(RingtoneManager.TYPE_ALARM)
        val r = RingtoneManager.getRingtone(applicationContext, soundUri)
        val intent = Intent(this, this::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val v = longArrayOf(500, 1000)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID.toString())
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_opened_door_aperture)
            .setContentTitle("ALARM")
            .setContentText("DOOR HAS BEEN OPENED")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(DEFAULT_ALL)
//            .setSound(soundUri)
//            r.play()
//            .setLights(Color.RED, 3000, 3000)





        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(ConstNoti.notiID, builder.build())
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnStart.setOnClickListener {
            createNotificationChannel()
            test()
        }
        val serverSocket: BluetoothServerSocket?
        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        // Phone does not support Bluetooth so let the user know and exit.
        if (m_bluetoothAdapter == null) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Not compatible")
                .setMessage("Your phone does not support Bluetooth")
                .setPositiveButton("Exit",
                    DialogInterface.OnClickListener { dialog, which -> System.exit(0) })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
        else {
            if (!m_bluetoothAdapter!!.isEnabled) {
                val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBT, REQUEST_ENABLE_BLUETOOTH)
            } else {
                connectBluetooth.setOnClickListener {
                    val intentOpenBluetoothSettings = Intent()
                    intentOpenBluetoothSettings.action =
                        android.provider.Settings.ACTION_BLUETOOTH_SETTINGS
                    startActivity(intentOpenBluetoothSettings)
                }

            }
        }

    }



    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(name, context, attrs)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun Testing(view: View) {
        val test = Toast.makeText(this, "HelloAll", Toast.LENGTH_SHORT)
        test.show()
    }

    fun Alert() {

    }

}


