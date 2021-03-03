package com.demo.myapplication.domain.respository

import com.demo.myapplication.domain.model.TabModel
import io.reactivex.Single

/**
 * To make an interaction between [TabRepositoryImp] & [GetTabUseCase]
 * */
interface DiscoverRepository {

    suspend fun getTabList() : MutableList<TabModel>
}