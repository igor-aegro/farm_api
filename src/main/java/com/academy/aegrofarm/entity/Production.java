package com.academy.aegrofarm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "productions")
public class Production {

    @Id
    private String id;

    private BigDecimal production;

    @Override
    public String toString() {
        return "Production{" +
                "id='" + id + '\'' +
                ", production=" + production +
                '}';
    }
}
