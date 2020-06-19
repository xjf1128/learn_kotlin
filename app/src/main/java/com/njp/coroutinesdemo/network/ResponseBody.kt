package com.njp.coroutinesdemo.network

/**
 * @CreateDate: 2020/6/18 13:39
 * @Author: xujf
 * @Description: 类作用描述
 */
data class ResponseBody<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)