// O pacote pode ser diferente no seu projeto. Mantenha o seu.
package objectaula; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Point {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int x;
    private int y;

    // Construtor padrão público exigido pela JPA
    public Point() {
    }

    // Seu construtor para criar pontos
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public Long getId() { return this.id; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    @Override
    public String toString() {
        return String.format("Point[id=%d, x=%d, y=%d]", this.id, this.x, this.y);
    }
}