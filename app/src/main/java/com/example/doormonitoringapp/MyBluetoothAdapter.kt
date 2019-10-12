package com.example.doormonitoringapp

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bluetooth.view.*

class MyBluetoothAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list : MutableList<BluetoothDevice> = mutableListOf()
    var onItemClickListener: ((bluetoothDevice: BluetoothDevice, position: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bluetooth, parent, false)
        )
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bluetoothDevice = list.get(position)
        holder.itemView.name?.text = bluetoothDevice.name
        holder.itemView.address?.text = bluetoothDevice.address
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(list[position], position)
        }
    }

    fun setList(set: Set<BluetoothDevice>) {
        list.removeAll {
            true
        }
        for (item in set) {
            list.add(item)
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


