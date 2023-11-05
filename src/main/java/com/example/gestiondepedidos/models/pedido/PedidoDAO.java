package com.example.gestiondepedidos.models.pedido;


import java.util.ArrayList;

public interface PedidoDAO {

    ArrayList<Pedido> loadById(Long idUsuario);

}
