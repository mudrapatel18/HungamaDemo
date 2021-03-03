package com.demo.myapplication.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.domain.respository.AppRepository
import com.demo.myapplication.domain.usecase.base.UseCase
import com.demo.myapplication.presentation.view.fragment.DiscoverFragment
import org.json.JSONObject

class GetDiscoverUseCase(private val jsonObject: JSONObject,
                         private val appRepository: AppRepository
) : UseCase<ArrayList<TabModel>>() {
    override suspend fun executeOnBackground(): ArrayList<TabModel> {
        return appRepository.getTabList(jsonObject)
    }
}