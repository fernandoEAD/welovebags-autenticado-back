package com.welovebags.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.welovebags.dtos.ProdutoDTO;
import com.welovebags.entities.Categoria;
import com.welovebags.entities.Produto;
import com.welovebags.repositories.ProdutoRepository;
import com.welovebags.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Produto findById(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Produto> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return produtoRepository.findAllByCategoria(id_cat);
    }

    public Produto create(Integer id_cat, Produto obj) {
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return produtoRepository.save(obj);
    }

    public Produto update(Integer id, Produto obj) {
        Produto newObj = findById(id);
        updateData(newObj, obj);
        return produtoRepository.save(newObj);
    }

    private void updateData(Produto newObj, Produto obj) {
        newObj.setTitulo(obj.getTitulo());
        newObj.setMarca(obj.getMarca());
        newObj.setTexto(obj.getTexto());
    }

    public void delete(Integer id) {
        Produto obj = findById(id);
        try {
            produtoRepository.delete(obj);
        } catch (DataIntegrityViolationException e) {
            throw new com.welovebags.service.exceptions.DataIntegrityViolationException(
                    "Produto não pode ser deletada! Possui produtos associados");
        }
    }
}
