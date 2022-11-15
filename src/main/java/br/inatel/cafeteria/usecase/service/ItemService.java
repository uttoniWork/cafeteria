package br.inatel.cafeteria.usecase.service;

import br.inatel.cafeteria.adapter.dto.ItemRequest;
import br.inatel.cafeteria.adapter.repository.ItemRepository;
import br.inatel.cafeteria.domain.entity.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(ItemRequest itemRequest){

        return null;
    }

    public Item updateItem(ItemRequest itemRequest){

        return null;
    }

    public Item deleteItem(ItemRequest itemRequest){

        return null;
    }
}
