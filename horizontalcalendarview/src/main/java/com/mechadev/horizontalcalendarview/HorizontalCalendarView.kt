package com.mechadev.horizontalcalendarview

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class HorizontalCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private val dates: MutableList<Date> = mutableListOf()
    private var selectedPosition: Int = NO_POSITION
    private var todayPosition: Int = NO_POSITION

    private var monthTextColor: Int = Color.BLACK
    private var dayTextColor: Int = Color.BLACK
    private var dayNameTextColor: Int = Color.BLACK

    private var monthFont: Typeface? = null
    private var dayFont: Typeface? = null
    private var dayNameFont: Typeface? = null
    fun setupCalendar(
        startDate: Date,
        listener: OnClickCalendarListener,
        monthFont: Typeface? = null,
        dayFont: Typeface? = null,
        dayNameFont: Typeface? = null
    ) {
        this.monthFont = monthFont
        this.dayFont = dayFont
        this.dayNameFont = dayNameFont

        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        setHasFixedSize(true)
        isNestedScrollingEnabled = false

        val calendar = Calendar.getInstance().apply { time = startDate; clearTime() }
        val todayCalendar = Calendar.getInstance().apply { clearTime() }

        while (calendar.time <= todayCalendar.time) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        selectedPosition = dates.indexOf(todayCalendar.time)
        todayPosition = selectedPosition


        val adapter = CalendarAdapter(context, dates, todayPosition, listener).apply {

        }
        setAdapter(adapter)

        smoothScrollToPosition(selectedPosition)
    }

    private fun Calendar.clearTime() {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
}
