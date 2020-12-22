package com.example.ducamiteach.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ducamiteach.R
import com.example.ducamiteach.WeatherActivity
import com.example.ducamiteach.data.Daily
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainAdapter(private val list : List<Daily>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val itemView : View) : RecyclerView.ViewHolder(itemView) {
        val format = SimpleDateFormat("MM월 dd일")
        fun bind(daily: Daily) {
            itemView.weather_ico.setImageResource(WeatherActivity.iconInformation[daily.weather[0].main] ?: R.drawable.sunny)
            itemView.temp.text = "${daily.temp.max}℃"
            itemView.date.text = format.format(Calendar.getInstance().time)
            itemView.status.text = daily.weather[0].description
        }
    }
}