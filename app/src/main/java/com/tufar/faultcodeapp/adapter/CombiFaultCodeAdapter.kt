package com.tufar.faultcodeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tufar.faultcodeapp.databinding.CombiFaultRecyclerRowBinding
import com.tufar.faultcodeapp.databinding.CombiModelRecyclerRowBinding
import com.tufar.faultcodeapp.model.FaultCodeItems
import com.tufar.faultcodeapp.model.ModelItems

class CombiFaultCodeAdapter : RecyclerView.Adapter<CombiFaultCodeAdapter.FaultCodeViewHolder>() {
    class FaultCodeViewHolder(val binding: CombiFaultRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
    }

    var arrFaultCodeList = ArrayList<FaultCodeItems>()
    var ctx : Context? = null

    fun setData(arrData : List<FaultCodeItems>){
        arrFaultCodeList = arrData as ArrayList<FaultCodeItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaultCodeViewHolder {
        var binding = CombiFaultRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        ctx = parent.context
        return FaultCodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaultCodeViewHolder, position: Int) {
        holder.binding.textViewFaultCode.text = arrFaultCodeList[position].strFaultCode
        holder.binding.textViewDescription.text = arrFaultCodeList[position].description
    }

    override fun getItemCount(): Int {
        return arrFaultCodeList.size
    }

}