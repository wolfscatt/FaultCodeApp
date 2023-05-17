package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tufar.faultcodeapp.model.converter.FaultCodeListConverter
import com.tufar.faultcodeapp.model.converter.ModelListConverter

@Entity(tableName = "FaultCode")
class FaultCode (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "faultCodeItems")
    @Expose
    @SerializedName("faultCodes")
    @TypeConverters(FaultCodeListConverter::class)
    var faultCodeItems: List<FaultCodeItems>? = null
)