package objectaula;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtos.odb"); //abrir a conexao com o banco
        EntityManager em = emf.createEntityManager(); //gerenciar conexao com banco

        ProdutoService service = new ProdutoService(em);

        try {
            System.out.println("Cadastrando produtos...");
            service.criarProduto("Notebook", 3500.00);
            service.criarProduto("Mouse", 89.99);
            service.criarProduto("Teclado", 159.90);

            System.out.println("\nLista de produtos cadastrados:");
            List<Produto> produtos = service.listarProdutos();
            produtos.forEach(System.out::println);

            System.out.println("\nAtualizando preço do produto com ID 1...");
            Produto atualizado = service.atualizarPreco(1L, 2999.99);
            System.out.println("Atualizado: " + atualizado);

            System.out.println("\nRemovendo produto com ID 2...");
            service.removerProduto(2L);

            System.out.println("\nProdutos após remoção:");
            service.listarProdutos().forEach(System.out::println);

        } finally { //Se der erro no meio de uma transação, esse if garante que cancele tudo o que ficou pela metade no banco
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close(); //fechar
            emf.close();
            System.out.println("\nFinalizado.");
        }
    }
}
