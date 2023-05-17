package com.tufar.faultcodeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tufar.faultcodeapp.R
import com.tufar.faultcodeapp.adapter.CombiBrandAdapter
import com.tufar.faultcodeapp.adapter.CombiFaultCodeAdapter
import com.tufar.faultcodeapp.adapter.CombiModelAdapter
import com.tufar.faultcodeapp.databinding.ActivityMainBinding
import com.tufar.faultcodeapp.model.BrandItems
import com.tufar.faultcodeapp.model.FaultCodeItems
import com.tufar.faultcodeapp.model.ModelItems
import com.tufar.faultcodeapp.service.Database.FaultCodeDatabase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {
    private var arrBrandList = ArrayList<BrandItems>()
    private var arrModelList = ArrayList<ModelItems>()
    private var arrFaultCodeList = ArrayList<FaultCodeItems>()

    private val combiBrandAdapter = CombiBrandAdapter()
    private val combiModelAdapter = CombiModelAdapter()
    private val combiFaultCodeAdapter = CombiFaultCodeAdapter()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        getBrandDataFromDb()
        combiBrandAdapter.setOnClickListener(onClicked)
        combiModelAdapter.setOnClickListener(onClickedModel)

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var tempFaultArr = ArrayList<FaultCodeItems>()
                for (arrFault in arrFaultCodeList){
                    if (arrFault.strFaultCode.lowercase(Locale.getDefault()).contains(p0.toString())){
                        tempFaultArr.add(arrFault)
                    }
                }
                combiFaultCodeAdapter.setData(tempFaultArr)
                combiFaultCodeAdapter.notifyDataSetChanged()
                return true
            }

        })



    }
    private val onClicked = object : CombiBrandAdapter.OnItemClickListener{
        override fun onClicked(brandName: String) {
            try {
                getModelDataFromDb(brandName)
            }catch (e: Exception){
                Toast.makeText(this@MainActivity,"Seçtiğiniz Marka Henüz veritabanına eklenmedi",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private val onClickedModel = object : CombiModelAdapter.OnItemClickListener{
        override fun onClicked(modelName: String) {
            try {
                getFaultCodeDataFromDb(modelName)
            }catch (e : Exception){
                Toast.makeText(this@MainActivity,"Seçtiğiniz Marka Henüz veritabanına eklenmedi",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getBrandDataFromDb(){
        launch {
            this.let {
                var brand = FaultCodeDatabase.getDatabase(this@MainActivity).faultCodeDao().getAllBrand()
                arrBrandList = brand as ArrayList<BrandItems>
                arrBrandList.reverse()
                getModelDataFromDb(arrBrandList[0].strBrand)
                combiBrandAdapter.setData(arrBrandList)
                binding.rvBrand.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvBrand.adapter = combiBrandAdapter
                combiBrandAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun getFaultCodeDataFromDb(modelName : String){
        binding.tvFaultCode.text = "$modelName Fault Codes"
        launch {
            this.let {
                var model = FaultCodeDatabase.getDatabase(this@MainActivity).faultCodeDao().getSpesificFaultCodeList(modelName)
                arrFaultCodeList = model as ArrayList<FaultCodeItems>
                combiFaultCodeAdapter.setData(arrFaultCodeList)
                binding.rvFaultCode.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvFaultCode.adapter = combiFaultCodeAdapter
                combiFaultCodeAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getModelDataFromDb(brandName : String){
        binding.tvModel.text = "$brandName Models"
        launch {
            this.let {
                var model = FaultCodeDatabase.getDatabase(this@MainActivity).faultCodeDao().getSpesificModelList(brandName)
                arrModelList = model as ArrayList<ModelItems>
                arrModelList.reverse()
                getFaultCodeDataFromDb(arrModelList[0].strModel)
                combiModelAdapter.setData(arrModelList)
                binding.rvModel.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvModel.adapter = combiModelAdapter
                combiModelAdapter.notifyDataSetChanged()
            }
        }
    }
}