package br.com.moneybag;

import java.util.List;


public class OutPessoas {
	
	private List<PessoaDTO>pessoas;

	public OutPessoas() {
		this(null);
	}
	
	public OutPessoas(List<PessoaDTO> pessoas) {
		super();
		this.pessoas = pessoas;
	}

	public List<PessoaDTO> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaDTO> pessoas) {
		this.pessoas = pessoas;
	}
	
	
	
}
