package com.welovebags.DBService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welovebags.entities.Categoria;
import com.welovebags.entities.Produto;
import com.welovebags.repositories.CategoriaRepository;
import com.welovebags.repositories.ProdutoRepository;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public void instanciaBaseDeDados() {
        Categoria cat1 = new Categoria(null, "Bolsa", "Bolsas Masculinas");
        Categoria cat2 = new Categoria(null, "Sapato", "Sapatos Masculinos");
        Categoria cat3 = new Categoria(null, "Calça", "Calças em geral");

        Produto p1 = new Produto(null, "Bolsa de couro", "Hurley", "Um produto com ótimos acabamentos", cat1);
        Produto p2 = new Produto(null, "Tênis", "Nike", "Nikeshox Original", cat2);
        Produto p3 = new Produto(null, "Sapatênis", "Polo", "Todo no veludo", cat2);
        Produto p4 = new Produto(null, "Calça Skinner", "Calvin Klein", "Calça estilo restart", cat3);
        Produto p5 = new Produto(null, "Calça Jeans", "Pulma", "Calça de verão", cat3);

        cat1.getProdutos().addAll(Arrays.asList(p1));
        cat2.getProdutos().addAll(Arrays.asList(p2, p3));
        cat3.getProdutos().addAll(Arrays.asList(p4, p5));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}
