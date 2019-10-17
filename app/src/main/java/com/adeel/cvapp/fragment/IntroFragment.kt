package com.adeel.cvapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adeel.cvapp.R
import com.adeel.cvapp.model.Intro
import kotlinx.android.synthetic.main.fragment_intro.*

/**
 * This fragment contains information like Name, Address, Contact Details
 */
class IntroFragment : Fragment() {
    var intro: Intro? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (intro != null) {
            tvName.text = intro?.name
            tvAddress.text = intro?.address + "\n" + intro?.postal_code
            tvMobileNumber.text = intro?.mobile_number
            tvEmail.text = intro?.email_address
        }
    }

    fun setData(intro: Intro) {
        this.intro = intro
    }
}