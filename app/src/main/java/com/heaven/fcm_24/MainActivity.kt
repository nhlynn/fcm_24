package com.heaven.fcm_24

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.heaven.fcm_24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 33) {
            val notificationPermission = arrayOf(
                Manifest.permission.POST_NOTIFICATIONS
            )
            if (!hasPermissions(notificationPermission)) {
                permissionRequestLauncher.launch(notificationPermission)
            }
        }

        binding.btnSubscribe.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("lynn")
        }

        binding.btnUnSubscribe.setOnClickListener {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("lynn")
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }

    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            val message = if (granted){
                "Allowed"
            }else{
                "Denied"
            }
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }
}