package com.example.nixfit.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nixfit.NixFitApp
import com.example.nixfit.R
import com.example.nixfit.ui.theme.NixFitTheme

@Composable
internal fun HomeContent(
    viewModel: HomeViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    HomeScreen(state = state)

}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit = {},
    state: HomeViewState
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Welcome to the app",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        FloatingActionButton(
            modifier = Modifier.weight(1f),
            onClick = onNextButtonClicked
        ){
            Text(stringResource(R.string.next))
        }
//        OutlinedButton(
//            modifier = Modifier.weight(1f),
//            onClick = onCancelButtonClicked
//        ) {
//            Text(stringResource(R.string.cancel))
//        }
//        Button(
//            modifier = Modifier.weight(1f),
//            onClick = onNextButtonClicked
//        ) {
//            Text(stringResource(R.string.next))
//        }
    }
}

@Preview
@Composable
fun NixFitAppPreview() {
    NixFitTheme {
        NixFitApp()
    }
}
