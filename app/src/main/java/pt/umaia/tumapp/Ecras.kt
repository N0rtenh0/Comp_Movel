package pt.umaia.tumapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast



@Composable
fun Ecra01(
    navController: NavController,  // Add NavController as a parameter
    oCampo1: MutableState<String>,
    oCampo2: MutableState<String>,
    onClick: (String, String) -> Unit
) {


    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "This is what less-abled people will hear when they tap the image. Please write a conscious description of your image",
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(id = R.string.ecra01),
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = oCampo1.value,
            onValueChange = { oCampo1.value = it },
            label = { Text("Insira o seu User") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = oCampo2.value,
            onValueChange = { oCampo2.value = it },
            label = { Text("Insira a sua Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Button(
            onClick = {
                if (oCampo1.value =="admin" && oCampo2.value =="admin" ){

                    // Navigate to the desired screen
                    navController.navigate("ecra03")
                } else {
                    // Show toast message
                    Toast.makeText(context, "Utilizador ou Password Errada", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "${oCampo1.value} , ${oCampo2.value}", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            navController.navigate("ecra02") // Navigate to Ecra02
        }) {
            Text("Register")
        }
    }

}





@Composable
fun Ecra02(
    navController: NavController,  // Add NavController as a parameter
    oCampo1: MutableState<String>,
    oCampo2: MutableState<String>,
    selectedOption: MutableState<String>,
    option1Text: MutableState<String>,  // New parameter for Option 1 label
    option2Text: MutableState<String>,  // New parameter for Option 2 label
    onClick: (String, String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp)
    ) {
        // Display the header text
        Text(
            text = stringResource(id = R.string.ecra02),
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )



        Spacer(modifier = Modifier.height(16.dp))

        // First Text Box
        TextField(
            value = oCampo1.value,
            onValueChange = { oCampo1.value = it },
            label = { Text("Insira o User") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Second Text Box
        TextField(
            value = oCampo2.value,
            onValueChange = { oCampo2.value = it },
            label = { Text("Insira a sua password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Radio Buttons for options with dynamic labels
        Text(text = "Tipo:")

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == "Option 1",
                onClick = { selectedOption.value = "Option 1" }
            )
            Text(text = option1Text.value)  // Use dynamic label for Option 1

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedOption.value == "Option 2",
                onClick = { selectedOption.value = "Option 2" }
            )
            Text(text = option2Text.value)  // Use dynamic label for Option 2
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button with onClick that passes the field values and selected option
        Button(onClick = { onClick(oCampo1.value, oCampo2.value, selectedOption.value)
            navController.navigate("ecra01")}) {
            Text("Registar")
        }

    }
}






@Composable
fun Ecra03(
    listA: MutableState<String>, // Only pass list as a parameter
//        option1Text: MutableState<String>,  // Parameter to hold Option 1 label
//        option2Text: MutableState<String> // Parameter to hold Option 2 label

) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.ecra03),
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list's value
        Text(
            text = listA.value,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        /*
                    // Text Box to update Option 1 label
                    TextField(
                        value = option1Text.value,
                        onValueChange = { option1Text.value = it },
                        label = { Text("Update Option 1 Label") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Text Box to update Option 2 label
                    TextField(
                        value = option2Text.value,
                        onValueChange = { option2Text.value = it },
                        label = { Text("Update Option 2 Label") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Text Box to update Option 3 label
        */
    }
}



@Composable
fun Ecra04(
    navController: NavController,
    Dias: MutableState<String>,
    Local: MutableState<String>,
    selectedOption: MutableState<String>,
    option1Text: MutableState<String>,  // Parameter to hold Option 1 label
    option2Text: MutableState<String>, // Parameter to hold Option 2 label
    onClick: (String, String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.ecra04),
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // First Text Box
        TextField(
            value = Dias.value,
            onValueChange = { Dias.value = it },
            label = { Text("Insira o Dia da Atuação") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Second Text Box
        TextField(
            value = Local.value,
            onValueChange = { Local.value = it },
            label = { Text("Insira o Local da Atuação") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Radio Buttons for options with dynamic labels
        Text(text = "Tipo de atuação:")

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == option1Text.value,
                onClick = { selectedOption.value = option1Text.value }
            )
            Text(text = option1Text.value)  // Use dynamic label for Option 1

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedOption.value == option2Text.value,
                onClick = { selectedOption.value = option2Text.value }
            )
            Text(text = option2Text.value)  // Use dynamic label for Option 2
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = { onClick(Dias.value, Local.value, selectedOption.value)
        }) {
            Text("Adicionar Evento")
        }

    }
}



@Composable
fun MeuRadioButton(
    selecionado: Boolean,
    resId: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        RadioButton(
            selected = selecionado,
            onClick = { onClick(resId) }
        )
        Text(
            text = stringResource(resId),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}





