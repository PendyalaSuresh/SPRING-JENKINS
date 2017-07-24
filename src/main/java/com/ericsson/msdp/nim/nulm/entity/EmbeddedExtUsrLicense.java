package com.ericsson.msdp.nim.nulm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbeddedExtUsrLicense implements Serializable { 
    @Column(name="RDB_NAME")
    private String rdb_name;
}