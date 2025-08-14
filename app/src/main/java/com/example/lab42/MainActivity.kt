package com.example.lab42

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lab42.ui.theme.Lab42Theme


data class Receta(
    val nombre: String,
    val imgURL: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab42Theme {
                PantallaPrincipal()
            }
        }
    }
}

@Composable
fun PantallaPrincipal(){
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var imgURL by remember { mutableStateOf(TextFieldValue("")) }

    val recetas = remember {mutableStateListOf<Receta>()}


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Healthy Living App",
            fontSize = 24.sp,
            modifier = Modifier.
                align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(35.dp))

        TextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = {Text("Nombre de la receta: ")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = imgURL,
            onValueChange = {imgURL = it},
            label = {Text("URL de la imagen: ")},
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombre.text.isNotBlank() && imgURL.text.isNotBlank() &&
                    !recetas.any {it.nombre == nombre.text && it.imgURL == imgURL.text}){
                    recetas.add(Receta(nombre.text, imgURL.text))
                    nombre = TextFieldValue("")
                    imgURL = TextFieldValue("")
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Todas tus recetas:",
            fontSize = 15.sp,
            color = Color.Blue,
            modifier = Modifier.
                align(Alignment.Start)
        )

        LazyColumn {
            items(recetas) { receta ->
                RecetaCard(
                    receta = receta,
                    onRemove = { recetas.remove(receta) }
                )
            }
        }
    }


}


@Composable
fun RecetaCard(receta: Receta, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ){
            AsyncImage(
                model = receta.imgURL,
                contentDescription = receta.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = receta.nombre)

            Spacer(modifier = Modifier.weight(1f))

            //Texto de la x para eliminar la receta
            Text(
                text = "❌",
                fontSize = 25.sp,
                color = Color.Red,
                modifier = Modifier
                    .clickable{
                        onRemove()
                    }
            )
        }
    }
}