package org.webproject.mainsystem.model.dao;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public class UserDao {

    @Id
    public UUID id;

    public String email;

    public String firstname;

    public String lastname;

}
