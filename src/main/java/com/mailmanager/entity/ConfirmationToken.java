package com.mailmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ConfirmationToken extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    public ConfirmationToken(User user){
        this.token = UUID.randomUUID().toString();
        this.user = user;
        this.expiredDate = new Date(System.currentTimeMillis()+5*60*1000);
    }

}
