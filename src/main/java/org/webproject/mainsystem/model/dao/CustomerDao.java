package org.webproject.mainsystem.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.webproject.mainsystem.model.dao.requestDao.RequestDao;
import java.util.Set;

@Entity(name = "Customer")
public class CustomerDao extends UserDao {

    @OneToMany(mappedBy = "customer_id")
    private Set<RequestDao> requestList;

}
