package com.demo.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tab_model")
class TabModel
    (
    @PrimaryKey
    var id:Int,
    var title : String
)