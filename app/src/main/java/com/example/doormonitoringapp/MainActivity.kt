package com.example.doormonitoringapp

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var m_bluetoothAdapter: BluetoothAdapter? = null
    lateinit var m_pairedDevices: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1
    var adapter : MyBluetoothAdapter? = null
    val handler: Handler = Handler {
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            if (m_bluetoothAdapter!!.isEnabled) {
                val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBT, REQUEST_ENABLE_BLUETOOTH)
            } else {
                connectBluetooth?.setOnClickListener {
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


