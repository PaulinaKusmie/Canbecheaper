package com.kuciaapp.canbecheaperkmp.view

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuciaapp.canbecheaperkmp.ui.theme.MainColor
import com.kuciaapp.canbecheaperkmp.viewmodel.ConfigurationViewModel
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(
    navController: NavController,
    onBack: () -> Unit,
    vm: ConfigurationViewModel = koinViewModel()) {
    val showDialog by vm.showDialog.collectAsState()

    if(showDialog){
        AlertDialogConfiguration(
            onDismissRequest = {
                vm.setShowDialog(false)
            },
            onConfirmation = {
                navController.navigate("LoginScreen")
                vm.deleteUser()

            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(MainColor),
                title = { Text("Konfiguracja") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Powrót",
                            tint = Color.Black
                        )
                    }
                },
            )

        },
        containerColor = MainColor
    ) {

            padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            val switchGradient = Brush.linearGradient(
                colors = listOf(Color(0xFF6E48AA), Color(0xFF9D50BB))
            )
            Card(
                modifier = Modifier.background(MainColor)
                    .border(1.dp, brush = switchGradient, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(),
                onClick = { navController.navigate("ReminderScreen") }
            ) {
                Text(
                    modifier = Modifier.background(MainColor)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Przypomnienia"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.background(MainColor)
                    .border(1.dp, brush = switchGradient, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(),

                onClick = { navController.navigate("SettingsSpecjalizationScreen") }

            ) {
                Text(
                    modifier = Modifier.background(MainColor)
                        .fillMaxWidth(),
                    text = "Zarządzanie wizytami",
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.background(MainColor)
                    .border(1.dp, brush = switchGradient, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(),

                onClick = { navController.navigate("SettingsExaminationScreen") }

            ) {
                Text(
                    modifier = Modifier.background(MainColor)
                        .fillMaxWidth(),
                    text = "Zarządzanie badaniami",
                    textAlign = TextAlign.Center
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier.background(MainColor)
                    .border(1.dp, brush = switchGradient, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth(),

                onClick = {
                   vm.setShowDialog(true)
                }

            ) {
                Text(
                    modifier = Modifier.background(MainColor)
                        .fillMaxWidth(),
                    text = "Usuń konto",
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


@Composable
fun AlertDialogConfiguration(onDismissRequest: () -> Unit, onConfirmation: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Uwaga!")
        },

        text = {
            Column() {
                Text(
                    text = "Czy na pewno chcesz usunąć konto? " +
                            "Wszystkie dane zostaną bezpowrotnie usunięte!",
                    modifier = Modifier.fillMaxWidth(),
                    textDecoration = TextDecoration.Underline
                )
            }

        },

        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                })
            {
                Text("Tak, usuń konto")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismissRequest()
            })
            {
                Text("Nie")
            }
        }
    )

}





