package com.example.gestiondepedidos.models.producto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long id;
    private String nombre;
    private int precio;
    private int cantidad_disponible;
}
