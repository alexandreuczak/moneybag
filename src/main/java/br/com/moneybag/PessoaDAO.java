package br.com.moneybag;

import java.util.List;

import javax.persistence.TypedQuery;

public class PessoaDAO extends GenericDAO<Pessoa>{

	public PessoaDAO() {
		super(Pessoa.class, "moneybag");
	}

	public List<Pessoa> pesquisar(String nome){
		TypedQuery<Pessoa> query = getEntityManager().createQuery("select p from Pessoa p where p.name like :nome", Pessoa.class);
		query.setParameter("nome", nome.concat("%"));
		return query.getResultList();
	}

}
