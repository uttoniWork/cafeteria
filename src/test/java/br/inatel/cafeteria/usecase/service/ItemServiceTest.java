package br.inatel.cafeteria.usecase.service;

import br.inatel.cafeteria.adapter.repository.ItemRepository;
import br.inatel.cafeteria.domain.entity.Item;
import br.inatel.cafeteria.domain.exception.ItemNotExistException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    ItemRepository itemRepository = mock(ItemRepository.class);
    ItemService itemService = new ItemService(itemRepository);

    @Test
    public void salvarItem(){
        when(itemRepository.save(any(Item.class))).thenReturn(getItem());
        Item item = new Item();

        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        Item itemTest = itemService.saveItem(item);

        assertEquals(1, itemTest.getItemId());
        assertEquals("Cafézinho",itemTest.getName());
        assertEquals(2.5F, itemTest.getPrice());
        assertEquals("Café preto", itemTest.getDescription());
        assertEquals("Bebida quente", itemTest.getCategory());
    }

    @Test
    public void atualizarItem(){
        when(itemRepository.save(any(Item.class))).thenReturn(getItem());
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(getItem()));
        Item item = new Item();

        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        Item itemTest = itemService.updateItem(1,item);

        assertEquals(2.5F, itemTest.getPrice());
    }

    @Test
    public void deletarItem(){
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(getItem()));
        doNothing().when(itemRepository).delete(any(Item.class));
        Item item = new Item();

        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        Item itemTest = itemService.deleteItem(1);

        assertEquals("Cafézinho", itemTest.getName());
        verify(itemRepository,times(1)).delete(any(Item.class));
    }

    @Test
    public void verificarTamanhoLista(){
        when(itemRepository.findAll()).thenReturn(getItemList());

        List<Item> listaTest = itemService.listAllItems();

        assertEquals(2, listaTest.size());
    }

    @Test
    public void verificarItem1Lista(){
        when(itemRepository.findAll()).thenReturn(getItemList());

        List<Item> listaTest = itemService.listAllItems();

        assertEquals("Cafézinho", listaTest.get(0).getName());
    }

    @Test
    public void verificarPaoLista(){
        when(itemRepository.findAll()).thenReturn(getItemList());

        List<Item> listaTest = itemService.listAllItems();

        assertEquals("Pao", listaTest.get(1).getName());
    }

    @Test
    public void verificarItemCategoria(){
        when(itemRepository.findByCategory(anyString())).thenReturn(getItemListSalgados());

        List<Item> listaTest = itemService.listItemsByCategory("Salgado");

        assertEquals("Pao", listaTest.get(0).getName());
    }

    @Test
    public void verificarSeNaoDeleta(){
        when(itemRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        assertThrows(
                ItemNotExistException.class,
                () -> itemService.deleteItem(1)
        );
    }

    @Test
    public void verificarseNaoAtualiza(){
        when(itemRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        Item item = new Item();

        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        assertThrows(
                ItemNotExistException.class,
                () -> itemService.updateItem(1,item)
        );
    }

    private Item getItem(){
        Item item = new Item();

        item.setItemId(1);
        item.setName("Cafézinho");
        item.setPrice(2.5F);
        item.setDescription("Café preto");
        item.setCategory("Bebida quente");

        return item;
    }

    private Item getPao(){
        Item item = new Item();

        item.setItemId(2);
        item.setName("Pao");
        item.setPrice(5F);
        item.setDescription("Pão de sal");
        item.setCategory("Salgado");

        return item;
    }

    private List<Item> getItemList(){
        List<Item> lista = new ArrayList<>();
        lista.add(getItem());
        lista.add(getPao());

        return lista;
    }

    private List<Item> getItemListSalgados(){
        List<Item> lista = new ArrayList<>();
        lista.add(getPao());

        return lista;
    }

}
