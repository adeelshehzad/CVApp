package com.adeel.cvapp.adapter

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adeel.cvapp.R
import com.adeel.cvapp.retrofit.Experience

class ExperienceRVAdapter(diffCallback: DiffUtil.ItemCallback<Experience>, private val context: Context) :
    ListAdapter<Experience, ExperienceRVAdapter.ExperienceViewHolder>(diffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ExperienceViewHolder {
        val contentView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_experience_education, viewGroup, false)
        return ExperienceViewHolder(contentView)
    }

    override fun onBindViewHolder(viewHolder: ExperienceViewHolder, position: Int) {
        val experience = getItem(viewHolder.adapterPosition)

        viewHolder.tvCompanyName.text = experience.employer_name
        viewHolder.tvRole.text = experience.designation
        viewHolder.tvPeriodLocation.text =
            experience.from_date + " to " + experience.to_date + " - " + experience.location

        experience.more_details.forEach { item ->
            viewHolder.tvRoleDescription.append(context.getString(R.string.bullet_pt) + item + "\n")
        }
    }

    class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCompanyName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvName) }
        val tvRole: TextView by lazy { itemView.findViewById<TextView>(R.id.tvInformation) }
        val tvPeriodLocation: TextView by lazy { itemView.findViewById<TextView>(R.id.tvPeriodLocation) }
        val tvRoleDescription: TextView by lazy { itemView.findViewById<TextView>(R.id.tvDescription) }
    }

}