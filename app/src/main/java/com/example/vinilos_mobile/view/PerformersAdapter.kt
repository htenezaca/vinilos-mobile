package com.example.vinilos_mobile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.PerformerItemBinding
import com.example.vinilos_mobile.model.models.Performer

class PerformersAdapter(performers: List<Performer> = emptyList() ) : RecyclerView.Adapter<PerformersAdapter.PerformersViewHolder>() {

    var performers: List<Performer> = performers
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class PerformersViewHolder(val viewDataBinding: PerformerItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.performer_item
        }
    }

    override fun onBindViewHolder(holder: PerformersViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.performer = performers[position]
        }

        Log.d("AlbumsAdapter", "onBindViewHolder: ${performers[position].name}")
        Glide.with(holder.viewDataBinding.root)
            .load(performers[position].image)
            .into(holder.viewDataBinding.performerImage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PerformersAdapter.PerformersViewHolder {
        val withDataBinding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformersAdapter.PerformersViewHolder.LAYOUT,
            parent,
            false
        )

        return PerformersAdapter.PerformersViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return performers.size
    }
}
