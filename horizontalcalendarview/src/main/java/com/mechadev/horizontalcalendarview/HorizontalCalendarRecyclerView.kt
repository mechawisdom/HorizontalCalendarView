package com.mechadev.horizontalcalendarview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//
//class HorizontalCalendarRecyclerView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyle: Int = 0
//) : RecyclerView(context, attrs, defStyle) {
//
//    init {
//        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
//        setHasFixedSize(true)
//        isNestedScrollingEnabled = false
//    }
//
//    fun attachAdapter(adapter: Adapter<*>) {
//        this.adapter = adapter
//    }
//
//    fun smoothScrollToPositionWithDelay(position: Int, delayMillis: Long = 500) {
//        Handler(Looper.getMainLooper()).postDelayed({
//            smoothScrollToPosition(position)
//        }, delayMillis)
//    }
//}
