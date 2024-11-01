package com.ben.modal;

import com.ben.domain.AccountStatus;
import com.ben.domain.USER_ROLE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean emailVerified=false;

    private String Password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne
    private Address pickUpAddress = new Address();

    private String GSTIN;

    private USER_ROLE role=USER_ROLE.ROLE_SELLER;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
