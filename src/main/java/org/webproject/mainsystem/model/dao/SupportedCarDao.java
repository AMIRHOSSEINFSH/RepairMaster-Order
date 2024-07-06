package org.webproject.mainsystem.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Supported_car")
public class SupportedCarDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(unique = true, nullable = false)
    public String brand;

    public String model;

    public String img_link;

}
