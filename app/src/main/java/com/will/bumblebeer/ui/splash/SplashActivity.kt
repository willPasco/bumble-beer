package com.will.bumblebeer.ui.splash

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.ActivitySplashBinding
import com.will.bumblebeer.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    companion object {
        const val logTag = "SplashLog"
    }

    /**
     * ViewModel reference with lazy injection
     */
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setup()

        observeErrors()
        observeIfDataLoaded()
    }

    private fun observeErrors() {
        viewModel.errorLiveData.observe(this@SplashActivity, Observer {
            AlertDialog.Builder(this@SplashActivity)
                .setTitle(R.string.error)
                .setMessage("${it.message}\n${getString(R.string.closing_app)}")
                .setCancelable(false)
                .setPositiveButton(R.string.ok) { dialogInterface, which ->
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        dialogInterface.dismiss()
                        finish()
                    }
                }
                .show()
        })
    }

    private fun observeIfDataLoaded() {
        viewModel.showNextViewLiveData.observe(this@SplashActivity, Observer { willRedirectToHome ->
            if (willRedirectToHome) {
                Log.i(logTag, "Redirecting to Home Activity from Splash")
                startActivity(
                    Intent(
                        this@SplashActivity,
                        HomeActivity::class.java
                    )
                )
                finish()
            }
        })
    }
}
