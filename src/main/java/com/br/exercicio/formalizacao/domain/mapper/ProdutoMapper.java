package com.br.exercicio.formalizacao.domain.mapper;

import com.br.exercicio.formalizacao.domain.dto.ProdutoDTO;
import com.br.exercicio.formalizacao.domain.entity.ProdutoEntity;
import com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum;
import com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(target="tipoProduto", expression = "java(produtoDTO.getTipoProduto().getDescription())")
    @Mapping(target="statusSolicitacao", expression = "java(produtoDTO.getStatusSolicitacao().getDescription())")
    ProdutoEntity toProdutoEntity(ProdutoDTO produtoDTO);

    @Mapping(target="tipoProduto", expression = "java(com.br.exercicio.formalizacao.domain.enums.TipoProdutoEnum.from(produto.getTipoProduto()))")
    @Mapping(target="statusSolicitacao", expression = "java(com.br.exercicio.formalizacao.domain.enums.StatusSolicitacaoEnum.from(produto.getStatusSolicitacao()))")
    ProdutoDTO toProdutoDto(ProdutoEntity produto);

    List<ProdutoEntity> from(List<ProdutoDTO> produtos);

    List<ProdutoDTO> fromProdutoDto(List<ProdutoEntity> produtos);
}
