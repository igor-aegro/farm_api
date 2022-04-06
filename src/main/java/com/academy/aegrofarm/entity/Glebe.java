package com.academy.aegrofarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Glebe {

    private String id;

    private String name;

    private BigDecimal area;

    private List<BigDecimal> production;

    @Override
    public String toString() {
        return "Glebe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", production=" + production +
                '}';
    }
}
