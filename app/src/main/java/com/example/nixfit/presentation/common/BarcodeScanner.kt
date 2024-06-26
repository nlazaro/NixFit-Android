package com.example.nixfit.presentation.common

import android.content.Context
import android.widget.Toast
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

// Logic for barcode scanning
object BarcodeScannerUtil {
    fun startBarcodeScanning(context: Context) {
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_EAN_8,
                Barcode.FORMAT_EAN_13,
                Barcode.FORMAT_UPC_A,
                Barcode.FORMAT_UPC_E
            )
            .enableAutoZoom()
            .build()
        val scanner = GmsBarcodeScanning.getClient(context, options)
        scanner.startScan()
            .addOnSuccessListener { barcodes ->
                val barcodeNumber: String? = barcodes.rawValue
                Toast.makeText(context, barcodeNumber, Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Scan failed: ${exception.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }
}