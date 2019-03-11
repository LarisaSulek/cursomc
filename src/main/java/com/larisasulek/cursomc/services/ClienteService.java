package com.larisasulek.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisasulek.cursomc.domain.Cliente;
import com.larisasulek.cursomc.repositories.ClienteRepository;
import com.larisasulek.cursomc.services.exception.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar (Integer id) throws ObjectNotFoundException {  //recebe o ID e retorna o cliente correspondente
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+", Tipo: " + Cliente.class.getName()));
	
}
}
