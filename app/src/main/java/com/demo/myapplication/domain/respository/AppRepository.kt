package com.demo.myapplication.domain.respository

import androidx.lifecycle.MutableLiveData
import com.demo.myapplication.domain.model.TabModel
import io.reactivex.Single
import org.json.JSONObject

/**
 * To make an interaction between [TabRepositoryImp] & [GetTabUseCase]
 * */
interface AppRepository {

    suspend fun getTabList(jsonObject: JSONObject) : ArrayList<TabModel>
}