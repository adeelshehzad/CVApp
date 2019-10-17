package com.adeel.cvapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.adeel.cvapp.R
import com.adeel.cvapp.fragment.EducationFragment
import com.adeel.cvapp.fragment.ExperienceFragment
import com.adeel.cvapp.fragment.HighlightsFragment
import com.adeel.cvapp.fragment.IntroFragment
import com.adeel.cvapp.model.Intro
import com.adeel.cvapp.retrofit.CVResponse
import com.adeel.cvapp.retrofit.CVService
import com.adeel.cvapp.retrofit.Education
import com.adeel.cvapp.retrofit.Experience
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCVData()
    }

    /**
     * Initialize Retrofit client and get data from gist then load the data into fragments.
     */
    private fun getCVData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cvService = retrofit.create(CVService::class.java)
        val call = cvService.getCVData()

        call.enqueue(object : Callback<CVResponse> {
            override fun onFailure(call: Call<CVResponse>, t: Throwable) {
                Log.e(LOG_TAG, "Exception: ", t)
                Toast.makeText(this@MainActivity, "Exception occurred fetching data: ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<CVResponse>, response: Response<CVResponse>) {
                Log.d(LOG_TAG, "responseCode: ${response.code()}")
                Log.d(LOG_TAG, "response: ${response.body()}")

                //Seperate sections of CV
                val intro = getIntroFromResponse(response.body())
                val highlights = response.body().highlights
                val experience = response.body().experience
                val education = response.body().education

                loadFragments(intro, highlights, experience, education)
            }
        })
    }

    private fun getIntroFromResponse(cvResponse: CVResponse): Intro {
        return Intro(
            cvResponse.name,
            cvResponse.designation,
            cvResponse.address,
            cvResponse.postal_code,
            cvResponse.mobile_number,
            cvResponse.email_address
        )
    }

    /**
     * Load all fragments within their respective FrameLayout containers.
     * This will create a single page application and will give the application a Wikipedia-esque look
     * without having the activity to do all the work related to views.
     *
     * @param intro The intro section (Name, Address, Contact Details etc)
     * @param highlights The Skills/Career Highlights section
     * @param experience The work experience section
     * @param education The education section
     */
    fun loadFragments(
        intro: Intro,
        highlights: List<String>,
        experience: List<Experience>,
        education: List<Education>
    ) {
        val introFragment = IntroFragment()
        introFragment.setData(intro)

        val highlightsFragment = HighlightsFragment()
        highlightsFragment.setData(highlights)

        val experienceFragment = ExperienceFragment()
        experienceFragment.setData(experience)

        val educationFragment = EducationFragment()
        educationFragment.setData(education)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.introFragmentContainer, introFragment)
        fragmentTransaction.replace(R.id.highlightFragmentContainer, highlightsFragment)
        fragmentTransaction.replace(R.id.experienceFragmentContainer, experienceFragment)
        fragmentTransaction.replace(R.id.educationFragmentContainer, educationFragment)
        fragmentTransaction.commit()
    }

    companion object {
        val LOG_TAG = "MainActivity"
        val baseURL =
            "https://gist.githubusercontent.com/adeelshehzad/599bfc2612d88a58af4cb097e2e855f4/raw/3a6d1a01047c57e3d639c05c9c2e81b635f559c1/"
    }
}
