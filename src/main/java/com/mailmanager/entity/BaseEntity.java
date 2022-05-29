package com.mailmanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    private Boolean active;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
}
