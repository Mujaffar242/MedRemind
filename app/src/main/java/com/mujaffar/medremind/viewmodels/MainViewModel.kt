package com.mujaffar.medremind.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel:ViewModel() {

    var calendar=Calendar.getInstance()


    var date=MutableLiveData<Date>()

    var session=MutableLiveData<String>()

    var month =Transformations.map(date) { SimpleDateFormat("MMM").format(date.value) }

    var day =Transformations.map(date) { SimpleDateFormat("dd").format(date.value) }



    init {
        date.value=calendar.time
    }


    fun onNextClick()
    {
        calendar.add(Calendar.DAY_OF_MONTH,1)
        date.value=calendar.time
    }

    fun onPreviousClick()
    {
        if(calendar.time.after(Date()))
        {
            calendar.add(Calendar.DAY_OF_MONTH,-1)
            date.value=calendar.time
        }
    }

}