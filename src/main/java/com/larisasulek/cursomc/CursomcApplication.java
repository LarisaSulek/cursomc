package com.larisasulek.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.larisasulek.cursomc.domain.Categoria;
import com.larisasulek.cursomc.domain.Cidade;
import com.larisasulek.cursomc.domain.Cliente;
import com.larisasulek.cursomc.domain.Endereco;
import com.larisasulek.cursomc.domain.Estado;
import com.larisasulek.cursomc.domain.Produto;
import com.larisasulek.cursomc.domain.enums.TipoCliente;
import com.larisasulek.cursomc.repositories.CategoriaRepository;
import com.larisasulek.cursomc.repositories.CidadeRepository;
import com.larisasulek.cursomc.repositories.ClienteRepository;
import com.larisasulek.cursomc.repositories.EnderecoRepository;
import com.larisasulek.cursomc.repositories.EstadoRepository;
import com.larisasulek.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2)); //primeiro tem que salvar os estados
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "12345678910", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("999-9999", "333-3333")); 
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 302", "Jardim", "38078091", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos", "105", "sala 800", "Centro", "54390012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		}

}
