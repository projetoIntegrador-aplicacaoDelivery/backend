package com.generation.AplicacaoDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.AplicacaoDelivery.model.Produto;

@Repository
	public interface ProdutoRepository extends JpaRepository <Produto, Long>{

	List<Produto> findAllByNomeProdutoContainingIgnoreCase(@Param("nomeProduto") String nomeProduto);


}
