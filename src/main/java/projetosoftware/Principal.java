package projetosoftware;

import java.util.List;
import corejava.Console;

public class Principal {
	public static void main(String[] args) throws VersaoObsoletaException {
		String nome;
		String rua;
		String bairro;
		String estado;
		Shopping umShopping;
		// DAO � utilizado para manter um padr�o de Design de Desenvolvimento
		ShoppingDAO shoppingDAO = FabricaDeDAOs.getDAO(ShoppingDAO.class);

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que voc� deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um Shopping");
			System.out.println("2. Alterar dados de um Shopping");
			System.out.println("3. Remover um shopping");
			System.out.println("4. Listar todos os shoppings");
			System.out.println("5. Sair");

			int opcao = Console.readInt('\n' + "Digite um n�mero entre 1 e 5:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do Shopping: ");
				bairro = Console.readLine('\n' + "Informe o nome do bairro do Shopping: ");
				rua = Console.readLine('\n' + "Informe o nome da rua do Shopping: ");
				estado = Console.readLine("\nInforme o nome do estado do Shopping");

				umShopping = new Shopping(nome, bairro, rua, estado);

				shoppingDAO.inclui(umShopping);

				System.out.println('\n' + "Shopping n�mero " + umShopping.getId() + " inclu�do com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do shopping que voc� deseja alterar: ");

				try {
					umShopping = shoppingDAO.recuperaShopping(resposta);
				} catch (ShoppingNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umShopping.getId() + "    Nome = " + umShopping.getNome()
						+ "    Rua = " + umShopping.getRua() + "    Bairro = " + umShopping.getBairro()
						+ "    Estado = " + umShopping.getEstado() + "    Vers�o = " + umShopping.getVersao()

				);

				System.out.println("O que voc� deseja alterar?");
				System.out.println("1. Nome");
				System.out.println("2. Rua");
				System.out.println("3. Bairro");

				System.out.println("4. Estado");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um n�mero de 1 a 4:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");

					umShopping.setNome(novoNome);

					// Tentativa de Alterar
					try {
						shoppingDAO.altera(umShopping);

						System.out.println('\n' + "Altera��o de nome efetuada com sucesso!");
					}
					// Captura do Erro caso n�o seja encontrado
					catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					catch(VersaoObsoletaException e) {
						System.out.println('\n' + e.getMessage());

					}
					break;

				case 2:
					String novaRua = Console.readLine("Digite o nome da nova Rua ");
					umShopping.setRua(novaRua);
					try {
						shoppingDAO.altera(umShopping);

						System.out.println('\n' + "Altera��o da rua feita com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					catch(VersaoObsoletaException e) {
						System.out.println('\n' + e.getMessage());

					}
					break;

				case 3:
					String novoBairro = Console.readLine("Digite o nome do novo Bairro ");
					umShopping.setBairro(novoBairro);
					try {
						shoppingDAO.altera(umShopping);

						System.out.println('\n' + "Altera��o do bairro feito com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					catch(VersaoObsoletaException e) {
						System.out.println('\n' + e.getMessage());

					}
					break;
				case 4:
					String novoEstado = Console.readLine("Digite o nome do novo Estado ");
					umShopping.setRua(novoEstado);
					try {
						shoppingDAO.altera(umShopping);

						System.out.println('\n' + "Altera��o do estado feito com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					catch(VersaoObsoletaException e) {
						System.out.println('\n' + e.getMessage());

					}
					break;
				default:
					System.out.println('\n' + "Op��o inv�lida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o n�mero do shopping que voc� deseja remover: ");

				try {
					umShopping = shoppingDAO.recuperaShopping(resposta);
				} catch (ShoppingNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "N�mero = " + umShopping.getId() + "    Nome = " + umShopping.getNome());

				String resp = Console.readLine('\n' + "Confirma a remo��o do Shopping? Digite s para confirmar");

				if (resp.equals("s")) {
					try {
						shoppingDAO.exclui(umShopping.getId());
						System.out.println('\n' + "Shopping removido com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Shopping n�o removido.");
				}

				break;
			}

			case 4: {
				List<Shopping> shoppings = shoppingDAO.recuperaShoppings();

				for (Shopping shopping : shoppings) {
					System.out.println('\n' + "Id = " + shopping.getId() + "  Nome = " + shopping.getNome()
							+ "  Rua = " + shopping.getRua() + "  Bairro = "
							+ shopping.getBairro() + "  Estado = "
									+ shopping.getEstado()
							);
				}

				break;
			}

			case 5: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Op��o inv�lida!");
			}
		}
	}
}
