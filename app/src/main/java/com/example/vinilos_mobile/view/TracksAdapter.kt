package com.example.vinilos_mobile.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.TrackItemBinding
import com.example.vinilos_mobile.model.models.Track

class TracksAdapter(tracks: List<Track> = emptyList() ) : RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {

    var tracks: List<Track> = tracks
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class TracksViewHolder(val viewDataBinding: TrackItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.track_item
        }
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.track = tracks[position]
        }

        Log.d("AlbumsAdapter", "onBindViewHolder: ${tracks[position].name}")

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TracksAdapter.TracksViewHolder {
        val withDataBinding: TrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TracksAdapter.TracksViewHolder.LAYOUT,
            parent,
            false
        )

        return TracksAdapter.TracksViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }
}
