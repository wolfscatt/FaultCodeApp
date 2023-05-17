package com.tufar.faultcodeapp.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tufar.faultcodeapp.model.BrandItems

class BradListConverter {
    @TypeConverter
    fun fromBrandList(brand : List<BrandItems>) : String?{
        if (brand == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<BrandItems>(){}.type
            return gson.toJson(brand,type)
        }
    }

    @TypeConverter
    fun toBrandList(brandStr : String) : List<BrandItems>?{
        if (brandStr == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<BrandItems>(){}.type
            return gson.fromJson(brandStr,type)
        }
    }
}