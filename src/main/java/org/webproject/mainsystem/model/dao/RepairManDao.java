package org.webproject.mainsystem.model.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.webproject.mainsystem.model.enumData.WorkExperience;

import java.util.Set;

@Setter
@Getter
@Entity(name = "RepairMan")
public class RepairManDao extends UserDao {

    @OneToMany
    public Set<SkillDao> skillList;

    @OneToMany
    public Set<SupportedCarDao> supportedCarList;

    @OneToMany(mappedBy = "repairMan")
    public Set<RequestDao> requestList;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public WorkExperience workExperience;
}
