package org.webproject.mainsystem.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;


@Entity(name = "Customer")
public class CustomerDao extends UserDao {

    @OneToMany(mappedBy = "requester")
    public Set<RequestDao> requestList;

    @OneToMany(mappedBy = "reporter")
    public Set<ReportDao> reportList;
}
