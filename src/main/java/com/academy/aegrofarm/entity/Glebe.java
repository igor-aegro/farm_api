package com.academy.aegrofarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "glebes")
public class Glebe {

    @Id
    private String id;

    private String name;

    private BigDecimal area;

    private List<BigDecimal> production;

    private BigDecimal productivity;

    @Override
    public String toString() {
        return "Glebe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", production=" + production +
                ", productivity=" + productivity +
                '}';
    }
}
