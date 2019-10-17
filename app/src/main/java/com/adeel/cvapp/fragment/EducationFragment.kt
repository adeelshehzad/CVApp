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
import com.adeel.cvapp.adapter.EducationRVAdapter
import com.adeel.cvapp.retrofit.Education
import kotlinx.android.synthetic.main.fragment_education.*

/**
 * This fragment contains information related to education
 */
class EducationFragment : Fragment() {
    private val education = ArrayList<Education>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvEducationLabel.setOnClickListener {
            val areHighlightsVisible: Boolean = rvEducation.visibility == View.VISIBLE
            when {
                areHighlightsVisible -> {
                    rvEducation.visibility = View.GONE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_up_float)
                    tvEducationLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
                else -> {
                    rvEducation.visibility = View.VISIBLE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_down_float)
                    tvEducationLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
            }
        }

        val adapter = EducationRVAdapter(object : DiffUtil.ItemCallback<Education>() {
            override fun areItemsTheSame(p0: Education, p1: Education): Boolean {
                return false
            }

            override fun areContentsTheSame(p0: Education, p1: Education): Boolean {
                return false
            }

        })
        adapter.submitList(education)

        rvEducation.layoutManager = LinearLayoutManager(activity)
        rvEducation.adapter = adapter
    }

    fun setData(education: List<Education>) {
        this.education.addAll(education)
    }
}