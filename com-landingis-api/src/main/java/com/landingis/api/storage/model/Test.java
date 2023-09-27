package com.landingis.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = TablePrefix.PREFIX_TABLE+"test")
@EntityListeners(AuditingEntityListener.class)
public class Test extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique =  true)
    private String name;
    @Column(name = "post_ode")
    private String postCode;
    @Column(name = "kind")
    private Integer kind;

}
