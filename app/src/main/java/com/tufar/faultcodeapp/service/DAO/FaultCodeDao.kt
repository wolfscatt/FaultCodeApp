package com.tufar.faultcodeapp.service.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tufar.faultcodeapp.model.BrandItems
import com.tufar.faultcodeapp.model.FaultCodeItems
import com.tufar.faultcodeapp.model.ModelItems

@Dao
interface FaultCodeDao {

    @Query("Select * from BrandItems Order by id Desc")
    suspend fun getAllBrand() : List<BrandItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrand(brandItems: BrandItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModel(modelItems: ModelItems)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaultCode(FaultCodeItems: FaultCodeItems)

    @Query("Delete from BrandItems")
    suspend fun clearBrandDb()

    @Query("Delete from ModelItems")
    suspend fun clearModelDb()

    @Query("Delete from FaultCodeItems")
    suspend fun clearFaultCodeDb()

    @Query("Select * from ModelItems Where brandName = :brandName Order by id Desc")
    suspend fun getSpesificModelList(brandName : String) : List<ModelItems>

    @Query("Select * from FaultCodeItems Where  modelName = :modelName Order by id Desc")
    suspend fun getSpesificFaultCodeList(modelName : String) : List<FaultCodeItems>
}