package com.njp.coroutinesdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.njp.coroutinesdemo.bean.LoadState
import com.njp.coroutinesdemo.launch
import com.njp.coroutinesdemo.network.NetworkService
import com.njp.coroutinesdemo.network.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val imageData = MutableLiveData<List<String>>()
    val loadState = MutableLiveData<LoadState>()

    fun getData() {
        launch(
            {
                loadState.value = LoadState.Loading()
                val data1 = async {
                    delay(3000)
                    Repository.getBannerData()
                }
                val data2 = async { Repository.getBannerData() }
                val data3 = async { Repository.getBannerData() }
                imageData.value = listOf(data1.await(), data2.await(), data3.await()).map {
                    var url = ""
                    url = it[0].imagePath
                    url
                }
                loadState.value = LoadState.Success()
            },
            {
                loadState.value = LoadState.Fail(it.message ?: "加载失败")
            },
            {
                //结束
            }
        )
    }

}