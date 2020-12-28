package com.example.crudapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.Database.Helm
import kotlinx.android.synthetic.main.adapter_helm.view.*

class HelmAdapter (private val AllHelm: ArrayList<Helm>, private val listener: OnAdapterListener) : RecyclerView.Adapter<HelmAdapter.HelmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelmViewHolder {
        return HelmViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_helm, parent, false)
        )
    }

    override fun getItemCount() = AllHelm.size

    override fun onBindViewHolder(holder: HelmViewHolder, position: Int) {
        val helm = AllHelm[position]
        holder.view.text_merk.text = helm.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(helm)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(helm)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(helm)
        }
    }

    class HelmViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Helm>) {
        AllHelm.clear()
        AllHelm.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(helm: Helm)
        fun onDelete(helm: Helm)
        fun onUpdate(helm: Helm)
    }
}