package com.example.gestiondepedidos.models.pedido;

import com.example.gestiondepedidos.models.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;
    private Long codigo;
    private String fecha;
    private int total;
}
