package com.generation.AplicacaoDelivery.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.AplicacaoDelivery.model.Categoria;
import com.generation.AplicacaoDelivery.model.Produto;
import com.generation.AplicacaoDelivery.repository.ProdutoRepository;

@Service
public class ProdutoService {

	Random random = new Random();

	@Autowired
	private ProdutoRepository produtoRepository;

	public Optional<Produto> recomendaProduto() {
		if (produtoRepository.count() > 0) {
			int id = random.nextInt((int) (produtoRepository.count() + 1));
			if (produtoRepository.existsById((long) id)) {
				return produtoRepository.findById((long) id);
			} else {
				while (produtoRepository.existsById((long) id) == false) {
					id += 1;
				}
				return produtoRepository.findById((long) id);
			}
		}
		return Optional.empty();

	}

	public Optional<Produto> recomendaProdutoPorCategoria(Categoria categoria) {

		if (categoria.getProdutos().isEmpty() != true) {
			Produto produto = categoria.getProdutos().get(random.nextInt(categoria.getProdutos().size()));

			return produtoRepository.findById(produto.getId());
		}

		return Optional.empty();
	}

}
