package org.webproject.mainsystem.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webproject.mainsystem.model.enumData.RequestStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Request")
public class RequestDao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID requestId;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    public CustomerDao requester;

    @ManyToOne
    @JoinColumn(name = "repairMan_id",nullable = true)
    public RepairManDao repairMan;

    @OneToOne
//    @Column(nullable = false)
    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    public SupportedCarDao car;

    public String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public RequestStatus status = RequestStatus.ADMIN_PENDING;

}
