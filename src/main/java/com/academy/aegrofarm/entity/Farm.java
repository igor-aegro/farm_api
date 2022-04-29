package com.academy.aegrofarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "farms")
public class Farm {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private List<Glebe> glebes;

    private BigDecimal productivity;

    @Override
    public String toString() {
        return "Farm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", glebes=" + glebes +
                ", productivity=" + productivity +
                '}';
    }

}
