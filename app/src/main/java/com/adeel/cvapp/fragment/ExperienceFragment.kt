package com.adeel.cvapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adeel.cvapp.R
import com.adeel.cvapp.adapter.ExperienceRVAdapter
import com.adeel.cvapp.retrofit.Experience
import kotlinx.android.synthetic.main.fragment_experience.*

/**
 * This fragment contains information related to work experience
 */
class ExperienceFragment : Fragment() {
    private val experience = ArrayList<Experience>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //expand/collapse experience
        tvExperienceLabel.setOnClickListener {
            val areHighlightsVisible: Boolean = rvExperience.visibility == View.VISIBLE
            when {
                areHighlightsVisible -> {
                    rvExperience.visibility = View.GONE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_up_float)
                    tvExperienceLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
                else -> {
                    rvExperience.visibility = View.VISIBLE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_down_float)
                    tvExperienceLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
            }
        }

        val adapter = ExperienceRVAdapter(object : DiffUtil.ItemCallback<Experience>() {
            override fun areItemsTheSame(p0: Experience, p1: Experience): Boolean {
                return false
            }

            override fun areContentsTheSame(p0: Experience, p1: Experience): Boolean {
                return false
            }

        }, activity!!)
        adapter.submitList(experience)

        rvExperience.layoutManager = LinearLayoutManager(activity)
        rvExperience.adapter = adapter
    }

    fun setData(experience: List<Experience>) {
        this.experience.addAll(experience)
    }
}