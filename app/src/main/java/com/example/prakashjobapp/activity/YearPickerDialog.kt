package com.example.prakashjobapp.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.prakashjobapp.R
import java.util.*

class YearPicker : DialogFragment() {
    private var listener: OnDateSetListener? = null
    fun setListener(listener: OnDateSetListener?) {
        this.listener = listener
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity(), R.style.DialogTheme_ )
        val inflater = requireActivity().layoutInflater
        val cal = Calendar.getInstance()
        val dialog: View = inflater.inflate(R.layout.activity_year_picker, null)
        val monthPicker = dialog.findViewById<View>(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById<View>(R.id.picker_year) as NumberPicker
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);
        val year = cal[Calendar.YEAR] +1
        yearPicker.minValue = 1900
        yearPicker.maxValue = 3500
        yearPicker.value = year
        builder.setView(dialog).setPositiveButton(Html.fromHtml("<font color='#FF4081'>Ok</font>")
        ) { dialog, id -> listener!!.onDateSet(null, yearPicker.value,monthPicker.value ,0) }

            .setNegativeButton(Html.fromHtml("<font color='#FF4081'>Cancel</font>")
            ) { dialog, id -> this.dialog!!.cancel() }
        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
    }
}