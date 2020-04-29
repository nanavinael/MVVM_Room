package com.example.mvvm_room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//Class yang digunakan untuk deklarasi tabel pada database beserta kolom-
//kolomnya
@Entity
data class Student (

    //Id student, Primary Key , Auto Incremmet (autoGenerate)
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    //Kolom nama value inputan dari user
    @ColumnInfo var name: String = ""
)