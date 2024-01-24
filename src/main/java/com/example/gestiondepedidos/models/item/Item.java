package com.example.gestiondepedidos.models.item;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private Long codigo;
    private int cantidad;
    private String nombre_producto;
}

