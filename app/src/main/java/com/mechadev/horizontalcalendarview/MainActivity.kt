package com.mechadev.horizontalcalendarview

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var horizontalCalendarView: HorizontalCalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateString = "04.12.2024"
        val startDate: Date = try {
            dateFormat.parse(dateString) ?: throw IllegalArgumentException("Invalid date format")
        } catch (e: ParseException) {
            Log.e("DATE_PARSE", "Date parsing error: ${e.message}")
            Date()
        }


        horizontalCalendarView = findViewById(R.id.horizontal)

        horizontalCalendarView.setupCalendar(
            startDate = startDate,
            listener = object : OnClickCalendarListener {
                override fun clicked(date: Date) {
                  //  Toast.makeText(applicationContext, date.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}