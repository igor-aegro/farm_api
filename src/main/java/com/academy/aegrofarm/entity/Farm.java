package com.academy.aegrofarm.farm;

import java.math.BigDecimal;
import java.util.List;

public class Farm {

    private String Id;

    private String name;

    private BigDecimal area;

    private List<Glebe> glebes;

    public Farm(String id, String name, BigDecimal area, List<Glebe> glebes) {
        Id = id;
        this.name = name;
        this.area = area;
        this.glebes = glebes;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public List<Glebe> getGlebes() {
        return glebes;
    }

    public void setGlebes(List<Glebe> glebes) {
        this.glebes = glebes;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", glebes=" + glebes +
                '}';
    }
}
