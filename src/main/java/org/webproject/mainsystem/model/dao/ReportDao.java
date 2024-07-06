package org.webproject.mainsystem.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Report")
public class ReportDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    public CustomerDao reporter;

    @ManyToOne
    @JoinColumn(name = "repairMan_id",nullable = false)
    public RepairManDao repairMan;

    public String description;
}
