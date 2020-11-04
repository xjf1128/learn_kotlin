package com.njp.coroutinesdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.njp.coroutinesdemo.R
import com.njp.coroutinesdemo.bean.LoadState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //获取ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //对加载状态进行动态观察
        viewModel.loadState.observe(this, Observer {
            when (it) {
                is LoadState.Success -> button.isEnabled = true
                is LoadState.Fail -> {
                    button.isEnabled = true
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }
                is LoadState.Loading -> {
                    button.isEnabled = false
                }
            }

        })

        //对图片Url数据进行观察
        viewModel.imageData.observe(this, Observer {
            //用Glide加载三张图片
            Glide.with(this)
                .load(it[0])
                .into(imageView1)
            Glide.with(this)
                .load(it[1])
                .into(imageView2)
            Glide.with(this)
                .load(it[2])
                .into(imageView3)
        })

        //点击刷新按钮来网络加载
        button.setOnClickListener {
//            viewModel.getData()
            testCoroutines()
        }
    }

    private fun testCoroutines() {
        lifecycleScope.launchWhenResumed {
            val result1 = async(Dispatchers.Default) {
                add(1)
            }

            val result2 = async(Dispatchers.Default) {
                add(2)
            }

            val i = result1.await() + result2.await();
            Log.e("Flow","result1 + result2 = $i")

            flow {
                for(i in 1..3) {
                    Log.e("Flow","$i emit")
                    emit(i)
                }
            }.filter {
                Log.e("Flow","$it filter")
                it % 2 != 0
            }.map<Int,String> {
                Log.e("Flow","$it map")
                "${it * it} money"
            }.collect {
                Log.e("Flow","i get $it")
            }

//            val flow = (1..10).asFlow().flowOn(Dispatchers.IO)
//
//            flow.collect { num ->
//                Log.e("ktx", "collect value = $num")
//            }
        }
    }

    private suspend fun add(i: Int): Int {
//        delay( i*100L)
        var i = 0
        while (i<Int.MAX_VALUE) {
            i++
        }
        return i
    }
}
