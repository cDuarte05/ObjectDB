package objectaula;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean menu = true;
        int opt = 0;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtos.odb"); //abrir a conexao com o banco de maneira local
        
//        Map<String, String> properties = new HashMap<String, String>();
//        properties.put ("javax.persistence.jdbc.user", "admin");
//        properties.put ("javax.persistence.jdbc.password", "admin");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:6136/produtos.odb", properties);
        
        EntityManager em = emf.createEntityManager(); //gerenciar conexao com banco

        ProdutoService service = new ProdutoService(em);

        try {
            do {
                opt = 0;
                
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Buscar Produto por ID");
                System.out.println("3 - Listar Produtos Cadastrados");
                System.out.println("4 - Atualizar Preco de Produto Cadastrado");
                System.out.println("5 - Excluir Produtos");
                System.out.println("6 - Sair");
                
                Scanner scan = new Scanner(System.in);
                
                try {
                    opt = scan.nextInt();
                } catch (Exception e) {
                    System.out.println ("Opcao Invalida: " + e);
                    e.printStackTrace();
                }
                
                switch (opt) {
                    case 1:
                        String nome = "";
                        double preco = 0;
                        try {
                            scan.nextLine();
                            nome = scan.nextLine();
                            preco = scan.nextDouble();
                            service.criarProduto(nome, preco); 
                        } catch (Exception e) {
                            System.out.println ("Entrada Invalida: " + e);
                            e.printStackTrace();
                        }                                           
                        break;
                        
                    case 2:
                        long id = 0;
                        Produto produto = null;
                        try {
                            id = scan.nextLong();
                            produto = service.buscarPorId(id);
                            if (produto == null) {
                                System.out.println("Sem registro.");
                            } else {
                                System.out.println(produto.toString());
                            }  
                        } catch (Exception e) {
                            System.out.println ("Entrada Invalida: " + e);
                            e.printStackTrace();
                        }  
                        break;
                        
                    case 3:
                        List<Produto> produtos = service.listarProdutos();
                        if (produtos.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado");
                        } else {
                            produtos.forEach(System.out::println);
                        }                       
                        break;
                        
                    case 4:
                        id = 0;
                        double novoPreco = 0;
                        try {
                            id = scan.nextLong();
                            novoPreco = scan.nextDouble();
                            service.atualizarPreco(id, novoPreco);
                        } catch (Exception e) {
                            System.out.println ("Entrada Invalida: " + e);
                            e.printStackTrace();
                        }                  
                        break;
                    
                    case 5:
                        id = 0;
                        try {
                            id = scan.nextLong();
                            service.removerProduto(id);
                        } catch (Exception e) {
                            System.out.println ("Entrada Invalida: " + e);
                            e.printStackTrace();
                        } 
                        break;
                        
                    case 6:
                        menu = false;
                        break;
                    
                    default:
                        System.out.println("Opcao Invalida");
                        break;
                }
            } while (menu);
        } catch (Exception e) {
            
            System.out.println ("Excecao de uso: " + e);
            e.printStackTrace();
            
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
