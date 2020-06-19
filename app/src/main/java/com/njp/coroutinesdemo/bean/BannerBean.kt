package com.njp.coroutinesdemo.bean

/**
 * @CreateDate: 2020/6/19 15:03
 * @Author: xujf
 * @Description: 类作用描述
 */
data class BannerBean (
    val desc: String,
    val id: Int = 0,
    val imagePath: String,
    val isVisible: Int = 0,
    val order: Int = 0,
    val title: String,
    val type: Int = 0,
    val url: String
)