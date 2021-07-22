package com.mujaffar.medremind.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mujaffar.medremind.R
import com.mujaffar.medremind.databinding.ActivityMainBinding
import com.mujaffar.medremind.ui.adapters.MyAdapter
import com.mujaffar.medremind.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.days

class MainActivity : AppCompatActivity() {


    //for hold data binding reference
 lateinit var binding:ActivityMainBinding;

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    var adapter:MyAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init binding
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner =this


        var mainViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.mainViewModel=mainViewModel


        supportActionBar?.hide()

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager


        tabLayout!!.addTab(tabLayout!!.newTab().setText("Morning"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Afternoon"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Evening"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Night"))

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL


       mainViewModel.date.observe(this, Observer {


           adapter=null


           var newDate=SimpleDateFormat("MM/dd/yyyy").format(it).toString()

            adapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount,
               newDate)
           viewPager!!.adapter = adapter
       })

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



    }



}