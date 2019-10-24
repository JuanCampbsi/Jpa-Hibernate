package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;


public class TesteHibernate {
	/*
	 * Inserindo os dados no banco
	 * 
	 * @Test public void testeHibernateUtil() {
	 * 
	 * DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	 * 
	 * UsuarioPessoa pessoa = new UsuarioPessoa();
	 * 
	 * pessoa.setLogin("Teste.bsi"); pessoa.setNome("Juan");
	 * pessoa.setSenha("123456"); pessoa.setEmail("juancampos.bsi@outlook.com");
	 * pessoa.setSobrenome("Daniel");
	 * 
	 * daoGeneric.salvar(pessoa);
	 * 
	 * } /* Método de pesquisa no banco pelo Id
	 */
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
	 * Método de pesquisa
	 */

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		pessoa.setEmail("juliaambrozino@outlook.com");
		pessoa.setNome("julia");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);

		daoGeneric.deletarPoId(pessoa);

	}

}
