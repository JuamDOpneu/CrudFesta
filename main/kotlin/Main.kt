fun main() {
    val convidados = mutableListOf<String>()
    var opcao: Int

    do {
        println("\n=== MENU CRUD FESTA ===")
        println("1. Cadastrar")
        println("2. Listar")
        println("3. Editar")
        println("4. Excluir")
        println("5. Buscar")
        println("6. Sair")
        print("Escolha uma opção: ")

        opcao = readLine()?.toIntOrNull() ?: 0

        when (opcao) {
            1 -> cadastrar(convidados)
            2 -> listar(convidados)
            3 -> editar(convidados)
            4 -> excluir(convidados)
            5 -> buscar(convidados)
            6 -> println("Saindo do sistema...")
            else -> println("Opção inválida! Tente novamente.")
        }
    } while (opcao != 6)
}

//Validação para aceitar apenas letras no nome
fun cadastrar(convidados: MutableList<String>) {
    println("\n=== CADASTRAR CONVIDADO ===")

    var nome: String
    do {
        print("Digite o nome do convidado (apenas letras): ")
        nome = readLine()?.trim() ?: ""
    } while (!nome.matches(Regex("^[\\p{L} ]+$")))

    convidados.add(nome.uppercase())
    println("Cadastro realizado com sucesso!")
}

fun listar(convidados: List<String>) {
    println("\n=== LISTA DE CONVIDADOS ===")
    if (convidados.isEmpty()) {
        println("Nenhum convidado cadastrado.")
    } else {
        convidados.forEachIndexed { index, nome ->
            println("${index + 1}. $nome")
        }
    }
}

//Validações para edição
fun editar(convidados: MutableList<String>) {
    if (convidados.isEmpty()) {
        println("Não há convidados cadastrados!")
        return
    }

    listar(convidados)

    // Valida posição numérica
    var posicao: Int
    do {
        print("Digite o número do convidado que deseja editar: ")
        posicao = readLine()?.toIntOrNull() ?: 0
    } while (posicao !in 1..convidados.size)

    // Valida novo nome (apenas letras)
    var novoNome: String
    do {
        print("Digite o novo nome (apenas letras): ")
        novoNome = readLine()?.trim() ?: ""
    } while (!novoNome.matches(Regex("^[\\p{L} ]+$")))

    // Valida resposta S/N em maiúsculo
    var resposta: String
    do {
        print("Confirmar alteração para ${novoNome.uppercase()}? (S/N): ")
        resposta = readLine()?.uppercase() ?: ""
    } while (resposta !in listOf("S", "N"))

    if (resposta == "S") {
        convidados[posicao - 1] = novoNome.uppercase()
        println("Edição realizada com sucesso!")
    } else {
        println("Edição cancelada!")
    }
}

// Validação para posição numérica na exclusão
fun excluir(convidados: MutableList<String>) {
    if (convidados.isEmpty()) {
        println("Não há convidados cadastrados!")
        return
    }

    listar(convidados)

    var posicao: Int
    do {
        print("Digite o número do convidado que deseja excluir: ")
        posicao = readLine()?.toIntOrNull() ?: 0
    } while (posicao !in 1..convidados.size)

    val nome = convidados[posicao - 1]
    var resposta: String
    do {
        print("Tem certeza que deseja excluir $nome? (S/N): ")
        resposta = readLine()?.uppercase() ?: ""
    } while (resposta !in listOf("S", "N"))

    if (resposta == "S") {
        convidados.removeAt(posicao - 1)
        println("Convidado excluído com sucesso!")
    } else {
        println("Exclusão cancelada!")
    }
}

//Busca case-insensitive e validação alfabética
fun buscar(convidados: List<String>) {
    if (convidados.isEmpty()) {
        println("Não há convidados cadastrados!")
        return
    }

    var termoBusca: String
    do {
        print("Digite o nome para buscar (apenas letras): ")
        termoBusca = readLine()?.trim() ?: ""
    } while (!termoBusca.matches(Regex("^[\\p{L} ]*$")))

    val resultados = convidados.filter { it.contains(termoBusca, ignoreCase = true) }

    if (resultados.isEmpty()) {
        println("Nenhum convidado encontrado com '$termoBusca'")
    } else {
        println("\n=== RESULTADOS DA BUSCA ===")
        resultados.forEach { println(it) }
    }
}