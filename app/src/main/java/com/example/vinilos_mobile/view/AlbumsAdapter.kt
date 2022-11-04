package com.example.vinilos_mobile.view


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.AlbumItemBinding
import com.example.vinilos_mobile.model.models.Album
import com.squareup.picasso.Picasso

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
            Log.d("image", it.album!!.cover)
            Picasso.get().load(it.album!!.cover).into(it.imageView)
        }
        holder.viewDataBinding.root.setOnClickListener {
            // val action =
            // Navigate using that action
            // holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


}