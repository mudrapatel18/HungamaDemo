package com.demo.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.myapplication.model.TabModel
import org.json.JSONObject


class DiscoverViewModel(application: Application) : AndroidViewModel(application) {
    private val tabListLiveData: MutableLiveData<ArrayList<TabModel>> = MutableLiveData<ArrayList<TabModel>>();

    init{    }

    fun getTabList(jsonObject : JSONObject) : MutableLiveData<ArrayList<TabModel>> {
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
    }

}
