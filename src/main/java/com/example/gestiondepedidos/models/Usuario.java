package com.example.gestiondepedidos.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String nombre;
    private String email;
    private String password;
}
