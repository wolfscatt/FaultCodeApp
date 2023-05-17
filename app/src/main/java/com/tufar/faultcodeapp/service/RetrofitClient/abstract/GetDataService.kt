package com.tufar.faultcodeapp.service.RetrofitClient.abstract

import com.tufar.faultcodeapp.model.Brand
import com.tufar.faultcodeapp.model.FaultCode
import com.tufar.faultcodeapp.model.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {

    @GET("brands.json")
    fun getBrandList() : Call<Brand>

    @GET("models.json")
    fun getModelList() : Call<Model>

    @GET("faultCode.json")
    fun getFaultCodeList() : Call<FaultCode>
}