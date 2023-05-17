package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "BrandItems")
data class BrandItems (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "idBrand")
    @Expose
    @SerializedName("idBrand")
    val idBrand : String,

    @ColumnInfo(name = "strBrand")
    @Expose
    @SerializedName("strBrand")
    val strBrand : String,

    @ColumnInfo(name = "strBrandThumb")
    @Expose
    @SerializedName("strBrandThumb")
    val strBrandThumb : String
)
