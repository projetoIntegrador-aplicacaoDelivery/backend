package com.generation.AplicacaoDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.AplicacaoDelivery.model.Produto;

@Repository
	public interface ProdutoRepository extends JpaRepository <Produto, Long>{


}
