package com.demo.myapplication.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.myapplication.model.BucketContentModel
import com.demo.myapplication.model.BucketModel
import com.demo.myapplication.model.StoryModel
import com.demo.myapplication.model.TabModel
import org.json.JSONObject


class PagerViewModel(application: Application) : AndroidViewModel(application) {
    private val storyListLiveData: MutableLiveData<ArrayList<StoryModel>> = MutableLiveData<ArrayList<StoryModel>>();
    private val bucketListLiveData: MutableLiveData<ArrayList<BucketModel>> = MutableLiveData<ArrayList<BucketModel>>();

    init{    }

    fun getStoryList(jsonObject : JSONObject) : MutableLiveData<ArrayList<StoryModel>> {
        val isSuccess = jsonObject.getString("message")
        var storyModelList : ArrayList<StoryModel> = ArrayList()

        if(isSuccess.equals("Success", true)) {
            val arrayStory = jsonObject.getJSONArray("data")
            for (i in 0 until arrayStory.length()) {
                val detail = arrayStory.getJSONObject(i)
                var storyModel: StoryModel =
                    StoryModel(
                        detail.getInt("id"),
                        detail.getString("title"),
                        detail.getString("img")

                    )
                storyModelList.add(storyModel)
            }
        }
        storyListLiveData.value = storyModelList

        return storyListLiveData
    }

    fun getBucketList(jsonObject : JSONObject) : MutableLiveData<ArrayList<BucketModel>> {
        val isSuccess = jsonObject.getString("message")
        var bucketModelList : ArrayList<BucketModel> = ArrayList()

        if(isSuccess.equals("Success", true)) {
            Log.e("Result", isSuccess +":IF")
            val arrayBucket = jsonObject.getJSONArray("data")
            for (i in 0 until arrayBucket.length()) {
                val detail = arrayBucket.getJSONObject(i)

                var listContentModel : ArrayList<BucketContentModel> = ArrayList()

                for(j in 0 until detail.getJSONArray("contents").length()) {
                    val content = detail.getJSONArray("contents").getJSONObject(j)

                    var contentModel: BucketContentModel = BucketContentModel(content.getInt("id"),
                            content.getString("title"), content.getString("subtitle"), content.getString("image"))

                    listContentModel.add(contentModel)
                }
                var bucketModel =
                    BucketModel(
                        detail.getInt("id"),
                        detail.getString("title"),
                        detail.getString("subtitle"),
                        detail.getBoolean("showmore"),
                        detail.getInt("type"),
                        listContentModel
                    )
                bucketModelList.add(bucketModel)
            }
        }
        bucketListLiveData.value = bucketModelList

        return bucketListLiveData
    }

}
