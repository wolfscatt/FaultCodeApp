package com.tufar.faultcodeapp.service.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tufar.faultcodeapp.model.*
import com.tufar.faultcodeapp.model.converter.BradListConverter
import com.tufar.faultcodeapp.model.converter.FaultCodeListConverter
import com.tufar.faultcodeapp.model.converter.ModelListConverter
import com.tufar.faultcodeapp.service.DAO.FaultCodeDao

@Database(entities = [Brand::class, BrandItems::class, Model::class, ModelItems::class, FaultCode::class, FaultCodeItems::class], version = 1, exportSchema = false)
@TypeConverters(BradListConverter::class, ModelListConverter::class, FaultCodeListConverter::class)
abstract class FaultCodeDatabase : RoomDatabase(){

    companion object{

        @Volatile   // volatile annotation' ı oluşturduğumuz instance' ın bütün threadler de görünür olmasını sağlıyor.
        private var faultCodeDatabase : FaultCodeDatabase? = null

        @Synchronized   // synchronized ise aynı anda farklı 2 thread bu metoda ulaşamıyor biri bitip sonra diğeri başlıyor.
        fun getDatabase(context: Context): FaultCodeDatabase{
            if (faultCodeDatabase == null){
                faultCodeDatabase = Room.databaseBuilder(context.applicationContext,FaultCodeDatabase::class.java,"FaultCode.db").build()
            }
            return faultCodeDatabase!!
        }
    }

    abstract fun faultCodeDao() : FaultCodeDao
}