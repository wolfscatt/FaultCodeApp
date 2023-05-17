package com.tufar.faultcodeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tufar.faultcodeapp.model.converter.ModelListConverter

@Entity(tableName = "Model")
class Model (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "modelItems")
    @Expose
    @SerializedName("models")
    @TypeConverters(ModelListConverter::class)
    var modelItems: List<ModelItems>? = null
)