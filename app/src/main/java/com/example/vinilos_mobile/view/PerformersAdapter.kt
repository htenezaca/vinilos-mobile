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
import com.example.vinilos_mobile.databinding.PerformerItemBinding
import com.example.vinilos_mobile.model.models.Band
import com.example.vinilos_mobile.model.models.Musician
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

        Log.d("PerformerAdapter", "onBindViewHolder: ${performers[position].name}")
        Glide.with(holder.viewDataBinding.root)
            .load(performers[position].image)
            .into(holder.viewDataBinding.performerImage)

        //seetup view when sreen is rotated
        val rotation = holder.viewDataBinding.root.getResources().getConfiguration().orientation
        if (rotation == 1) {
            holder.viewDataBinding.performerImage.getLayoutParams().height = 500
            holder.viewDataBinding.performerImage.getLayoutParams().width = 500
        } else {
            holder.viewDataBinding.performerImage.getLayoutParams().height = 300
            holder.viewDataBinding.performerImage.getLayoutParams().width = 300
        }

        holder.viewDataBinding.root.setOnClickListener {
            val fm = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val fragment = PerformerDetailFragment.newInstance(
                performerId = performers[position].id,
                when(performers[position]) { is Band -> true; is Musician -> false; else -> null }
            )
            fm.beginTransaction().replace(R.id.fragment_main_view, fragment)
                .addToBackStack("Performer List").commit()
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
