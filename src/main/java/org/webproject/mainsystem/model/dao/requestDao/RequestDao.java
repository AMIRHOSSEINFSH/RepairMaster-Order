package org.webproject.mainsystem.model.dao.requestDao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webproject.mainsystem.model.dao.CustomerDao;
import org.webproject.mainsystem.model.dao.RepairManDao;
import org.webproject.mainsystem.model.dao.SupportedCarDao;
import org.webproject.mainsystem.model.enumData.RequestStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "Request")
public class RequestDao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID requestId;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerDao requester;

    @ManyToOne
    @JoinColumn(name = "repairMan_id",nullable = true)
    private RepairManDao repairMan;

    @OneToOne
    @Column(nullable = false)
    private SupportedCarDao car;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.ADMIN_PENDING;

}
