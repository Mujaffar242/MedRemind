package com.mujaffar.medremind.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.room.Index
import com.mujaffar.medremind.database.DatabaseTaskModel
import com.mujaffar.medremind.database.ScheduleDao
import com.mujaffar.medremind.database.getDatabase
import com.mujaffar.medremind.repository.ScheduleRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScheduleviewModel(context: Context,val date:String,val session:String) : ViewModel()
{



    //corotiune job
    private var currentJob: Job? = null

    val database= getDatabase(context)

    val scheduleRepository=ScheduleRepository(database,date,session)

    //for hold currency list return by repository
    var  scheduleList=scheduleRepository.schedules

    //
    var allData=scheduleRepository.allSchedules



    //for show loading spinner
    var showLoadingProgressBar=MutableLiveData<Boolean>()

    /*
    * get list of contacts and save into room databse when app load first time
    * */
    init {
        showLoadingProgressBar.value=false
    }


    /*
    * funcation for update single contact row on room database
    * */
     fun changeCompleteStatus(taskDatabaseTaskModel: DatabaseTaskModel)
    {
        viewModelScope.launch {
            scheduleRepository.updateCompleteStatus(taskDatabaseTaskModel)
        }
    }





    /*
    * for show loading spinner
    * */
    fun showLoadingSpinner()
    {
        showLoadingProgressBar.value=true;
    }


    /*
   * for hide loading spinner
   * */
    fun hideLoadingSpinner()
    {
        showLoadingProgressBar.value=false;
    }

}