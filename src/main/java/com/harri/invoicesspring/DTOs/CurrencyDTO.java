package com.harri.invoicesspring.DTOs;

public class CurrencyDTO {


    private Integer id;

    private String name;

    private Character symbol;

    public CurrencyDTO(){

    }

    public CurrencyDTO(Integer id, String name, Character symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }
}
