package capstone.cbcb.domain.demo;


import jakarta.persistence.*;

// 테스트용 엔티티입니다.

@Entity
public class temp2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int temp_id;

    @Column(nullable = false)
    private String name;
}
