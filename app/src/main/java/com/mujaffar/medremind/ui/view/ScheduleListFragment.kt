package com.mujaffar.medremind.ui.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mujaffar.medremind.MarklCompleteListner
import com.mujaffar.medremind.database.DatabaseTaskModel
import com.mujaffar.medremind.databinding.FragmentScheduleListBinding
import com.mujaffar.medremind.viewmodels.ScheduleViewModelFactory
import com.mujaffar.medremind.viewmodels.ScheduleviewModel
import com.mujaffar.lavtrade.admin_module.ui.adapter.ScheduleListAdapter


class ScheduleListFragment : Fragment(), MarklCompleteListner {




    //for hold data binding reference
    lateinit var binding: FragmentScheduleListBinding;


    /**
     * RecyclerView Adapter
     */
    private var viewModelAdapter: ScheduleListAdapter? = null

    //for hold currency viewmodel
    lateinit var viewModel: ScheduleviewModel


        //variable for hold list type
        lateinit var date: String

        //variable for hold list type
        lateinit var session: String



    //variable for hold progress dialog
    lateinit var progressDialog: ProgressDialog




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        date=arguments?.getString("date").toString()
        session=arguments?.getString("session").toString()

        //init progress dialog
        progressDialog = ProgressDialog(activity)


        //init binding
        binding = FragmentScheduleListBinding.inflate(layoutInflater)





        //init viewmodel
        viewModelAdapter = ScheduleListAdapter(this)

        //set adapter to recylerview
        binding.contactListRecyclerView.adapter = viewModelAdapter


        val viewModelFactory= context?.let { ScheduleViewModelFactory(it,date, session.toUpperCase()) }

        viewModel=ViewModelProviders.of(this,viewModelFactory).get(ScheduleviewModel::class.java)


        binding.lifecycleOwner=this
        //  viewModel= ViewModelProviders.of(this).get(ScheduleviewModel::class.java)

        binding.scheduleviewModel=viewModel


        //observe schedule live data based on different page type and add to adapter
        //if page type is contactlist
        viewModel.scheduleList?.observe(this, Observer { schudule ->
            schudule?.apply {
                //viewModelAdapter?.contacts = contact as List<DatabaseContactModel>

                viewModelAdapter?.schedules=schudule


            }
        })




        viewModel.allData?.observe(this, Observer { schudule ->
            schudule?.apply {
                //viewModelAdapter?.contacts = contact as List<DatabaseContactModel>


                 //if contact list is empty mean fetching contacts show loading spinner
                 if (schudule.isEmpty())
                     viewModel.showLoadingSpinner()
                 //otherwise hide the loading spinner
                 else
                     viewModel.hideLoadingSpinner()

            }
        })




        /*
        * observer the showLoadingProgressBar and based on this show loading spinner
        * */
        viewModel.showLoadingProgressBar.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })


        return binding.root
    }





    /*
    * funcation for show no data text centre of screen if list is empaty
    * */
    private fun hideShowNODataBasedOnList(contact: List<DatabaseTaskModel>) {
        if (contact.isEmpty()) {
            binding.noDataText.visibility = View.VISIBLE
        } else {
            binding.noDataText.visibility = View.GONE
        }
    }



    /*
    * for mark complete task
    * */
    override fun markComplete(taskModel: DatabaseTaskModel) {
        viewModel.changeCompleteStatus(taskModel)

        if(taskModel.type.equals("VOD"))
        {
            val intent = Intent(activity, PlayVideoActivity::class.java)
// To pass any data to next activity
            intent.putExtra("hlsUrl", taskModel.video?.hlsUrl)
// start your next activity
            startActivity(intent)
        }
    }


}