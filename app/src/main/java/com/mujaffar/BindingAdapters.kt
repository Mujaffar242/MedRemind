package com.mujaffar

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mujaffar.medremind.database.DatabaseTaskModel
import com.mujaffar.medremind.viewmodels.MainViewModel
import java.text.SimpleDateFormat


@BindingAdapter("setQuantity")
fun TextView.setQuantity(data: DatabaseTaskModel) {
    setText(data.douse)
}


@BindingAdapter("hideShowFoodContext")
fun TextView.hideShowBasedOnComplete(data: DatabaseTaskModel) {
    if (data.isCompleted) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
    }
}

@BindingAdapter("showOnCompleted")
fun ImageView.showOnCompleted(data: DatabaseTaskModel) {
    if (data.isCompleted) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

@BindingAdapter("setImageFromNetwork")
fun ImageView.setImageFromUrl(data: DatabaseTaskModel) {
    Glide.with(this.context)
        .load(data.video?.thumbnail)
        .into(this)
}


@BindingAdapter("setFormattedMonthOnTextView")
fun TextView.setFormattedMonthOnTextView(mainViewModel: MainViewModel) {

    var spf = SimpleDateFormat("MMM");
    setText(spf.format(mainViewModel.date.value))
}


@BindingAdapter("setFormatedDayhOnTextView")
fun TextView.setFormatedDayhOnTextView(mainViewModel: MainViewModel) {
    var spf = SimpleDateFormat("dd");
    setText(spf.format(mainViewModel.date.value))
}


