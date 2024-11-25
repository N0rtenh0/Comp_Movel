package pt.umaia.tumapp


sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Ecra01 : Destino(route = "ecra01", icon = R.drawable.login, title = "Login")
    object Ecra02 : Destino(route = "ecra02", icon = R.drawable.register, title = "Register")
    object Ecra03 : Destino(route = "ecra03", icon = R.drawable.lista, title = "Lista")
    object Ecra04 : Destino(route = "ecra04", icon = R.drawable.lista_add, title = "List Add")
    object Ecra05 : Destino(route = "ecra05", icon = R.drawable.edita, title = "Editar")
    companion object {
        val toList = listOf(Ecra01, Ecra02, Ecra03,Ecra04,Ecra05)
    }
}