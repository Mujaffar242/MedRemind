package com.mujaffar.medremind.work
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mujaffar.medremind.database.getDatabase
import com.mujaffar.medremind.repository.ScheduleRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = ScheduleRepository(database,"","")
        return try {
            //get data from exchnage api
           repository.getScheduleFromServer()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
}
