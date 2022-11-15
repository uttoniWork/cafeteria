package br.inatel.cafeteria.adapter.dto;

import br.inatel.cafeteria.domain.entity.Category;

import javax.persistence.Column;

public class ItemRequest {

    private Integer itemId;
    private String name;
    private Float price;
    private String description;
    private Category category;

    public ItemRequest() {
    }
}
