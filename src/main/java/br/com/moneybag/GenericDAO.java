package br.com.moneybag;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO<T extends Persistable> {

	private EntityManager entityManager;
	
	private final Class<T> oClass;

	public Class<T> getObjectClass() {
		return this.oClass;
	}

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		if (entityManager == null)
			throw new IllegalStateException("EntityManager not found");

		return this.entityManager;
	}

	/*@SuppressWarnings("unchecked")
	public GenericDAO(String persistence) {
		this.oClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.setEntityManager(SessionFactory.getInstance(persistence).getEntityManagerFactory().createEntityManager());
	}*/
	
	@SuppressWarnings("unchecked")
	public GenericDAO(Class entityClass, String persistence){
		this.oClass = entityClass;
		this.setEntityManager(SessionFactory.getInstance(persistence).getEntityManagerFactory().createEntityManager());
	}

	public T atualizar(T object) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(object);
		getEntityManager().getTransaction().commit();
		return object;
	}

	public void excluir(T object) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(object);
		getEntityManager().getTransaction().commit();
	}

	public T pesquisar(int id) {
		return (T) getEntityManager().find(oClass, id);
	}

	public T salvar(T object) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(object);
		getEntityManager().getTransaction().commit();
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> pesquisar() {
		String sql = "select obj from " + oClass.getSimpleName() + " obj ";
		Query query = getEntityManager().createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> pesquisar(String sql, Map<String, Object> params) {
		Query query = getEntityManager().createQuery(sql);
		for (String chave : params.keySet()) {
			query.setParameter(chave, params.get(chave));
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> pesquisar(String sql, Map<String, Object> params,
			int maximo, int atual) {
		Query query = getEntityManager().createQuery(sql).setMaxResults(maximo)
				.setFirstResult(atual);

		for (String chave : params.keySet()) {
			query.setParameter(chave, params.get(chave));
		}
		return query.getResultList();
	}	

}
