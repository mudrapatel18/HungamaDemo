package com.demo.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.demo.myapplication.domain.model.TabModel
import org.json.JSONObject

class AppService {


    fun getTabList(jsonObject : JSONObject) : ArrayList<TabModel> {
//        val tabListLiveData: MutableLiveData<ArrayList<TabModel>> = MutableLiveData<ArrayList<TabModel>>();
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
//        tabListLiveData.value = tabModelList

        return tabModelList
    }
}