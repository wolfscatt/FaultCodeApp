package com.tufar.faultcodeapp.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tufar.faultcodeapp.model.BrandItems
import com.tufar.faultcodeapp.model.FaultCodeItems

class FaultCodeListConverter {
    @TypeConverter
    fun fromFaultCodeList(faultCode : List<FaultCodeItems>) : String?{
        if (faultCode == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<FaultCodeItems>(){}.type
            return gson.toJson(faultCode,type)
        }
    }

    @TypeConverter
    fun toFaultCodeList(faultCodeStr : String) : List<FaultCodeItems>?{
        if (faultCodeStr == null)
            return null
        else{
            val gson = Gson()
            val type = object : TypeToken<FaultCodeItems>(){}.type
            return gson.fromJson(faultCodeStr,type)
        }
    }
}