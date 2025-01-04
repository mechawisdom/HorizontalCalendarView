# HorizontalCalendarView

`HorizontalCalendarView` is a simple and customizable calendar view for Android, designed to display a horizontally scrolling calendar where users can select a date. This is perfect for date selection purposes in your app, providing an intuitive and elegant solution for date-related functionalities.

![download](https://github.com/user-attachments/assets/31734d4c-6688-4541-892d-702311f7e510)


## Features
- Customizable start date.
- Easy-to-use date format parsing.
- Click listener to capture selected dates.

## Installation

### Step 1: Add the JitPack repository to your project

Add JitPack repository to your root `build.gradle` file:

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the dependency

Add the following dependency in your `build.gradle` file:

```
dependencies {
    // Kotlin DSL 
    implementation("com.github.mechawisdom:horizontalcalendarview:1.0.0")
    // Groovy DSL
    implementation 'com.github.mechawisdom:horizontalcalendarview:1.0.0'
}
```

## Usage

### XML Layout

First, include the `HorizontalCalendarView` in your layout XML:

```
<com.mechadev.horizontalcalendarview.HorizontalCalendarView
    android:id="@+id/horizontalCalendarView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

### Kotlin Code

Now, in your Activity or Fragment, use the following code to set up the calendar and handle date selection.

```
val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
val dateString = "04.12.2024"
val startDate: Date = try {
    dateFormat.parse(dateString) ?: throw IllegalArgumentException("Invalid date format")
} catch (e: ParseException) {
    Log.e("DATE_PARSE", "Date parsing error: ${e.message}")
    Date()
}

val horizontalCalendarView = findViewById<HorizontalCalendarView>(R.id.horizontalCalendarView)
horizontalCalendarView.setupCalendar(
    startDate = startDate,
    listener = object : OnClickCalendarListener {
        override fun clicked(date: Date) {
            // Handle date selection
            Log.d("Selected Date", date.toString())
        }
    }
)
```
