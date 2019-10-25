package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<T> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	/*
	 * Relacionado ao método salvar
	 */
	public void salvar(T entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}

	
	/*
	 * Relacionado ao método de pesquisa no banco pelo Id
	 */
	public T pesquisar(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		T t = (T) entityManager.find(entidade.getClass(), id);
		return t;
	}

	/*
	 * Relacionado outra forma de pesquisar no banco pelo Id
	 */
	public T pesquisar(Long id, Class<T> entidade) {

		T t = (T) entityManager.find(entidade, id);
		return t;
	}

	/*
	 * Relacionado ao método de salvar ou atualizar
	 */
	public T updateMerge(T entidade) { // Salva ou atualiza
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		T entidadeSalva = entityManager.merge(entidade);
		transaction.commit();

		return entidadeSalva;
	}

	public void deletarPoId(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.createNativeQuery("delete from "
				+ entidade.getClass().getSimpleName().toLowerCase()
				+ " where id =" + id).executeUpdate(); // faz delete
		transaction.commit();// grava alteração no banco
	}

	public List<T> listar(Class<T> entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<T> lista = entityManager.createQuery("from " + entidade.getName())
				.getResultList();

		transaction.commit();
		return lista;
	}

	public EntityManager geEntityManager() {
		return entityManager;
	}

}
