package com.mujaffar.medremind.database


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mujaffar.medremind.network.Dosage
import com.mujaffar.medremind.network.Drug
import com.mujaffar.medremind.network.Task
import com.mujaffar.medremind.network.Video
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList


/*
* for save day by day schedule by date
* */
@Entity
data class DatabaseTaskModel constructor(

    val date: String,
    val session: String,
    val type: String,
    @Embedded
    val video: Video?,
    val douse:String?,
    val genericName:String?,
    val foodContext: String,
    var isCompleted:Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int= 0
}


