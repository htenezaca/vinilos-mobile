package com.example.vinilos_mobile.view


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.AlbumItemBinding
import com.example.vinilos_mobile.model.models.Album

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        Log.d("AlbumsAdapter", "onBindViewHolder: ${albums[position].name}")
        Glide.with(holder.viewDataBinding.root)
            .load(albums[position].cover)
            .into(holder.viewDataBinding.imageView)

        //seetup view when sreen is rotated
        val rotation = holder.viewDataBinding.root.getResources().getConfiguration().orientation
        if (rotation == 1) {
            holder.viewDataBinding.imageView.getLayoutParams().height = 500
            holder.viewDataBinding.imageView.getLayoutParams().width = 500
        } else {
            holder.viewDataBinding.imageView.getLayoutParams().height = 300
            holder.viewDataBinding.imageView.getLayoutParams().width = 300
        }

        holder.viewDataBinding.root.setOnClickListener {

            //TODO: implement navigation to album detail
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


}