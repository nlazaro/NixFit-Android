package com.example.nixfit.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun BarcodeScanner(){
    val context = LocalContext.current
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_UPC_A,
            Barcode.FORMAT_UPC_E)
        .enableAutoZoom()
        .build()
    val scanner = GmsBarcodeScanning.getClient(context, options)

    Column{
        FloatingActionButton(
            onClick = {
                scanner.startScan()
                    .addOnSuccessListener { barcodes ->
                        val barcodeNumber: String? = barcodes.rawValue
                        Toast.makeText(context, barcodeNumber, Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "Scan failed: ${exception.message}", Toast.LENGTH_LONG).show()
                    }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Scan barcode")
        }
    }
}

@Preview
@Composable
fun BarcodeScannerPreview(){
    BarcodeScanner()
}