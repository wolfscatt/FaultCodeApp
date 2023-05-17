package com.tufar.faultcodeapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.tufar.faultcodeapp.databinding.ActivitySplashBinding
import com.tufar.faultcodeapp.model.*
import com.tufar.faultcodeapp.service.Database.FaultCodeDatabase
import com.tufar.faultcodeapp.service.RetrofitClient.abstract.GetDataService
import com.tufar.faultcodeapp.service.RetrofitClient.concrete.RetrofitClientInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clearDatabase()
        getBrandFromAPI()


        binding.btnGetStart.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun getBrandFromAPI(){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getBrandList()
        call.enqueue(object : Callback<Brand>{
            override fun onResponse(call: Call<Brand>, response: Response<Brand>) {
                for (arr in response.body()!!.brandItems!!){
                    getModelsFromAPI(arr.strBrand)
                }
                insertBrandIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Brand>, t: Throwable) {
                Toast.makeText(this@SplashActivity,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getModelsFromAPI(brandName : String){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getModelList()
        var modelList = arrayListOf<ModelItems>()
        call.enqueue(object: Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                for (arr in response.body()!!.modelItems!!){
                    if(arr.brandName == brandName){
                        getFaultCodeFromAPI(arr.strModel)
                        modelList.add(arr)
                    }
                }
                insertModelIntoRoomDb(brandName,modelList)
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Toast.makeText(this@SplashActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getFaultCodeFromAPI(modelName : String){
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getFaultCodeList()
        var faultCodeList = arrayListOf<FaultCodeItems>()
        call.enqueue(object : Callback<FaultCode>{
            override fun onResponse(call: Call<FaultCode>, response: Response<FaultCode>) {
                for (arr in response.body()!!.faultCodeItems!!){
                    if (arr.modelName == modelName){
                        faultCodeList.add(arr)
                    }
                }
                insertFaultCodeIntoRoomDb(modelName,faultCodeList)
            }

            override fun onFailure(call: Call<FaultCode>, t: Throwable) {
                Toast.makeText(this@SplashActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun insertModelIntoRoomDb(brandName : String, modelItems: List<ModelItems>?){
        launch{
            this.let {
                for (arr in modelItems!!){
                    var modelItem = ModelItems(arr.id,arr.idModel,arr.strModel,brandName)
                    FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().insertModel(arr)
                }
            }
        }
    }
    private fun insertFaultCodeIntoRoomDb(modelName : String, faultCodes: List<FaultCodeItems>?){
        launch {
            this.let {
                for (arr in faultCodes!!){
                    var faultCodeItem = FaultCodeItems(arr.id, arr.idFaultCode, arr.strFaultCode, arr.description, modelName)
                    FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().insertFaultCode(arr)
                }
            }
        }
    }
    private fun insertBrandIntoRoomDb(brand: Brand?){
        launch {
            this.let {
                for (arr in brand!!.brandItems!!){
                    FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().insertBrand(arr)
                }
            }
        }
    }

    private fun clearDatabase(){
        launch{
            this.let {
                FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().clearBrandDb()
                FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().clearModelDb()
                FaultCodeDatabase.getDatabase(this@SplashActivity).faultCodeDao().clearFaultCodeDb()

            }
        }
    }
}