package az.nuranaibrahimova.springjwt.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column
    private String name;
}
