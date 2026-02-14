package com.kuciaapp.canbecheaperkmp.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kuciaapp.canbecheaperkmp.ui.theme.MainColor
import com.kuciaapp.canbecheaperkmp.utility.UserSession
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController,
    userSession: UserSession) {
    val scope = rememberCoroutineScope()
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(50.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
//                    Image(
//                       //painter = painterResource(id = R.drawable.image),
//                        contentDescription = "Medical image",
//                        modifier = Modifier
//                            .size(200.dp)
//                            .padding(40.dp)
//                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                GenerateButton("Spożywcze") {
                    navController.navigate("ProductPriceScreen/1")
                }
                Spacer(modifier = Modifier.height(5.dp))
                GenerateButton("Dom i zdrowie") {
                    navController.navigate("ExaminationScreen/2")
                }
                Spacer(modifier = Modifier.height(5.dp))
                GenerateButton("Elektronika i AGD") {
                    navController.navigate("ExaminationScreen/3")
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    modifier = Modifier
                        .height(80.dp)
                        .width(310.dp),
                    onClick = { navController.navigate("ConfigurationScreen") }) {
                    Text("Ustawienia")
                }

                Spacer(modifier = Modifier.weight(3f))

                TextButton( modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            userSession.clearUserId()
                            navController.navigate("LoginScreen") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                ) {
                    Text("Wyloguj się",
                        color = Color.Black)
                }



            }
        }
    }
}

@Composable
fun GenerateButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .height(80.dp)
            .width(310.dp),
        onClick = onClick
    ) {
        Text(label)
    }
}

