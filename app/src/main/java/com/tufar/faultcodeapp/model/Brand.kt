package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tufar.faultcodeapp.model.converter.BradListConverter

@Entity(tableName = "Brand")
data class Brand (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "brandItems")
    @Expose
    @SerializedName("brands")
    @TypeConverters(BradListConverter::class)
    var brandItems: List<BrandItems>? = null
)
