package com.example.gestiondepedidos.models.item;

import java.util.ArrayList;

public interface ItemDAO {

    Item load(Long id);
    ArrayList<Item> loadById(Long codigo);

}
