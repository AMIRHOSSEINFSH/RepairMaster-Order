package org.webproject.mainsystem.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.webproject.mainsystem.model.dao.requestDao.RequestDao;
import org.webproject.mainsystem.model.enumData.WorkExperience;

import java.util.Set;

@Getter
@Entity(name = "RepairMan")
public class RepairManDao extends UserDao {

    @OneToMany
    private Set<SkillDao> skillList;

    @OneToMany
    private Set<SupportedCarDao> supportedCarList;

    @OneToMany(mappedBy = "repairMan_id")
    private Set<RequestDao> requestList;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkExperience workExperience;
}
