package com.serhii.springmvc.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity  implements Serializable {
    private String name;
}
