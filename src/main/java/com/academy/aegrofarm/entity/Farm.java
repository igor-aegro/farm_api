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
@Document("farm")
public class Farm {

    @Id
    private String Id;

    private String name;

    private BigDecimal area;

    private List<Glebe> glebes;

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
