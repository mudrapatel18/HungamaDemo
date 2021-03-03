 package com.demo.myapplication.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.domain.model.response.ErrorModel
import com.demo.myapplication.domain.model.response.ErrorStatus
import com.demo.myapplication.domain.usecase.GetDiscoverUseCase
import com.demo.myapplication.utils.Log
import org.json.JSONObject


 class DiscoverViewModel constructor(private val getTabListUseCase: GetDiscoverUseCase) : ViewModel() {
        // val tabListLiveData: MutableLiveData<ArrayList<TabModel>> = MutableLiveData<ArrayList<TabModel>>();
         val tabListLiveDataClean: MutableLiveData<ArrayList<TabModel>> = MutableLiveData<ArrayList<TabModel>>();
        val error : MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }

    init{  loadTabList()  }

    fun loadTabList(){
        Log.LOG_E("DiscoverViewModel", "loadTabList")
        getTabListUseCase.execute{
            onComplete {
                Log.LOG_E("DiscoverViewModel", "ArraySize:"+it.size)
                tabListLiveDataClean.value = it
                //insert(it)
            }

            onError { throwable ->
                Log.LOG_E("DiscoverViewModel", "loadTabList:Error")

                if(throwable.errorStatus== ErrorStatus.UNAUTHORIZED){
                   // doReshresh()
                    Log.LOG_E("DiscoverViewModel", "loadTabList:refresh")

                }else{
                    Log.LOG_E("DiscoverViewModel",  throwable.message)

                    error.value=throwable
                }

            }

            onCancel {

            }
        }
    }

    /*fun getTabList(jsonObject : JSONObject) : MutableLiveData<ArrayList<TabModel>> {
        val isSuccess = jsonObject.getString("message")
        var tabModelList : ArrayList<TabModel> = ArrayList()

        if(isSuccess.equals("Success", true)) {
            val arrayTab = jsonObject.getJSONArray("data")
            for (i in 0 until arrayTab.length()) {
                val detail = arrayTab.getJSONObject(i)
                var tabModel: TabModel =
                    TabModel(
                        detail.getInt("id"),
                        detail.getString("title")
                    )
                tabModelList.add(tabModel)
            }
        }
        tabListLiveData.value = tabModelList

        return tabListLiveData
    }*/

}
