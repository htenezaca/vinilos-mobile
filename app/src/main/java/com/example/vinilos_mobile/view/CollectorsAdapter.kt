package com.example.vinilos_mobile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.CollectorItemBinding
import com.example.vinilos_mobile.model.models.Collector

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>() {
    private var iconCollector:String = "https://firebasestorage.googleapis.com/v0/b/storage-cdabb.appspot.com/o/%E2%86%B3%20Image.png?alt=media&token=b474bbd7-8a90-45e1-8cfa-fa35bb6cc600"
    var collectors: List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        Log.d("CollectorsAdapter", "onBindViewHolder: ${collectors[position].name}")
        Glide.with(holder.viewDataBinding.root)
            .load(iconCollector)
            .into(holder.viewDataBinding.collectorImageView)
        holder.viewDataBinding.root.setOnClickListener {
            // Use the fragment manager to replace the main fragment view
            // Get the child fragmentManager
            val fm = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val fragment = CollectorDetailFragment.newInstance(collectorId = collectors[position].collectorId)
            // Add the current fragment to the back stack
            fm.beginTransaction().replace(R.id.fragment_main_view, fragment)
                .addToBackStack("Collector List").commit()
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


}
