package com.demo.myapplication.model

class BucketModel
    (
    var id:Int,
    var title : String,
    var subTitle : String,
    var showMore : Boolean,
    var type : Int,
    var contentList : ArrayList<BucketContentModel>
)