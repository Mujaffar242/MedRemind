package com.mujaffar.medremind

import com.mujaffar.medremind.database.DatabaseTaskModel


/*
* interface for handle update schedule status
* */
interface MarklCompleteListner {

    fun markComplete(taskModel: DatabaseTaskModel)


}