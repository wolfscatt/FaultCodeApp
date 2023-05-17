package com.tufar.faultcodeapp.service.RetrofitClient.concrete

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    //https://raw.githubusercontent.com/wolfscatt/combi_database/master/brands.json

    companion object{
        private var retrofit : Retrofit? = null
        val BASE_URL = "https://raw.githubusercontent.com/wolfscatt/combi_database/master/"
        val retrofitInstance : Retrofit?
        get() {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}