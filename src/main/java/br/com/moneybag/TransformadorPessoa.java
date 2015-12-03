package br.com.moneybag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransformadorPessoa {

	public static PessoaDTO transformar(Pessoa pessoa){
		PessoaDTO dto = new PessoaDTO();
		dto.setAge(pessoa.getAge());
		dto.setBirthday(pessoa.getBirthday());
		dto.setId(pessoa.getId());
		dto.setName(pessoa.getName());
		return dto;
	}
	
	public static List<PessoaDTO> transformar(List<Pessoa> pessoas){
		List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();
		for(Pessoa p : pessoas){
			retorno.add(transformar(p));
		}
		return retorno;
	}
	
	public static Pessoa transformar(PessoaDTO dto){
		if(dto == null){
			return null;
		}
		
		Pessoa pessoa = new Pessoa();
		pessoa.setAge(dto.getAge());
		if(dto.getBirthday() == null){
			pessoa.setBirthday(new Date());
		}else{
			pessoa.setBirthday(dto.getBirthday());
		}
		pessoa.setId(dto.getId());
		pessoa.setName(dto.getName());
		return pessoa;
	}
	
}
