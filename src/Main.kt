var convidado: Convidado = Convidado()

fun main() {
    menu()
}

private fun menu() {
    do {


        println("---Menu---")
        println("1- Cadastrar")
        println("2- Listar")
        println("3- Editar")
        println("4- Excluindo")
        println("0- Sair")

        val opcao = readln().toInt()//validade
        when (opcao) {
            1 -> {
                println("Cadastrado...")
                convidado = cadastrar()
            }

            2 -> {
                println("Listado...")
                listar(convidado)
            }

            3 -> println("Editando...")
            4 -> println("Excluindo...")
            0 -> println("Saindo...")
        }
    } while (true)
}

private fun cadastrar(): Convidado {
    //instacia


    print("qual seu Nome?")
    //val nome = readln()
    convidado.nome = readln()

    print("Qual vai ser o Presente?")
    //val presente = readln()
    convidado.presente = readln()

    print("Qual sua restriçao Alimentar?")
    //val alimento = readln()
    convidado.alimentar = readln()

    return convidado
}

private fun listar(convidado: Convidado) {
    print(
        "Nome: ${convidado.nome};\n Presente: " +
                "${convidado.presente};\n" +
                "Restricao ${convidado.alimentar};\n " +
                "Vai ir a Festa? ${convidado.precenca}"
    )

}

private fun editar() {
    println("o convidado vai? S/N")
    val resposta = readln()
    when (resposta) {
        "S" -> convidado.precenca = true
        "N" -> convidado.precenca = false
    }

}

private fun excluir() {
    convidado.nome = ""
    convidado.alimentar = ""
    convidado.presente = ""
    convidado.precenca = false
    println("Convidado Excluido")
}