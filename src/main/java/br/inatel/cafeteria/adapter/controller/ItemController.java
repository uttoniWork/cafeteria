package br.inatel.cafeteria.adapter.controller;

import br.inatel.cafeteria.domain.entity.Item;
import br.inatel.cafeteria.usecase.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> postItem(@Valid @RequestBody Item item) {

        final Item savedItem = itemService.saveItem(item);

        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> putItem(@Valid @RequestBody Item item, @PathVariable Integer itemId){

        final Item updatedItem = itemService.updateItem(itemId, item);

        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Item> deleteItem(@PathVariable Integer itemId){

        final Item deletedItem = itemService.deleteItem(itemId);

        return ResponseEntity.ok(deletedItem);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItems(){

        final List<Item> items = itemService.listAllItems();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Item>> getItemsByCategory(@RequestParam String category){

        final List<Item> items = itemService.listItemsByCategory(category);

        return ResponseEntity.ok(items);
    }
}
