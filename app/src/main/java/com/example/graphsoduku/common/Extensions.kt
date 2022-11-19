package com.example.graphsoduku.common

import android.app.Activity
import android.widget.Toast

// Extensions follow OCP
// software entities should be open for extensions but closed for modification
// enables us to add new functionality to the original source code without modifying the original source code


// internal code is good for visibility that needs to be accessed from different packages
// but not from external packages or modules


// here we can use this any activity to show toast
internal fun Activity.makeToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}

internal fun Long.toTime(): String {
    if (this >= 3600) return "+59:59"
    var minutes = ((this % 3600) / 60).toString()
    if (minutes.length == 1) minutes = "0$minutes"
    var seconds = (this % 60).toString()
    if (seconds.length == 1) seconds = "0$seconds"
    return String.format("$minutes:$seconds")
}