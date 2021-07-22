/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mujaffar.medremind.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

/*
* room query for communicate with sqlite database
* */
@Dao
interface ScheduleDao {
    @Query("select * from DatabaseTaskModel where date IN(:date)  and session IN(:session)")
    fun getDataForDateAndSession(date:String,session:String): LiveData<List<DatabaseTaskModel>>


    @Query("select * from DatabaseTaskModel")
    fun getAllSession():LiveData<List<DatabaseTaskModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // fun insertAll(vararg task: DatabaseTaskModel)
    fun insertAll(list: List<DatabaseTaskModel>)


    /*
   *for update database model single row
   * use for update task complete or not
    */
    @Update
    fun update(contact: DatabaseTaskModel)


}

@Database(entities = [DatabaseTaskModel::class], version = 1,exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract val scheduleDao: ScheduleDao
}

private lateinit var INSTANCE: ScheduleDatabase

fun getDatabase(context: Context): ScheduleDatabase {
    synchronized(ScheduleDatabase::class.java)
    {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ScheduleDatabase::class.java,
                "schedule"
            ).build()
        }
    }

    return INSTANCE
}