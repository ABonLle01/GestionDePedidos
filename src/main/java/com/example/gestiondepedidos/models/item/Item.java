package com.example.gestiondepedidos.models.item;

import com.example.gestiondepedidos.models.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private Long codigo;
    private int cantidad;
    private String nombre_producto;
}
