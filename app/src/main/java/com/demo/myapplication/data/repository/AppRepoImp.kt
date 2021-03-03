package com.demo.myapplication.data.repository

import com.demo.myapplication.domain.model.TabModel
import com.demo.myapplication.domain.respository.AppRepository
import org.json.JSONObject

class AppRepoImp () : AppRepository {
    override suspend fun getTabList(jsonObject: JSONObject): ArrayList<TabModel> {
        var appService = AppService()
        return appService.getTabList(jsonObject);

    }


   /* override suspend fun saveFoods(foodDto: FoodDto): Long {
        if (foodDto.results.size > 0) {
            for (food in foodDto.results) {
                foodDao.insertFood(food)
            }

        }
        return 0L
    }

    override suspend fun getHome(): FoodDto {
        return cloudRepository
            .getHome()
    }*/


}