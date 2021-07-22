package com.mujaffar.lavtrade.admin_module.ui.adapter

import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mujaffar.medremind.MarklCompleteListner
import com.mujaffar.medremind.R
import com.mujaffar.medremind.database.DatabaseTaskModel
import com.mujaffar.medremind.databinding.MedicineItemBinding
import com.mujaffar.medremind.databinding.VideoItemBinding
import com.mujaffar.medremind.util.constants


/**
 * RecyclerView Adapter show medicine or video schedule for date and session
 */

class ScheduleListAdapter(private val marklCompleteListner: MarklCompleteListner) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var schedules: List<DatabaseTaskModel> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType)
        {
            constants.TYPE_VIDEO->VideoViewHolder.from(parent)
            else->MedicineViewHolder.from(parent)

        }

    }


    override fun getItemViewType(position: Int): Int {
        val comparable = schedules.get(position)
        return when (comparable.type) {
            "MEDICINE" -> constants.TYPE_MEDICINE
            "VOD" -> constants.TYPE_VIDEO
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }



    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*holder.viewDataBinding.also {
            it.scheduleModel = getItem(position)
            it.markCompleteListner = marklCompleteListner
        }

        holder.bind()*/

       val databasTakModel:DatabaseTaskModel=schedules.get(position)


        when (holder) {
            is VideoViewHolder -> holder.viewDataBinding.also {
                it.scheduleModel = schedules.get(position)
                it.markCompleteListner = marklCompleteListner
                holder.bind()
            }
            is MedicineViewHolder -> holder.viewDataBinding.also {
                it.scheduleModel = schedules.get(position)
                it.markCompleteListner = marklCompleteListner
                holder.bind()
            }
        }



    }

    override fun getItemCount(): Int {
        return schedules.size
    }


}


/**
 * ViewHolder for hold medicine item
 */
class MedicineViewHolder private constructor(val viewDataBinding: MedicineItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.medicine_item

        public fun from(parent: ViewGroup): MedicineViewHolder {
            val withDataBinding: MedicineItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return MedicineViewHolder(withDataBinding)
        }


    }


    public fun bind(

    ) {

    }

}




/**
 * ViewHolder for hold medicine item
 */
class VideoViewHolder private constructor(val viewDataBinding: VideoItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.video_item

        public fun from(parent: ViewGroup): VideoViewHolder {
            val withDataBinding: VideoItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return VideoViewHolder(withDataBinding)
        }


    }


    public fun bind(

    ) {

    }

}



