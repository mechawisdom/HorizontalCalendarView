package com.mechadev.horizontalcalendarview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.coroutineContext

@SuppressLint("NotifyDataSetChanged")
class CalendarAdapter(
    private val context: Context,
    private val dates: List<Date>,
    private val todayPosition: Int,
    private val listener: OnClickCalendarListener
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val dayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
    private val dayOfMonthFormat = SimpleDateFormat("dd", Locale.getDefault())
    private val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
    private var selectedPosition: Int = todayPosition
    private var monthTextColor: Int = ContextCompat.getColor(context,R.color.colorOnPrimaryVariant)
    private var dayTextColor: Int = ContextCompat.getColor(context,R.color.colorOnPrimary)
    private var dayNameTextColor: Int =ContextCompat.getColor(context,R.color.colorOnPrimaryVariant)

    private var monthFont: Typeface? = null
    private var dayFont: Typeface? = null
    private var dayNameFont: Typeface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false),
            monthTextColor, dayTextColor, dayNameTextColor,
            monthFont, dayFont, dayNameFont
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val date = dates[position]
        holder.dayNameTextView.text = dayOfWeekFormat.format(date)
        holder.dayTextView.text = dayOfMonthFormat.format(date)
        holder.monthTextView.text = monthFormat.format(date)

        holder.itemView.setOnClickListener {
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)
            listener.clicked(date)
        }
        updateItemView(holder, position)
    }

    private fun updateItemView(holder: ViewHolder, position: Int) {
        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.calendar_selected_background)
            if (todayPosition == position) {
                holder.todayBadge.visibility = View.VISIBLE
            } else {
                holder.todayBadge.visibility = View.GONE
            }
        } else if (todayPosition == position) {
            holder.todayBadge.visibility = View.VISIBLE
        } else {
            holder.itemView.setBackgroundResource(R.drawable.calendar_unselected_background)
            holder.todayBadge.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = dates.size

    fun setSelectedPosition(position: Int) {
        if (position in 0 until itemCount) {
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    class ViewHolder(
        itemView: View,
        monthTextColor: Int,
        dayTextColor: Int,
        dayNameTextColor: Int,
        monthFont: Typeface?,
        dayFont: Typeface?,
        dayNameFont: Typeface?
    ) : RecyclerView.ViewHolder(itemView) {
        val monthTextView: TextView = itemView.findViewById(R.id.monthTextView)
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val dayNameTextView: TextView = itemView.findViewById(R.id.dayNameTextView)
        val todayBadge: View = itemView.findViewById(R.id.todayBadge)

        init {
            monthTextView.setTextColor(monthTextColor)
            dayTextView.setTextColor(dayTextColor)
            dayNameTextView.setTextColor(dayNameTextColor)

            monthTextView.typeface = monthFont
            dayTextView.typeface = dayFont
            dayNameTextView.typeface = dayNameFont
        }
    }

    fun setMonthTextColor(color: Int) {
        monthTextColor = color
        notifyDataSetChanged()
    }

    fun setDayTextColor(color: Int) {
        dayTextColor = color
        notifyDataSetChanged()
    }

    fun setDayNameTextColor(color: Int) {
        dayNameTextColor = color
        notifyDataSetChanged()
    }

    fun setMonthFont(font: Typeface?) {
        monthFont = font
        notifyDataSetChanged()
    }

    fun setDayFont(font: Typeface?) {
        dayFont = font
        notifyDataSetChanged()
    }

    fun setDayNameFont(font: Typeface?) {
        dayNameFont = font
        notifyDataSetChanged()
    }
}
