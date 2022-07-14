package com.spring.codeblog.model;
import javax.persistence.*;

//Isto é uma tabela no banco de dados.

@Entity
@Table(name="TB_POST")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

}
