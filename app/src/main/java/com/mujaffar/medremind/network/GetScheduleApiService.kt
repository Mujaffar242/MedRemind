package com.mujaffar.medremind.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred

/*
* use retofit library  for networking request
* */

private const val BASE_URL = "https://38rhabtq01.execute-api.ap-south-1.amazonaws.com/"

interface GetScheduleApiService {
    @GET("dev/schedule")
    fun getSchedule()
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            :Deferred<ApiResponseModels>
}

/*
* moshi library for remove callback methods of retrofit
* */
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()


object ScheduleApi {
    val retrofitService : GetScheduleApiService by lazy { retrofit.create(GetScheduleApiService::class.java) }
}



