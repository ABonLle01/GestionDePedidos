package com.example.gestiondepedidos.models.item;

import java.util.ArrayList;

public interface ItemDAO {

    ArrayList<Item> loadById(Long codigo);

}
