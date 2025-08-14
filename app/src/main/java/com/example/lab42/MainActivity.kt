package com.example.lab42

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab42.ui.theme.Lab42Theme
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.lab42.ui.theme.Lab42Theme

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

    val recetas = remember {mutableStateListOf<Receta>()


    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = {Text("Nombre de la receta: ")},
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = imgURL,
        onValueChange = {imgURL = it},
        label = {Text("URL de la imagen: ")},
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick{
            if (nombre.text.isNotBlank() && imgURL.text.isNotBlank()){
                recetas.add(Receta(nombre.text, imgURL.text))
                nombre = TextFieldValue("")
                imgURL = TextFieldValue("")
            }
        },
        modifier = Modifier.align(Alignment.End)
    ) {
        Text("Agregar")
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(recetas) { receta ->
            RecetaCard(receta)
        }
    }
}


@Composable
fun RecetaCard(receta: Receta) {

}