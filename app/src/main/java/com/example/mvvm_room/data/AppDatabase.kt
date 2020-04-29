package com.example.mvvm_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_room.dao.StudentDao
import com.example.mvvm_room.entity.Student

//Melalui class Database inilah kita akan memiliki akses
//ke implementasi Dao yang kita buat sebelumnya

@Database(entities = arrayOf(Student::class), version = 1)

//menentukan class entitas yang digunakan yaitu student
//dan versi database

abstract class AppDatabase : RoomDatabase() {

    //    Database Room digunakan untuk mendapatkan objek akses data,
    abstract fun studentDao(): StudentDao

    //    atau DAO, yang terkait dengan database tersebut
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {

            //  fungsi getInstance() menjamin bahwa kelas tersebut
            // dibuat instansnya .

            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
            //instance object AppDatabase dengan cara memanggil Room.databaseBuilder.
            //Masukkan student-database sebagai nama dbnya
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
