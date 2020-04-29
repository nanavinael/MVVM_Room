package com.example.mvvm_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm_room.fragment.NameListFragment
import com.example.mvvm_room.fragment.NewNameFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(),
    NewNameFragment.OnFragmentInteractionListener,
    NameListFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menggunakan savedInstanceState untuk menjaga data
        if (savedInstanceState == null) {
            goToStudentListFragment()
        }

        Timber.plant(Timber.DebugTree())
    }

    override fun goToStudentListFragment() {

            // Kelas FragmentManager dan kelas FragmentTransaction memungkinkan kita untuk menambah,
            // menghapus dan menimpa fragment yang ada di layout saat activity sedang aktif.

        val manager = supportFragmentManager

        // Memulai transaksi
        val transaction = manager.beginTransaction()

        // mengganti isi container dengan fragment baru
        transaction.replace(R.id.flContent, NameListFragment.newInstance())

        //transaksi dimulai
        transaction.commit()
    }

    override fun goToNewNameFragment() {
        val manager = supportFragmentManager

        // Memulai transaksi
        val transaction = manager.beginTransaction()

        // mengganti isi container dengan fragment baru
        transaction.replace(R.id.flContent, NewNameFragment.newInstance())

        //transaksi dimulai
        transaction.commit()
    }
}

