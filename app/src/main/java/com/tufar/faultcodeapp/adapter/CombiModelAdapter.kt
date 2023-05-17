package com.tufar.faultcodeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tufar.faultcodeapp.databinding.CombiModelRecyclerRowBinding
import com.tufar.faultcodeapp.model.ModelItems

class CombiModelAdapter : RecyclerView.Adapter<CombiModelAdapter.ModelViewHolder>() {
    class ModelViewHolder(val binding: CombiModelRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    var listener : OnItemClickListener? = null
    var arrModelList = ArrayList<ModelItems>()
    var ctx : Context? = null

    fun setData(arrData : List<ModelItems>){
        arrModelList = arrData as ArrayList<ModelItems>
    }
    fun setOnClickListener(listener1 : OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        var binding = CombiModelRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        ctx = parent.context
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.binding.textViewModelName.text = arrModelList[position].strModel
        // buraya da görsel eklenecek
        holder.binding.root.setOnClickListener {
            listener!!.onClicked(arrModelList[position].strModel)
        }
    }

    override fun getItemCount(): Int {
        return arrModelList.size
    }
    interface OnItemClickListener{
        fun onClicked(modelName : String)
    }
}