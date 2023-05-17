package com.tufar.faultcodeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tufar.faultcodeapp.databinding.ActivityMainBinding
import com.tufar.faultcodeapp.databinding.CombiBrandRecyclerRowBinding
import com.tufar.faultcodeapp.model.BrandItems

class CombiBrandAdapter : RecyclerView.Adapter<CombiBrandAdapter.BrandViewHolder>(){
    class BrandViewHolder(val binding: CombiBrandRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
    }

    var listener : OnItemClickListener? = null
    var arrBrandList = ArrayList<BrandItems>()
    var ctx : Context? = null

    fun setData(arrData : List<BrandItems>){
        arrBrandList = arrData as ArrayList<BrandItems>
    }
    fun setOnClickListener(listener1 : OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        var binding = CombiBrandRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        ctx = parent.context
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.binding.textViewCombiName.text = arrBrandList[position].strBrand
        // resimleri getirmek için kod yazılacak
        Glide.with(ctx!!).load(arrBrandList[position].strBrandThumb).into(holder.binding.imgCombi)

        holder.binding.root.setOnClickListener {
            listener!!.onClicked(arrBrandList[position].strBrand)
        }
    }

    override fun getItemCount(): Int {
        return arrBrandList.size
    }

    interface OnItemClickListener{
        fun onClicked(brandName : String)
    }
}