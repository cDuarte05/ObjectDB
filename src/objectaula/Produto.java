package objectaula;

import javax.persistence.*;

@Entity // Marca a classe como persistível pelo JPA
public class Produto {

    @Id // Define o campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id;

    private String nome;
    private double preco;

    public Produto() {} // Construtor padrão é obrigatório para JPA

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return String.format("Produto[ID = " + id + ", Nome = " + nome + ", Preco = " + preco + "]");
    }
}
