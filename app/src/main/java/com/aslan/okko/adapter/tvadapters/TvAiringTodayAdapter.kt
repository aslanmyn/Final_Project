package com.aslan.okko.adapter.tvadapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aslan.okko.databinding.ItemRowBinding
import com.aslan.okko.model.tvAiringToday.TvAiringTodayResult
import com.bumptech.glide.Glide

class TvAiringTodayAdapter(
    private var tvAiringTodayList: List<TvAiringTodayResult>, var context: Context,
    val onTvClickListener: (item: TvAiringTodayResult) -> Unit): RecyclerView.Adapter<TvAiringTodayAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TvAiringTodayResult) {
            binding.movieId.text = item.name
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
        val item=tvAiringTodayList[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
          return tvAiringTodayList.size
    }
}