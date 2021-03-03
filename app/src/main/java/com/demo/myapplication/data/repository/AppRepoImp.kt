package com.demo.myapplication.data

import com.demo.myapplication.domain.model.TabModel
import io.reactivex.Single

class AppRepoImp

    (private val cloudRepository: BaseCloudRepository, private val foodDao: FoodDao) : AppRepository {
        override suspend fun selectAllFoods(): MutableList<ArrayList<TabModel> {

            return foodDao.selectAllFoods()

        }



    }
}