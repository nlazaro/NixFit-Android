package com.nlazaro.nixfit_android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.nlazaro.nixfit_android.fragments.DashboardFragment
import com.nlazaro.nixfit_android.fragments.HomeFragment
import com.nlazaro.nixfit_android.fragments.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var fabBarcodeScanner: FloatingActionButton
    private lateinit var barCodeScanner: GmsBarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
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

        // setups barcode scanner options
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_EAN_8,
                Barcode.FORMAT_EAN_13,
                Barcode.FORMAT_UPC_A,
                Barcode.FORMAT_UPC_E)
            .allowManualInput()
            .build()
        barCodeScanner = GmsBarcodeScanning.getClient(this, options)
        fabBarcodeScanner = findViewById(R.id.fabBarcodeScanner)
        fabBarcodeScanner.setOnClickListener {
            barCodeScanner.startScan()
                .addOnSuccessListener { result ->
                    val barcodeNumber: String? = result.rawValue
                    Toast.makeText(this, "$barcodeNumber", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Scan failed: ${exception.message}", Toast.LENGTH_LONG).show()
                }
        }

    }
}