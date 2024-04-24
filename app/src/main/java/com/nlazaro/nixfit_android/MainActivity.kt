package com.nlazaro.nixfit_android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.nlazaro.nixfit_android.fragments.DashboardFragment
import com.nlazaro.nixfit_android.fragments.HomeFragment
import com.nlazaro.nixfit_android.fragments.ProfileFragment


class MainActivity : AppCompatActivity() {

    private lateinit var barcodeLauncher: ActivityResultLauncher<ScanOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)

        // Handles navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragmentToShow: Fragment
            when (item.itemId) {
                R.id.navigation_home -> fragmentToShow = HomeFragment()
                R.id.navigation_dashboard -> fragmentToShow = DashboardFragment()
                R.id.navigation_profile -> fragmentToShow =  ProfileFragment()
            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            // Return true to say that we've handled this user interaction on the item
            true
        }
        // Sets default selection
        bottomNavigationView.selectedItemId = R.id.navigation_home

        // -- end of setup actions --

        barcodeLauncher = registerForActivityResult(ScanContract()) {
                result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val client: APIClient = APIClient()
                val response:String = client.GetBardcodeData(result.contents)
                Toast.makeText(
                    this@MainActivity,
                    "Scanned: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()

            }
        }
        val fabBarcodeScanner = findViewById<FloatingActionButton>(R.id.fabBarcodeScanner)
        fabBarcodeScanner.setOnClickListener{
            Log.d("MainActivity", "Barcode button clicked!")
            barcodeLauncher.launch(ScanOptions()
                .setPrompt("Scan a barcode")
                .setBeepEnabled(false)
                .setOrientationLocked(false))
        }

    }
}