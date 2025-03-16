package com.example.imcapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcapp.ui.theme.IMCAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    IMCScreen()
                }
            }
        }
    }
}

@Composable
fun IMCScreen() {


    var peso = remember {
        mutableStateOf("")
    }

    var altura = remember {
        mutableStateOf("")
    }

    var imc = remember {
        mutableStateOf(0.0)
    }

    var statusImc = remember {
        mutableStateOf("")
    }




    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // ---- header ---------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorResource(id = R.color.vermelho_fiap))
            ) {
              Image(painter = painterResource(id = R.drawable.bmi),
                  contentDescription = "Icone BMI" ,
                  modifier = Modifier
                      .size(70.dp)
                      .padding(top = 16.dp)
                  )
                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
                )
            }
            // --- formulário
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
            Card(modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black)

            ) {
                Column(
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = 30.dp
                    )
                ) {
                    Text(
                        text = "Seus dados",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.vermelho_fiap),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Seu peso",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )
                    OutlinedTextField(
                        value = peso.value,
                        singleLine = true,
                        onValueChange = { peso.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Seu peso em Kg.")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sua altura",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.vermelho_fiap)
                    )
                    OutlinedTextField(
                        value = altura.value,
                        onValueChange = {altura.value = it},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Sua altura em cm."
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                            focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                               imc.value = calularImc(peso = peso.value.toDouble(), altura.value.toDouble())
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.vermelho_fiap))
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            }
            }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
            elevation = CardDefaults.cardElevation(4.dp),
            //border = BorderStroke(width = 1.dp, Color(0xffed145b))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize()
            ) {
                Column() {
                    Text(
                        text = "Resultado",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Peso Ideal.",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = "${imc.value}",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End
                )
            }
        }
            }
        }
        // -- Card Resultado



