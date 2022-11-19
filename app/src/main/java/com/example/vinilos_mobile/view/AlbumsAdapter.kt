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
import com.example.vinilos_mobile.databinding.AlbumItemBinding
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.models.Track

class AlbumsAdapter(albums: List<Album> = emptyList()) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<Album> = albums
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
            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.AUTOMATIC)
            .into(holder.viewDataBinding.albumListImageView)

        //setup view when screen is rotated
        /*val rotation = holder.viewDataBinding.root.getResources().getConfiguration().orientation
        if (rotation == 1) {
            holder.viewDataBinding.albumListImageView.getLayoutParams().height = 500
            holder.viewDataBinding.albumListImageView.getLayoutParams().width = 500
        } else {
            holder.viewDataBinding.albumListImageView.getLayoutParams().height = 300
            holder.viewDataBinding.albumListImageView.getLayoutParams().width = 300
        }*/

        holder.viewDataBinding.root.setOnClickListener {
            // Use the fragment manager to replace the main fragment view
            // Get the child fragmentManager
            val fm = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val fragment = AlbumDetailFragment.newInstance(albumId = albums[position].albumId)
            // Add the current fragment to the back stack
            fm.beginTransaction().replace(R.id.fragment_main_view, fragment)
                .addToBackStack("Album List").commit()
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


}
