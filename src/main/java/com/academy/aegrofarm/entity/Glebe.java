package com.academy.aegrofarm.farm;

import java.math.BigDecimal;
import java.util.List;

public class Glebe {

    private String id;

    private String name;

    private BigDecimal area;

    private List<BigDecimal> production;

    public Glebe() {
    }

    public Glebe(String id, String name, BigDecimal area, List<BigDecimal> production) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.production = production;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<BigDecimal> getProduction() {
        return production;
    }

    public void setProduction(List<BigDecimal> production) {
        this.production = production;
    }

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
