package com.mujaffar.medremind.ui.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mujaffar.medremind.ui.view.ScheduleListFragment

class MyAdapter constructor(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int,val date:String) : FragmentStatePagerAdapter(fm) {



    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                val scheduleListFragment=ScheduleListFragment()
                val bundle=Bundle()

                bundle.putString("session","MORNING")
                bundle.putString("date",date)


                return scheduleListFragment.also { it.arguments=bundle }
            }
            1 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                val scheduleListFragment=ScheduleListFragment()
                val bundle=Bundle()

                bundle.putString("session","AFTERNOON")
                bundle.putString("date",date)


                return scheduleListFragment.also { it.arguments=bundle }
            }
            2 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                val scheduleListFragment=ScheduleListFragment()
                val bundle=Bundle()

                bundle.putString("session","EVENING")
                bundle.putString("date",date)


                return scheduleListFragment.also { it.arguments=bundle }
            }
            else->{
                //  val homeFragment: HomeFragment = HomeFragment()
                val scheduleListFragment=ScheduleListFragment()
                val bundle=Bundle()

                bundle.putString("session","NIGHT")
                bundle.putString("date",date)


                return scheduleListFragment.also { it.arguments=bundle }
            }
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
