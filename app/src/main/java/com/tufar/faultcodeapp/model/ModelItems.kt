package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ModelItems")
data class ModelItems (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "idModel")
    @Expose
    @SerializedName("idModel")
    val idModel: String,

    @ColumnInfo(name = "strModel")
    @Expose
    @SerializedName("strModel")
    val strModel: String,

    @ColumnInfo(name = "brandName")
    @Expose
    @SerializedName("brandName")
    val brandName: String,

)