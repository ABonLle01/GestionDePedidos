package com.example.gestiondepedidos.models.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;
    private Long codigo;
    private String fecha;
    private int total;
}
