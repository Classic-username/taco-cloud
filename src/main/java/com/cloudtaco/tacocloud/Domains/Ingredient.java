package com.cloudtaco.tacocloud.Domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient implements Persistable<String> {
    
    @Id
    private @With final String id;

    private @With final String name;

    private @With final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    // @Override
    // public boolean isNew() {
    //     throw new UnsupportedOperationException("Unimplemented method 'isNew'");
    // }
    //This override is auto generated to get past YET MORE THINGS THIS BOOK DOES NOT EXPLAIN OR SHOW
    //The book did not show this (as of page 83) but its implementation of this method is:
    @Override
    public boolean isNew() {
        return true;
    }
}