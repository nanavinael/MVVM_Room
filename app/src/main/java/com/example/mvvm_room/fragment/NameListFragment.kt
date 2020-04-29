package com.example.mvvm_room.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mvvm_room.R
import com.example.mvvm_room.data.AppDatabase
import com.example.mvvm_room.helper.StudentRecyclerAdapter
import com.example.mvvm_room.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
//fragment yang berfungsi sebagai tempat/komponen untuk menampilkan list nama
class NameListFragment : Fragment() {

    //Fragment dapat berkomunikasi dengan parent activity menggunakan sebuah interface dan listener.
    // Buat objek untuk listener
    private var listener: OnFragmentInteractionListener? = null

    // deklarasi ViewModel
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Menghubungkan activity/fragment dengan NewStudentViewModel
         * */
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)

        /**
         * Mengambil nilai live data ke NewStudentViewModel
         * */
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")

        //Menyimpan nilai Live data ke StudentRecyclerAdapter
            rvList.adapter = StudentRecyclerAdapter(it)

        })
    }

    // Method onCreateView dipanggil saat Fragment harus menampilkan layoutnya
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Layout XML tampilan untuk fragment ini
        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }


    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau
    // bisa juga didalam onCreateView)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //list data ditampilkan dengan linear layput yaitu tampilan dari atas kebawah
        rvList.layoutManager = LinearLayoutManager(activity)

        btnAdd.setOnClickListener {
        //fungsi onclick pada button tambah data baru
            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()
            GlobalScope.launch {
                dao?.getAll()
            }
        //Intent fragment NewNameFragment
            listener?.goToNewNameFragment()
        }
    }

    //dipanggil saat sebuah fragment terhubung ke activity.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //dipanggil saat fragment tidak lagi terhubung ke sebuah activity.
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToNewNameFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
