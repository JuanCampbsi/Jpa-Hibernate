package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	/*
	 * Inserindo os dados no banco
	 */

	@Test
	public void testeHibernateUtil() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setLogin("coringa");
		pessoa.setNome("DANIELFERREIRA");
		pessoa.setSenha("123456");
		pessoa.setEmail("SASASASA@outlook.com");
		pessoa.setSobrenome("SILVA");
		pessoa.setIdade((long) 24);

		daoGeneric.salvar(pessoa);

	}
	// Método de pesquisa no banco pelo Id

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);

	}

	/*
	 * Outro método de pesquisa no banco pelo Id
	 */
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		System.out.println(pessoa);

	}

	/*
	 * Método de pesquisa e atualização
	 */

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		pessoa.setEmail("juliaambrozino@outlook.com");
		pessoa.setNome("julia");
		pessoa.setSobrenome("Ambrozino");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	/*
	 * Método deletar
	 */
	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);

		daoGeneric.deletarPoId(pessoa);

	}

	/*
	 * Método consultar todos
	 */

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> List = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : List) {
			System.out.println("------------------------------------");
			System.out.println(usuarioPessoa);
			System.out.println("------------------------------------");
		}

	}

	/*
	 * Método queryes específicas
	 */

	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.geEntityManager()
				.createQuery("from UsuarioPessoa where nome = 'Campos'")
				.getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}

	/*
	 * Outro método de pesquisa no banco pelo Id
	 */
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.geEntityManager()
				.createQuery("from UsuarioPessoa order by id")// Pode ordenar
																// por Id ou por
																// nome
				.setMaxResults(3).getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}

	/*
	 * Foi passado parâmetros e utilizados Queryes adicionais
	 */
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.geEntityManager()
				.createQuery(
						"from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "julia")
				.setParameter("sobrenome", "ambrozino")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);

		}

	}

	/*
	 * Soma das idades
	 */
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.geEntityManager()
				.createQuery("select sum(u.idade) from UsuarioPessoa u")
				.getSingleResult();
		System.out.println("Soma de todas as idades é --> " + somaIdade);
		
	}

	/*
	 * Buscando todos na lista através do NamedQuery
	 */

	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.geEntityManager()
				.createNamedQuery("UsuarioPessoa.todos").getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	/*
	 * Buscando APENAS UM NOME na lista através do NamedQuery
	 */

	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.geEntityManager()
				.createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "DANIELFERREIRA")
				.getResultList();
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	/*
	 * Gravando dados na classe Telefone que foi atrelada ao Usuario Pessoa
	 */

	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(35L,
				UsuarioPessoa.class);
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("665656565");
		telefoneUser.setUsuarioPessoa(pessoa);

		daoGeneric.salvar(telefoneUser);
	}
	/*
	 * Exibindo os dados da classe Telefone que foi atrelada ao Usuario Pessoa
	 */
	@Test
	public void testeConsultaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(35L,
				UsuarioPessoa.class);
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("---------------------------");

		}

	}

}
