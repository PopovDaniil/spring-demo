package com.server.demo.field;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.server.demo.table.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Field {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;

    }

    public String getName() {
        return name;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Table table;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Field other = (Field) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
