package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDateButton: Button = findViewById(R.id.selectDateButton)
        selectDateButton.setOnClickListener {
            dateSelection()
        }
    }

    private fun dateSelection() {

        val cal = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val dayOfMonth: Int = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val dateSelectedString = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                val selectedDateTextView = findViewById<TextView>(R.id.selectedDateTextView)
                selectedDateTextView.text = dateSelectedString

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDate = sdf.parse(dateSelectedString)
                selectedDate?.let {
                    val selectedDateInMinutes = selectedDate.time / 60_000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60_000

                        val inMinutesTextView = findViewById<TextView>(R.id.inMinutesTextView)
                        inMinutesTextView.text = (currentDateInMinutes - selectedDateInMinutes).toString()
                    }
                }
            },
            year, month, dayOfMonth)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86_400_400
        datePickerDialog.show()
    }
}