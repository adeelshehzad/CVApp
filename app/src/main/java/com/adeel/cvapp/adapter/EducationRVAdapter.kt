package com.adeel.cvapp.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adeel.cvapp.R
import com.adeel.cvapp.retrofit.Education

class EducationRVAdapter(diffUtil: DiffUtil.ItemCallback<Education>) :
    ListAdapter<Education, EducationRVAdapter.EducationViewHolder>(diffUtil) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): EducationViewHolder {
        val contentView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_experience_education, viewGroup, false)
        return EducationViewHolder(contentView)
    }

    override fun onBindViewHolder(viewHolder: EducationViewHolder, position: Int) {
        val education = getItem(viewHolder.adapterPosition)

        viewHolder.tvSchoolName.text = education.school_name
        viewHolder.tvDegreeInfo.text = education.degree
        viewHolder.tvPeriodLocation.text = education.from_date + " to " + education.to_date + " - " + education.location
        viewHolder.tvDescription.text = education.details
    }

    class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSchoolName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvName) }
        val tvDegreeInfo: TextView by lazy { itemView.findViewById<TextView>(R.id.tvInformation) }
        val tvPeriodLocation: TextView by lazy { itemView.findViewById<TextView>(R.id.tvPeriodLocation) }
        val tvDescription: TextView by lazy { itemView.findViewById<TextView>(R.id.tvDescription) }
    }
}