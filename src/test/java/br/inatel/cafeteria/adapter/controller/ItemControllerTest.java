package br.inatel.cafeteria.adapter.controller;

import br.inatel.cafeteria.domain.entity.Item;
import br.inatel.cafeteria.domain.exception.ItemNotExistException;
import br.inatel.cafeteria.usecase.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    private final int ITEM_ID_CAFE = 1;
    private ItemService itemService = mock(ItemService.class);
    private ItemController itemController = new ItemController(itemService);

    @Test
    public void shouldPostItem() {
        Item itemWithId = getItem();
        itemWithId.setItemId(ITEM_ID_CAFE);

        when(itemService.saveItem(any(Item.class))).thenReturn(itemWithId);

        ResponseEntity<Item> response = itemController.postItem(getItem());
        ResponseEntity<Item> expectedResponseItem = getResponseItem();

        assertEquals(expectedResponseItem.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void shouldPutItem() {
        Item itemWithId = getItem();
        itemWithId.setItemId(ITEM_ID_CAFE);

        when(itemService.saveItem(any(Item.class))).thenReturn(itemWithId);

        ResponseEntity<Item> response = itemController.putItem(getItem(), ITEM_ID_CAFE);
        ResponseEntity<Item> expectedResponseItem = getResponseItem();

        assertEquals(expectedResponseItem.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void shouldDeleteItem() {
        Item itemWithId = getItem();
        itemWithId.setItemId(ITEM_ID_CAFE);

        when(itemService.deleteItem(anyInt())).thenReturn(itemWithId);

        ResponseEntity<Item> response = itemController.deleteItem(ITEM_ID_CAFE);
        ResponseEntity<Item> expectedResponseItem = getResponseItem();

        assertEquals(expectedResponseItem.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void shouldListAllItems() {
        when(itemService.listAllItems()).thenReturn(getItemList());

        ResponseEntity<List<Item>> responseList = itemController.getItems();
        ResponseEntity<List<Item>> expectedResponse = getResponseList();

        assertEquals(expectedResponse.getStatusCode(), responseList.getStatusCode());
        assertEquals(expectedResponse.getBody().size(), responseList.getBody().size());
    }

    @Test
    public void shouldListByCategory(){
        when(itemService.listItemsByCategory(anyString())).thenReturn(getItemList());

        ResponseEntity<List<Item>> responseList = itemController.getItemsByCategory("Bebida quente");
        ResponseEntity<List<Item>> expectedResponse = getResponseList();

        assertEquals(expectedResponse.getStatusCode(), responseList.getStatusCode());
        assertEquals(expectedResponse.getBody().size(), responseList.getBody().size());
    }

    @Test
    public void shouldThrowExceptionForInvalidItem(){

        when(itemService.saveItem(any(Item.class))).thenThrow(ItemNotExistException.class);

        assertThrows(
                ItemNotExistException.class,
                () -> itemController.postItem(getItem())
        );
    }

    private Item getItem() {
        Item item = new Item();
        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        return item;
    }

    private Item getCapuccino() {
        Item item = new Item();
        item.setItemId(2);
        item.setName("Capuccino");
        item.setPrice(5F);
        item.setDescription("Capuccino 250ml");
        item.setCategory("Bebida quente");

        return item;
    }

    private ResponseEntity<Item> getResponseItem() {
        Item item = getItem();
        item.setItemId(1);
        return ResponseEntity.ok(item);
    }

    private List<Item> getItemList() {
        List<Item> list = new ArrayList<>();
        list.add(getItem());
        list.get(0).setItemId(ITEM_ID_CAFE);
        list.add(getCapuccino());
        return list;
    }

    private ResponseEntity<List<Item>> getResponseList() {
        return ResponseEntity.ok(getItemList());
    }

    private Item getInvalidItem(){
        return new Item();
    }
}