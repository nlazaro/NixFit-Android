package com.example.nixfit.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.nixfit.R


@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
){
    Column(modifier = Modifier
        .fillMaxSize()){
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Hello World",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        IconButton(onClick = { /*TODO*/ })
        {
            Image(
                painter = painterResource(
                    id = R.drawable.hamburger_icon),
                contentDescription = "Menu"
            )
        }
    }
}

@Composable
@Preview
fun DashboardScreenPreview(){
    DashboardScreen()
}