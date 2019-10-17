package com.adeel.cvapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adeel.cvapp.R
import kotlinx.android.synthetic.main.fragment_highlights.*

/**
 * This fragment contains the information related to skills/career highlights
 */
class HighlightsFragment : Fragment() {
    private val highlights = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_highlights, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (highlights.size > 0) {
            for (item in highlights) {
                tvHighlight.append(getString(R.string.bullet_pt) + item + "\n")
            }
        }

        //expand/collapse highlights
        tvHighlightsLabel.setOnClickListener {
            val areHighlightsVisible: Boolean = tvHighlight.visibility == View.VISIBLE
            when {
                areHighlightsVisible -> {
                    tvHighlight.visibility = View.GONE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_up_float)
                    tvHighlightsLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
                else -> {
                    tvHighlight.visibility = View.VISIBLE

                    val leftDrawable = ContextCompat.getDrawable(activity!!, android.R.drawable.arrow_down_float)
                    tvHighlightsLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, null, null, null)
                }
            }
        }
    }

    fun setData(highlights: List<String>) {
        this.highlights.addAll(highlights)
    }
}