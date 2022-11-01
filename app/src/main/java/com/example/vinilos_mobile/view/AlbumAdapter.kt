package com.example.vinilos_mobile.view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.AlbumItemBinding
import com.example.vinilos_mobile.model.models.Album

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding): RecyclerView.ViewHolder(viewDataBinding.root){
        companion object{
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

    var albums: List<Album> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumListFragmentDirections.actionAlbumListFragment2ToAlbumDetailFragment()
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }




}