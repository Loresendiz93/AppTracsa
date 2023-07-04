package com.example.compp
import MyViewModel
import android.app.AlertDialog
import javax.mail.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.content.ActivityNotFoundException
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.content.Context
import android.net.Uri
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.compp.ui.theme.ComppTheme
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
class MainActivity : ComponentActivity() {
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
            ComppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                ) {
                    MyComposable(myViewModel )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposable(myViewModel: MyViewModel) {
    val savedImagePaths = mutableListOf<String>()
    var concatenatedMessage by remember { mutableStateOf("") }
    var fontSize by remember { mutableStateOf(20.sp) }
    var expandedButtonTable by remember { mutableStateOf(false) }
    var expanded_llantas by remember { mutableStateOf(false) }
    val opciones_llantas = listOf(
        " Llantas delanteras",
        " Llantas traseras"
    )
    var selectedOption_llantas by remember { mutableStateOf("") }
    var expanded_chasisGuarda_comb by remember { mutableStateOf(false) }
    val opciones_chasisGuarda_comb = listOf(
        "Calcas",
        "Guarda del operador",
        "Bujes cilindro incli",
        "Pernos de inclinacion",
        "Asiento",
        "Micro de asiento",
        "Chapa de puerta",
        "Pernos de puerta",
        "Eje de direccion"
    )
    var selectedOption_chasisGuarda_comb by remember { mutableStateOf("") }
    var expanded_electrico_comb by remember { mutableStateOf(false) }
    val opciones_electrico_comb = listOf(
        "Marcha",
        "Alternador",
        "Faros",
        "Cuartos",
        "Batería",
        "Display",
        "VCM",
        "ECM",
        "Harness",
        "Alarma de reversa",
        "Caja de fusibles",
        "Claxon",
        "Bomba de gasolina",
        "Palanca de avance",
        "Switch de luces"
    )
    var selectedOption_electrico_comb by remember { mutableStateOf("") }
    var expanded_direccion by remember { mutableStateOf(false) }
    val opciones_direccion = listOf(
        "Pernos",
        "Balero esférico",
        "Masas",
        "Cilindro de dirección",
        "Bujes de eje"
    )
    var selectedOption_direccion by remember { mutableStateOf("") }
    var expanded_ejeFrenos_comb by remember { mutableStateOf(false) }
    val opciones_ejeFrenos_comb = listOf(
        "Aceite diferencial",
        "Reten de eje",
        "Tambores",
        "Balatas",
        "Retenes",
        "Baleros",
        "Empaque del cilindro",
        "Bomba de frenos",
        "Palanca de frenos"
    )
    var selectedOption_ejeFrenos_comb by remember { mutableStateOf("") }
    var expanded_transmision_comb by remember { mutableStateOf(false) }
    val opciones_transmision_comb = listOf(
        "Aceite de transmisión",
        "Mangueras",
        "Fugas en retenes",
        "Funcionamiento"
    )
    var selectedOption_transmision_comb by remember { mutableStateOf("") }
    var expanded_motor by remember { mutableStateOf(false) }
    val opciones_motor = listOf(
        "Filtro de aire",
        "Bujias",
        "Filtro de aceite",
        "Aceite del motor",
        "Anticongelante",
        "Fugas de aceite",
        "Banda del alternador",
        "Radiador",
        "Guarda del ventilador",
        "Kit del vaporizador",
        "Ventilador",
        "Bomba de agua"
    )
    var selectedOption_motor by remember { mutableStateOf("") }
    var expanded_chasis_guarda by remember { mutableStateOf(false) }
    val opciones_chasis_guarda = listOf(
        "Calcas",
        "Guarda del operador",
        "Bujes del cilindro de inclinación",
        "Pernos de inclinación",
        "Asiento",
        "Micro de asiento",
        "Chapa de puerta",
        "Pernos de puerta",
        "Eje de dirección"
    )
    var selectedOption_chasis_guarda by remember { mutableStateOf("") }
    var expanded_mastil by remember { mutableStateOf(false) }
    val opciones_mastil = listOf(
        "Pads seccion/desplazador",
        "Respaldo de carga",
        "Cilindro desplazador",
        "Baleros",
        "Poleas",
        "Cilindro principal",
        "Cilindro secundario",
        "Mangueras",
        "Horquillas",
        "Seguro de Horquillas",
        "Medias lunas",
        "Cadenas centrales",
        "Cadenas laterales"
    )
    var selectedOption_mastil by remember { mutableStateOf("") }
    var expanded_sistema_electrico by remember { mutableStateOf(false) }
    val opciones_sistema_electrico = listOf(
        "Revision de conectores",
        "Monitoreo de parametros",
        "Luces de trabajo",
        "Luces stop",
        "Bateria",
        "Display",
        "Lavado con dielectrico",
        "Controladores",
        "Arnes",
        "Alarma de reversa",
        "Motores",
        "Claxon",
        "Solopilot"
    )
    var selectedOption_sistema_electrico by remember { mutableStateOf("") }
    var expanded_bateria by remember { mutableStateOf(false) }
    val opciones_bateria = listOf(
        "Fecha de verificacion",
        "Voltaje total",
        "Voltaje minimo de celda",
        "Voltaje maximo de celda",
        "Gravedad especifica",
        "Tipo de conector",
        "Limpieza de bateria"
    )
    var selectedOption_bateria by remember { mutableStateOf("") }
    var expanded_cargador by remember { mutableStateOf(false) }
    val opciones_cargador = listOf(
        "Funcionamiento",
        "Voltaje de entrada",
        "Voltaje de salida",
        "Tipo de conector"
    )
    var selectedOption_cargador by remember { mutableStateOf("") }
    var expanded_ejeFrenos by remember { mutableStateOf(false) }
    val opciones_ejeFrenos = listOf(
        "Motores traccion",
        "Sistema de frenado",
        "Balatas",
        "Bomba de frenos"
    )
    var selectedOption_ejeFrenos by remember { mutableStateOf("") }
    var expanded_transmision by remember { mutableStateOf(false) }
    val opciones_transmision = listOf(
        "Aceite de engranes",
        "Fugas retenes",
        "Funcionamiento",
        "Ajuste de velocidad"
    )
    var selectedOption_transmision by remember { mutableStateOf("") }
    var expanded_sistema_hidraulicos by remember { mutableStateOf(false) }
    val opciones_sistema_hidraulicos = listOf(
        "Aceite hidraulico",
        "Filtro aceite retorno",
        "Pruebas banco hidraulico",
        "Inspeccion de fugas",
        "Llenado de fluidos",
        "Mandos hidraulicos",
        "Bomba hidraulica",
    )
    var selectedOption_sistema_hidraulicos by remember { mutableStateOf("") }
    var expanded_tecnicos by remember { mutableStateOf(false) }
    val opciones_tecnicos = listOf(
        "Aceves Bernal Daniel Israel",
        "Alejandre Rios Alejandro",
        "Altamirano Estrada Edgar Antonio",
        "Amaton Verduzco Marco Antonio de Jesus",
        "Ayala Contreras Victor Hugo",
        "Diaz Banda Jesus Alberto",
        "Flores Garibay Jahel",
        "Garcia Castillo, Oscar",
        "Garcia Panduro Jose Angel",
        "Garcia Panduro Moises",
        "Gaytan Martinez Agustin",
        "Gonzalez Resendiz Luis Angel",
        "Gutierrez Corona Christian Yael Guadalupe",
        "Hernandez Castillo Luis Alonso",
        "Hernandez Garcia Jose Manuel",
        "Leon Sandoval Jesus Saul",
        "Lopez Montalvo David",
        "Macias Cabrera Josue Alejandro",
        "Morales Garcia Juan Ramon",
        "Nachis Figueroa Carlos Adrian",
        "Ocampo Becerra Luis Carlos",
        "Padilla Castañon Jesus Anibal",
        "Perez Poblano Carlos Miguel",
        "Prado Anguiano Ricardo Omar",
        "Ramos Reynoso Idelfonso",
        "Ramos Ruiz Jose de Jesus",
        "Reynoso Ruvalcaba Rogelio Isaias",
        "Saavedra Castorena Jose Guadalupe",
        "Sandoval Franco Gerardo Manuel",
        "Sandoval Galvez Carlos Eduardo",
        "Sandoval Zambrano Alejandro",
        "Segura Rodriguez Miguel Eduardo",
        "Toribio Aviña Luis",
        "Torres Contreras Juan Jesus",
        "Ureña Carranza Guadalupe de Jesus",
        "Vargas Guzman Branndon Ryan"
    )
    var selectedOption_tecnicos by remember { mutableStateOf("") }
    var expanded_equipos by remember { mutableStateOf(false) }
    val opciones_equipos = listOf(
        "Montacargas de combustion de gran capacidad",
        "Montacargas de combustion",
        "Montacargas electrico de gran capacidad",
        "Montacargas electrico",
        "Montacargas autonomo",
        "Patin hidraulico (transpaleta)",
    )
    var selectedOption_equipos by remember { mutableStateOf("") }
    var expanded_marcas by remember { mutableStateOf(false) }
    val opciones_marcas = listOf(
        "CAT",
        "JUNGEINRICH",
        "KALMAR",
        "MITSUBISHI",
        "ROCLA",
    )
    var selectedOption_marcas by remember { mutableStateOf("") }

    var horometro by remember { mutableStateOf<String?>(null) }
    var model by remember { mutableStateOf<String?>(null) }
    var serialNumber by remember { mutableStateOf<String?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.montitass),
            contentDescription = "Fondo de pantalla",
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(111.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "TRACSA",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Button(
                onClick = { expanded_tecnicos = !expanded_tecnicos },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RectangleShape
                    )
                    .clip(RectangleShape)
            ) {
                Text("Tecnico", fontSize = 20.sp)
            }
            if (expanded_tecnicos) {
                opciones_tecnicos.forEach { opcion ->
                    Button(
                        onClick = {
                            selectedOption_tecnicos = opcion
                            expanded_tecnicos = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text(opcion, fontSize = 20.sp)
                    }
                }
            }
            Text(
                text = selectedOption_tecnicos.orEmpty(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { expanded_equipos = !expanded_equipos },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RectangleShape
                    )
                    .clip(RectangleShape)
            ) {
                Text("Equipo", fontSize = 20.sp)
            }
            if (expanded_equipos) {
                opciones_equipos.forEach { opcion ->
                    Button(
                        onClick = {
                            selectedOption_equipos = opcion
                            expanded_equipos = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(opcion, fontSize = 20.sp)
                    }
                }
            }
            Text(
                text = selectedOption_equipos.orEmpty(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { expanded_marcas = !expanded_marcas },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RectangleShape
                    )
                    .clip(RectangleShape)
            ) {
                Text("Marca", fontSize = 20.sp)
            }
            if (expanded_marcas) {
                opciones_marcas.forEach { opcion ->
                    Button(
                        onClick = {
                            selectedOption_marcas = opcion
                            expanded_marcas = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(opcion, fontSize = 20.sp)
                    }
                }
            }
            Text(
                text = selectedOption_marcas.orEmpty(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            TextField(
                value = horometro.orEmpty(),
                onValueChange = { horometro = it },
                label = {
                    Text(
                        "Horometro",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
            )
            TextField(
                value = model.orEmpty(),
                onValueChange = { model = it },
                label = {
                    Text(
                        "Modelo",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
            )
            TextField(
                value = serialNumber.orEmpty(),
                onValueChange = { serialNumber = it },
                label = {
                    Text(
                        "Numero de serie",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))
            if (horometro?.isNotEmpty() == true && serialNumber?.isNotEmpty() == true &&
                model?.isNotEmpty() == true && selectedOption_marcas.isNotEmpty() == true &&
                selectedOption_tecnicos.isNotEmpty() == true && selectedOption_equipos.isNotEmpty() == true
            ) {

                if (selectedOption_equipos == "Montacargas de combustion" || selectedOption_equipos == "Montacargas de combustion de gran capacidad") {
                    Button(
                        onClick = {
                            expanded_motor = !expanded_motor
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Motor", fontSize = 20.sp)
                    }
                    if (expanded_motor) {
                        opciones_motor.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_motor = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_motor == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Motor", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_transmision_comb =
                                !expanded_transmision_comb
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Transmisión", fontSize = 20.sp)
                    }
                    if (expanded_transmision_comb) {
                        opciones_transmision_comb.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_transmision_comb = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_transmision_comb == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Transmision", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_ejeFrenos_comb = !expanded_ejeFrenos_comb
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Eje motriz y frenos", fontSize = 20.sp)
                    }
                    if (expanded_ejeFrenos_comb) {
                        opciones_ejeFrenos_comb.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_ejeFrenos_comb = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_ejeFrenos_comb == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Eje Motriz y frenos", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_direccion = !expanded_direccion
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Eje de dirección", fontSize = 20.sp)
                    }
                    if (expanded_direccion) {
                        opciones_direccion.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_direccion = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_direccion == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Eje de direccion", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_electrico_comb = !expanded_electrico_comb
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Sistema eléctrico", fontSize = 20.sp)
                    }
                    if (expanded_electrico_comb) {
                        opciones_electrico_comb.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_electrico_comb = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_electrico_comb == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Sistema electrico", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_chasisGuarda_comb =
                                !expanded_chasisGuarda_comb
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Chasis y guarda", fontSize = 20.sp)
                    }
                    if (expanded_chasisGuarda_comb) {
                        opciones_chasisGuarda_comb.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_chasisGuarda_comb = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_chasisGuarda_comb == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Chasis y guarda", opcion, myViewModel)
                                }
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            expanded_sistema_hidraulicos =
                                !expanded_sistema_hidraulicos
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .align(Alignment.CenterHorizontally)
                            .clip(RectangleShape)
                    ) {
                        Text("Sistema Hidraulico", fontSize = 20.sp)
                    }
                    if (expanded_sistema_hidraulicos) {
                        opciones_sistema_hidraulicos.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_sistema_hidraulicos = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_sistema_hidraulicos == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Sistema hidraulico", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_transmision = !expanded_transmision
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Transmisión", fontSize = 20.sp)
                    }
                    if (expanded_transmision) {
                        opciones_transmision.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_transmision = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_transmision == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Transmision", opcion, myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_ejeFrenos = !expanded_ejeFrenos
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Eje Motriz y Frenos", fontSize = 20.sp)
                    }
                    if (expanded_ejeFrenos) {
                        opciones_ejeFrenos.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_ejeFrenos = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_ejeFrenos == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable(
                                        "Eje motriz y frenos",
                                        opcion,
                                        myViewModel = myViewModel
                                    )
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_cargador = !expanded_cargador
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Cargador", fontSize = 20.sp)
                    }
                    if (expanded_cargador) {
                        opciones_cargador.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_cargador = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_cargador == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Cargador", opcion, myViewModel = myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_bateria = !expanded_bateria
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Batería", fontSize = 20.sp)
                    }
                    if (expanded_bateria) {
                        opciones_bateria.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_bateria = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_bateria == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable("Bateria", opcion, myViewModel = myViewModel)
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_sistema_electrico =
                                !expanded_sistema_electrico
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Sistema eléctrico", fontSize = 20.sp)
                    }
                    if (expanded_sistema_electrico) {
                        opciones_sistema_electrico.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_sistema_electrico = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_sistema_electrico == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable(
                                        "Sistema electrico",
                                        opcion,
                                        myViewModel = myViewModel
                                    )
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            expanded_chasis_guarda = !expanded_chasis_guarda
                            expandedButtonTable = false
                        },
                        modifier = Modifier
                            .padding(top = 37.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Chasis y guarda", fontSize = 20.sp)
                    }
                    if (expanded_chasis_guarda) {
                        opciones_chasis_guarda.forEach { opcion ->
                            Button(
                                onClick = {
                                    selectedOption_chasis_guarda = opcion
                                    expandedButtonTable = true
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RectangleShape
                                    )
                                    .clip(RectangleShape)
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(opcion, fontSize = 20.sp)
                            }
                            if (selectedOption_chasis_guarda == opcion) {
                                if (expandedButtonTable) {
                                    ButtonTable(
                                        "Chasis y guarda",
                                        opcion,
                                        myViewModel = myViewModel
                                    )
                                }
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        expanded_mastil = !expanded_mastil
                        expandedButtonTable = false
                    },
                    modifier = Modifier
                        .padding(top = 37.dp)
                        .width(250.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RectangleShape
                        )
                        .clip(RectangleShape)
                ) {
                    Text("Mastil", fontSize = 20.sp)
                }
                if (expanded_mastil) {
                    opciones_mastil.forEach { opcion ->
                        Button(
                            onClick = {
                                selectedOption_mastil = opcion
                                expandedButtonTable = true
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RectangleShape
                                )
                                .clip(RectangleShape)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(opcion, fontSize = 20.sp)
                        }
                        if (selectedOption_mastil == opcion) {
                            if (expandedButtonTable) {
                                ButtonTable("Mastil", opcion, myViewModel = myViewModel)
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        expanded_llantas = !expanded_llantas
                        expandedButtonTable = false
                    },
                    modifier = Modifier
                        .padding(top = 37.dp)
                        .width(250.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RectangleShape
                        )
                        .clip(RectangleShape)
                ) {
                    Text("Llantas", fontSize = 20.sp)
                }
                if (expanded_llantas) {
                    opciones_llantas.forEach { opcion ->
                        Button(
                            onClick = {
                                selectedOption_llantas = opcion
                                expandedButtonTable = true
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    RectangleShape
                                )
                                .clip(RectangleShape)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(opcion, fontSize = 20.sp)
                        }
                        if (selectedOption_llantas == opcion) {
                            if (expandedButtonTable) {
                                ButtonTable("Llantas", opcion, myViewModel = myViewModel)
                            }
                        }
                    }
                }
                val context = LocalContext.current

                Column(Modifier.fillMaxWidth()) {
                    var fileName by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = fileName,
                        onValueChange = { fileName = it },
                        label = { Text("Nombre del archivo") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 32.dp)
                    )

                    Button(
                        onClick = {
                            if (fileName.isNotEmpty()) {
                                val progressText = myViewModel.mensajeConcatenado.value ?: ""
                                saveProgress(context, fileName, progressText)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Guardar Progreso", fontSize = 20.sp)
                    }

                    Button(
                        onClick = {
                            if (fileName.isNotEmpty()) {
                                val loadedProgress = loadProgress(context, fileName)
                                if (loadedProgress != null) {
                                    myViewModel.addMessage(loadedProgress)
                                    showToast(context, loadedProgress)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Recuperar Progreso", fontSize = 20.sp)
                    }
                    Button(
                        onClick = {
                            if (fileName.isNotEmpty()) {
                                val loadedProgress = loadProgress(context, fileName)
                                if (loadedProgress != null && fileName.isNotEmpty()) {
                                    deleteProgress(context, fileName)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Eliminar Progreso", fontSize = 20.sp)
                    }


                    Button(
                        onClick = {
                            var modifiedMessage =
                                replaceDotsWithSpaces(myViewModel.mensajeConcatenado.value ?: "")
                            val subject =
                                "Checklist de inspeccion realizado por el tecnico $selectedOption_tecnicos al $selectedOption_equipos de la marca $selectedOption_marcas y modelo $model con numero de serie $serialNumber y horometro registrado de $horometro"
                            sendEmail(
                                modifiedMessage,
                                context = context,
                                subject = subject,
                                recipient = "loresendiz1993@gmail.com"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                            .width(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RectangleShape
                            )
                            .clip(RectangleShape)
                    ) {
                        Text("Enviar", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonTable(parametro1: String, parametro2: String, myViewModel: MyViewModel) {
    var comentario by remember { mutableStateOf("") }
    var showComentario by remember { mutableStateOf(false) }
    val savedImagePaths = remember { mutableStateListOf<Uri>() }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                savedImagePaths.add(uri)
            }
        }
    )

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    val mensaje = "$parametro1. $parametro2. Ok.\n"
                    myViewModel.addMessage(mensaje)
                    showToast(context, "Has seleccionado Ok")
                    comentario = ""
                    showComentario = true
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(text = "Ok")
            }
            Button(
                onClick = {
                    val mensaje = "$parametro1. $parametro2. Reparar.\n"
                    myViewModel.addMessage(mensaje)
                    showToast(context, "Has seleccionado Reparar")
                    comentario = ""
                    showComentario = true
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(text = "Reparar")
            }
            Button(
                onClick = {
                    val mensaje = "$parametro1. $parametro2. Faltante.\n"
                    myViewModel.addMessage(mensaje)
                    showToast(context, "Has seleccionado Faltante")
                    comentario = ""
                    showComentario = true
                    launcher.launch("image/*")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(text = "Faltante")
            }
        }
        if (showComentario) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = comentario,
                    onValueChange = { comentario = it },
                    label = {
                        Text(
                            "Comentario",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )
                Button(
                    onClick = {
                        val mensaje = "Comentario:$comentario\n\n"
                        myViewModel.addMessage(mensaje)
                        showToast(context, "Comentario guardado: $comentario.")
                        comentario = ""
                        showComentario = false

                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RectangleShape
                        )
                        .clip(RectangleShape)
                ) {
                    Text("Guardar")
                }
            }

        }
    }

}
fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
fun replaceDotsWithSpaces(input: String): String {
    return input.replace(".", "   ")
}
fun sendEmail(message: String?, context: Context, subject: String, recipient: String) {
    val body = "$message"
    val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
    }
    try {
        context.startActivity(Intent.createChooser(intent, "Enviar correo electrónico"))
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "No se encontró ninguna aplicación de correo electrónico.",
            Toast.LENGTH_SHORT
        ).show()
    }
}
fun saveProgress(context: Context, fileName: String, progressText: String) {
    val file = File(context.filesDir, "$fileName.txt")
    try {
        val writer = FileWriter(file)
        writer.write(progressText)
        writer.close()
        Toast.makeText(context, "Progreso guardado correctamente", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error al guardar el progreso", Toast.LENGTH_SHORT).show()
    }
}
fun loadProgress(context: Context, fileName: String): String? {
    val file = File(context.filesDir, "$fileName.txt")
    if (file.exists()) {
        try {
            val reader = FileReader(file)
            val buffer = CharArray(file.length().toInt())
            reader.read(buffer)
            val progressText = String(buffer)
            reader.close()
            return progressText
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error al cargar el progreso", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show()
    }
    return null
}
fun deleteProgress(context: Context, fileName: String) {
    val file = File(context.filesDir, "$fileName.txt")
    if (file.exists()) {
        file.delete()
        Toast.makeText(context, "Progreso eliminado correctamente", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_SHORT).show()
    }
}


