package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FaultCodeItems")
data class FaultCodeItems (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "idFaultCode")
    @Expose
    @SerializedName("idFaultCode")
    val idFaultCode: String,

    @ColumnInfo(name = "strFaultCode")
    @Expose
    @SerializedName("strFaultCode")
    val strFaultCode: String,

    @ColumnInfo(name = "description")
    @Expose
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "modelName")
    @Expose
    @SerializedName("modelName")
    val modelName: String,
)