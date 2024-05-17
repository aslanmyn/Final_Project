package com.aslan.okko.adapter.tvadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aslan.okko.databinding.ItemRowBinding
import com.aslan.okko.model.tvPopular.TvResult
import com.bumptech.glide.Glide

class TvPopularAdapter(private val tvList:List<TvResult>,val context: Context,val onTvClickListener:(item:TvResult)->Unit):
RecyclerView.Adapter<TvPopularAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item: TvResult){
            binding.movieId.text=item.name
            val url = "https://image.tmdb.org/t/p/w500/${item.backdropPath}"
            Glide.with(context)
                .load(url)
                .into(binding.imageOfData)
            binding.root.setOnClickListener {
                onTvClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view=tvList[position]
        holder.bind(view)
    }
    override fun getItemCount(): Int {

        return tvList.size

    }



}