package org.webproject.mainsystem.model.dao;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public class UserDao {

    @Id
    protected UUID id;

    private String email;

    private String firstname;

    private String lastname;

}
