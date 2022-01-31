package com.example.blank

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.blank.data.remote.services.CountriesService
import com.example.blank.ui.main.SectionsPagerAdapter
import com.example.blank.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import com.example.blank.data.remote.models.Country
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import android.view.Gravity

import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout


class MainActivity : AppCompatActivity() {
    companion object {
        const val URL_COUNTRY_API = "https://rescountries.eu/"
    }

    val retrofitclient = Retrofit.Builder()
        .baseUrl(URL_COUNTRY_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retroservice = retrofitclient.create(CountriesService::class.java)



    suspend fun makeRequest(view: View, request_endpoint: String) {
        fun toast(text: String) {
            Snackbar.make(
                view,

                text,
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }
        coroutineScope {

            launch(Dispatchers.IO) {

                val client: OkHttpClient = OkHttpClient()
                @Throws(IOException::class)
                fun run(url: String): StringBuffer {
                    val request: Request = Request.Builder()
                        .url(url)
                        .build()
                    client.newCall(request).execute().use {
                        return StringBuffer(it.body?.string() ?: "")
                    }
                }

                val server_url = "http://66.115.189.211:56811/$request_endpoint"

                try {
                    val httpResponse = run(server_url)

                    val strResp = httpResponse.toString()
                    if (strResp == "0") {
                        toast("the counter is zero")
                    } else {
                        toast(strResp)
                    }

                } catch (ex: Exception) {
                    toast("the server is unreachable")
                }

            }
        }
    }



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.check
        val rst: FloatingActionButton = binding.rst
        val inc: FloatingActionButton = binding.inc

        fab.setOnClickListener { view -> run {


            runBlocking {
                makeRequest(view, "status")
            }
        }

        }

        rst.setOnClickListener { view -> run {


            runBlocking {
                makeRequest(view, "reset")
            }
        }

        }

        inc.setOnClickListener { view -> run {


            runBlocking {
                makeRequest(view, "increment")
            }
        }

        }
    }
}