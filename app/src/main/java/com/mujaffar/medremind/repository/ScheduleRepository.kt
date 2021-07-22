package com.mujaffar.medremind.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.mujaffar.medremind.database.DatabaseTaskModel
import com.mujaffar.medremind.database.ScheduleDatabase
import com.mujaffar.medremind.network.ScheduleApi
import com.mujaffar.medremind.network.Task

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/*
* data source for provide data to our ui layer
* */
class ScheduleRepository(val database: ScheduleDatabase,val date:String,val session:String) {
    suspend fun getScheduleFromServer() {

        if((database.scheduleDao.getAllSession().value!= null))
        {
            return
        }
        withContext(Dispatchers.IO) {
            val response = ScheduleApi.retrofitService.getSchedule().await()
            //save list of currency with exchange rate provide by demo api on sqlite for save bandwidth
            database.scheduleDao.insertAll(convertTaskModelToDataBaseTaskModel(response.tasks))
            Log.e("","")
        }
    }



    /*
    * function that convert api response model to databse model
    * save schudule in database accoding to date
    * */
    fun convertTaskModelToDataBaseTaskModel(taskModelArrayList: List<Task>) :ArrayList<DatabaseTaskModel>{

        var scheduleArrayList: ArrayList<DatabaseTaskModel> = ArrayList()

        for (item in taskModelArrayList) {
            var initDay = Calendar.getInstance().time


            val cal = Calendar.getInstance()
            cal.time = Date()
            cal.add(Calendar.DAY_OF_YEAR, item.duration)


            var lastday = cal.time

            var initCalendar = Calendar.getInstance()

            while (initDay.before(lastday) || initDay.equals(lastday)) {


                for (schedule in item.scheduleList) {
                    var databaseTaskModel = DatabaseTaskModel(
                         SimpleDateFormat("MM/dd/yyyy").format(initDay).toString(),
                        schedule.session,
                        item.type,
                        item.video,
                        item.drug?.dosage?.dose.toString()+" "+item.drug?.dosage?.unit,
                        item.drug?.genericNm,
                        schedule.foodContext,
                        false
                    )
                    scheduleArrayList.add(databaseTaskModel)
                }



                initCalendar.add(Calendar.DAY_OF_YEAR, item.frequency)
                initDay = initCalendar.time
            }


        }

        return scheduleArrayList
    }



    /*
  * get schedule for date and sesssion (morining, evening, night  ,afternnon etc)
  *  */
    val schedules: LiveData<List<DatabaseTaskModel>> = database.scheduleDao.getDataForDateAndSession(date,session)



    /*
* get list of all schudle that are store in database
*  */
    val allSchedules: LiveData<List<DatabaseTaskModel>> = database.scheduleDao.getAllSession()



/*
* function for update task complete status
* */
    suspend fun updateCompleteStatus(databaseTaskModel: DatabaseTaskModel)
    {
        withContext(Dispatchers.IO){
        databaseTaskModel.isCompleted = true
        database.scheduleDao.update(databaseTaskModel)
    }

    }
}