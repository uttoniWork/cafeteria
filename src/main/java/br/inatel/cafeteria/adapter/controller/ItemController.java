package br.inatel.cafeteria.adapter.controller;

import br.inatel.cafeteria.adapter.dto.ItemRequest;
import br.inatel.cafeteria.domain.entity.Item;
import br.inatel.cafeteria.usecase.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> postItem(@RequestBody ItemRequest itemRequest){



        return null;
    }
}
