package com.njp.coroutinesdemo.network

import com.njp.coroutinesdemo.bean.BannerBean
import com.njp.coroutinesdemo.bean.ImageDataResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("banner/json")
    suspend fun getImage(@Query("type") type: String = "json"): ResponseBody<ImageDataResponseBody>

    @GET("banner/json")
    suspend fun getBanner(): ResponseBody<List<BannerBean>>
}