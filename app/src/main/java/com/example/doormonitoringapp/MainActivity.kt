package com.example.doormonitoringapp

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var m_bluetoothAdapter: BluetoothAdapter? = null

    lateinit var m_pairedDevices: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1
    var adapter : MyBluetoothAdapter? = null
    val deviceNameList: MutableList<String>? = mutableListOf<String>()
    val deviceMAC: MutableList<String>? = mutableListOf<String>()

    companion object {
        val EXTRA_ADDRESS: String = "Device_address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var BTAdapter = BluetoothAdapter.getDefaultAdapter()
        // Phone does not support Bluetooth so let the user know and exit.
        if (BTAdapter == null) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Not compatible")
                .setMessage("Your phone does not support Bluetooth")
                .setPositiveButton("Exit",
                    DialogInterface.OnClickListener { dialog, which -> System.exit(0) })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }

        if (!BTAdapter.isEnabled) {
            val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBT, REQUEST_ENABLE_BLUETOOTH)
        }



//
//        val m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
////
////
////        if (m_bluetoothAdapter != null) {
////            m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
////        }
////
//        if (adapter == null) {
//            adapter = MyBluetoothAdapter()
//        }
//        bluetoothDeviceList?.adapter = adapter
//        adapter?.setList(m_pairedDevices)

        refreshDeviceList?.setOnClickListener {
            val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            registerReceiver(receiver, filter)

            bluetoothDeviceList?.adapter = adapter
            adapter?.setList(BluetoothDevice.)
            m_bluetoothAdapter?.startDiscovery()
            if (m_bluetoothAdapter == null) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH)
            } else {

                m_bluetoothAdapter?.startDiscovery()
                m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
                (bluetoothDeviceList?.adapter as? MyBluetoothAdapter)?.let { myAdapter ->
                    myAdapter.setList(m_pairedDevices)
                }
            }
        }

//            Toast.makeText(applicationContext, "$position - ${bluetoothDevice.name}", Toast.LENGTH_SHORT).show()

//            m_bluetoothAdapter?.startDiscovery()
//        }
////        refreshDeviceList?.setOnClickListener {
////            val m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
////            m_bluetoothAdapter?.startDiscovery()
////            if (m_bluetoothAdapter.isEnabled) {
////                if (m_bluetoothAdapter != null) {
////                    m_bluetoothAdapter?.startDiscovery()
////
//////                    m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
//////                    (bluetoothDeviceList?.adapter as? MyBluetoothAdapter)?.let { myAdapter ->
//////                        myAdapter.setList(m_pairedDevices)
////
////                }
////            }
////            else {
////
////                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
////                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH)
////            }
////
////
////        }
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()


        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver)
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


