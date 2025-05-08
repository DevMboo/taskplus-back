package com.taskplus_back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.taskplus_back.enums.ProfileUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile", columnDefinition = "ENUM('ADMINISTRADOR','COLABORADOR')")
    private ProfileUser perfil = ProfileUser.COLABORADOR;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;
}
