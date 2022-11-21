package br.inatel.cafeteria.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private Integer itemId;
    @Column(name = "name")
    @NotBlank(message = "Name é um campo obrigatório")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Price é um campo obrigatório")
    @Min(value = 0, message = "Price deve ser um valor positivo maior ou igual a 0")
    private Float price;

    @Column(name = "description")
    @NotBlank(message = "Description é um campo obrigatório")
    private String description;

    @Column(name = "category")
    @NotBlank(message = "Category é um campo obrigatório")
    private String category;

    public Item() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
