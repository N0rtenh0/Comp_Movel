package pt.umaia.tumapp
import pt.umaia.tumapp.ui.theme.TUMAPPTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUMAPPTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ProgramaPrincipal()

                }
            }
        }
    }
}


@Composable
fun ProgramaPrincipal() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                appItems = Destino.toList
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavigation(navController = navController)
            }
        }
    )
}



@Composable
fun AppNavigation(navController: NavHostController) {
    // ECRA 01 e ECRA 02
    val User = rememberSaveable { mutableStateOf("") }
    val Password = rememberSaveable { mutableStateOf("") }
    var listU = rememberSaveable { mutableStateOf("") }
    val opcao = rememberSaveable { mutableStateOf("Option 1") }
    val option1Text = rememberSaveable { mutableStateOf("Caloiro") }
    val option2Text = rememberSaveable { mutableStateOf("Proto-Caloiro") }

    // ECRA 04
    val Dia = rememberSaveable { mutableStateOf("") }
    val Local = rememberSaveable { mutableStateOf("") }
    val Op1 = rememberSaveable { mutableStateOf("Option 1") }
    val op1Text = rememberSaveable { mutableStateOf("Pago") }
    val op2Text = rememberSaveable { mutableStateOf("Não Pago") }
    var listA = rememberSaveable { mutableStateOf("") }

    NavHost(navController, startDestination = Destino.Ecra01.route) {
        composable(Destino.Ecra01.route) {
            Ecra01(
                navController = navController,
                oCampo1 = User,
                oCampo2 = Password,
                onClick = { value1, value2 ->
                    listU.value += "\n Nome: ${User.value}, Password: ${Password.value}"
                }
            )
        }
        composable(Destino.Ecra02.route) {
            Ecra02(
                navController = navController,
                oCampo1 = User,
                oCampo2 = Password,
                selectedOption = opcao,
                option1Text = option1Text,
                option2Text = option2Text,
                onClick = { value1, value2, opcao ->
                    listU.value += "\n Nome: ${User.value}, Password: ${Password.value}, Opção: ${opcao}"
                }
            )
        }

        composable(Destino.Ecra03.route) {
            // Pass selected option to Ecra02
            Ecra03(listA)
        }


        composable(Destino.Ecra04.route) {
            Ecra04(
                navController = navController,
                Dias = Dia,
                Local = Local,
                selectedOption = Op1,
                option1Text = op1Text,
                option2Text = op2Text,
                onClick = { value1, value2 , value3->
                    listA.value += "\n Dia: ${Dia.value}, Local: ${Local.value}, Opção: ${Op1.value}"
                }
            )
        }



    }
}

@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<Destino>) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.Cyan_A400),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        appItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color.Black else Color.Black.copy(.4F)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) Color.Black else Color.Black.copy(.4F)
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}