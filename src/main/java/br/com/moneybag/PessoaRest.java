package br.com.moneybag;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pessoas")
public class PessoaRest {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/buscar/{id}")
	public PessoaDTO buscar(@PathParam("id") Integer id){				
		return TransformadorPessoa.transformar(new PessoaDAO().pesquisar(id));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/listar/{nome}")
	public Response listar(@PathParam("nome") String nome){				
		List<Pessoa> lista = new PessoaDAO().pesquisar(nome);
		return Response.ok().entity(new OutPessoas(TransformadorPessoa.transformar(lista))).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/listar")
	public Response listar(){
		List<Pessoa> lista = new PessoaDAO().pesquisar();
		return Response.ok().entity(new OutPessoas(TransformadorPessoa.transformar(lista))).build();
	}

	@POST
	@Path("/remover")
	@Consumes({MediaType.APPLICATION_JSON})
	public void remover(PessoaDTO pessoa){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa p = pessoaDAO.pesquisar(pessoa.getId());
		if(p != null){
			pessoaDAO.excluir(p);
		}
	}
	
	@POST
	@Path("/adicionar")
	@Consumes({MediaType.APPLICATION_JSON})
	public void adicionar(PessoaDTO pessoa){
		new PessoaDAO().salvar(TransformadorPessoa.transformar(pessoa));
	}
	
	@POST
	@Path("/atualizar")
	@Consumes({MediaType.APPLICATION_JSON})
	public void atualizar(PessoaDTO pessoa){
		new PessoaDAO().atualizar(TransformadorPessoa.transformar(pessoa));
	}
	
}
