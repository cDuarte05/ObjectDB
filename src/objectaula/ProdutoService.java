package objectaula;

import javax.persistence.*;
import java.util.List;

public class ProdutoService {

    private final EntityManager em; 

    public ProdutoService(EntityManager em) {
        this.em = em;
    }

    // CREATE
    public Produto criarProduto(String nome, double preco) {
        Produto produto = new Produto(nome, preco);
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        return produto;
    }

    // READ (buscar todos)
    public List<Produto> listarProdutos() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class)
                 .getResultList();
    }

    // READ (buscar por ID)
    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    // UPDATE
    public Produto atualizarPreco(Long id, double novoPreco) {
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            produto.setPreco(novoPreco);
            em.getTransaction().commit();
        }
        return produto;
    }

    // DELETE
    public void removerProduto(Long id) {
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }
}
