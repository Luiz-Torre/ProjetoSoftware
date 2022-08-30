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
		// DAO é utilizado para manter um padrão de Design de Desenvolvimento
		ShoppingDAO shoppingDAO = FabricaDeDAOs.getDAO(ShoppingDAO.class);

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um Shopping");
			System.out.println("2. Alterar dados de um Shopping");
			System.out.println("3. Remover um shopping");
			System.out.println("4. Listar todos os shoppings");
			System.out.println("5. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do Shopping: ");
				bairro = Console.readLine('\n' + "Informe o nome do bairro do Shopping: ");
				rua = Console.readLine('\n' + "Informe o nome da rua do Shopping: ");
				estado = Console.readLine("\nInforme o nome do estado do Shopping");

				umShopping = new Shopping(nome, bairro, rua, estado);

				shoppingDAO.inclui(umShopping);

				System.out.println('\n' + "Shopping número " + umShopping.getId() + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do shopping que você deseja alterar: ");

				try {
					umShopping = shoppingDAO.recuperaShopping(resposta);
				} catch (ShoppingNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umShopping.getId() + "    Nome = " + umShopping.getNome()
						+ "    Rua = " + umShopping.getRua() + "    Bairro = " + umShopping.getBairro()
						+ "    Estado = " + umShopping.getEstado() + "    Versão = " + umShopping.getVersao()

				);

				System.out.println("O que você deseja alterar?");
				System.out.println("1. Nome");
				System.out.println("2. Rua");
				System.out.println("3. Bairro");

				System.out.println("4. Estado");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 4:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");

					umShopping.setNome(novoNome);

					// Tentativa de Alterar
					try {
						shoppingDAO.altera(umShopping);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					}
					// Captura do Erro caso não seja encontrado
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

						System.out.println('\n' + "Alteração da rua feita com sucesso!");
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

						System.out.println('\n' + "Alteração do bairro feito com sucesso!");
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

						System.out.println('\n' + "Alteração do estado feito com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
					catch(VersaoObsoletaException e) {
						System.out.println('\n' + e.getMessage());

					}
					break;
				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do shopping que você deseja remover: ");

				try {
					umShopping = shoppingDAO.recuperaShopping(resposta);
				} catch (ShoppingNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umShopping.getId() + "    Nome = " + umShopping.getNome());

				String resp = Console.readLine('\n' + "Confirma a remoção do Shopping? Digite s para confirmar");

				if (resp.equals("s")) {
					try {
						shoppingDAO.exclui(umShopping.getId());
						System.out.println('\n' + "Shopping removido com sucesso!");
					} catch (ShoppingNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Shopping não removido.");
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
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
