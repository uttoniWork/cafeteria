package br.inatel.cafeteria.usecase.service;

import br.inatel.cafeteria.adapter.repository.ItemRepository;
import br.inatel.cafeteria.domain.entity.Item;
import br.inatel.cafeteria.domain.exception.ItemNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Integer itemId, Item newItem) {

        verifyItem(itemId);
        newItem.setItemId(itemId);

        return itemRepository.save(newItem);
    }

    public Item deleteItem(Integer itemId) {

        final Item item = verifyItem(itemId);
        itemRepository.delete(item);

        return item;
    }

    public List<Item> listAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> listItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    private Item verifyItem(Integer itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new ItemNotExistException("Item not found for id: " + itemId));
    }
}
