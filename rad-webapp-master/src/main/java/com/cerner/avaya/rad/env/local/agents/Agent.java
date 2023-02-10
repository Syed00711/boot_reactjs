package com.cerner.avaya.rad.env.local.agents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "username")
    public String userName;

    public String name;
    public String extension;
    public String state;
    public String reason;

    @Column(name = "statetime")
    public long stateTime;

    // TODO
    public String skills;
}