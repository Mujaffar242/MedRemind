package com.mujaffar.medremind.network

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize


/*
* model class for hold  api response
* */

@Parcelize
data class ApiResponseModels(val tasks:List<Task>):Parcelable

@Parcelize
data class Task(val taskCd:String, val type:String, val frequency:Int, val duration:Int, val scheduleList:List<Schedule>):Parcelable
{
    var drug: Drug?=null
    var video: Video?=null
}


@Parcelize
data class Schedule(val session:String,val foodContext:String): Parcelable


@Parcelize
data class Drug(val brandNm:String,val genericNm:String,val qty:Int,val dosage: Dosage): Parcelable


@Parcelize
data class Dosage(val dose:Int,val unit:String): Parcelable


@Parcelize
data class Video(val title:String,val hlsUrl:String,val thumbnail:String): Parcelable