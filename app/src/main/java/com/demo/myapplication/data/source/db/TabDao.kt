package com.demo.myapplication.data.source.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.demo.myapplication.domain.model.TabModel


@Dao
interface TabDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTab(tab: TabModel): Long

    @Delete
   suspend fun deleteTab(tab: TabModel): Int

    @Query("SELECT * from tab_model")
    fun getTabList(): LiveData<List<TabModel>>


}