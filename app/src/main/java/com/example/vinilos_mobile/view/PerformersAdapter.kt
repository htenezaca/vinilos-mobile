package com.example.vinilos_mobile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.PerformerItemBinding
import com.example.vinilos_mobile.model.models.Performer

class PerformersAdapter : RecyclerView.Adapter<PerformersAdapter.PerformersViewHolder>() {

    var performers: List<Performer> = emptyList()
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