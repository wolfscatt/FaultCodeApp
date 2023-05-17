package com.tufar.faultcodeapp.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tufar.faultcodeapp.model.BrandItems
import com.tufar.faultcodeapp.model.ModelItems

class ModelListConverter {
    @TypeConverter
    fun fromModelList(model : List<ModelItems>) : String?{
        if (model == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<ModelItems>(){}.type
            return gson.toJson(model,type)
        }
    }

    @TypeConverter
    fun toModelList(modelStr : String) : List<ModelItems>?{
        if (modelStr == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<ModelItems>(){}.type
            return gson.fromJson(modelStr,type)
        }
    }
}