package com.njp.coroutinesdemo.network

import com.njp.coroutinesdemo.bean.BannerBean
import com.njp.coroutinesdemo.bean.ImageDataResponseBody

/**
 * @CreateDate: 2020/6/18 13:40
 * @Author: xujf
 * @Description: 类作用描述
 */
object Repository {

    //数据脱壳与错误预处理
    private fun <T> preProcessData(responseBody: ResponseBody<T>): T {
        return if (responseBody.errorCode == 0) responseBody.data else throw Throwable(responseBody.errorMsg)
    }

    suspend fun getImageData(paramter: String): ImageDataResponseBody {
        //调用ApiService定义的接口方法
        val responseBody = NetworkService.apiService.getImage()
        //返回处理后的数据
        return preProcessData(responseBody)
    }

    suspend fun getBannerData(): List<BannerBean> {
        //调用ApiService定义的接口方法
        val responseBody = NetworkService.apiService.getBanner()
        //返回处理后的数据
        return preProcessData(responseBody)
    }

}