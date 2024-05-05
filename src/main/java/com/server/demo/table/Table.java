package com.server.demo.table;

import java.util.Set;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.server.demo.field.Field;
import com.server.demo.field.Field_;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Table {
    private static final String tableNamePrefix = "user";

    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    public String name;

    @JsonManagedReference
    @OneToMany(mappedBy = Field_.TABLE, fetch = FetchType.LAZY)
    public Set<Field> fields;

    protected Table() {
    };

    public Table(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return tableNamePrefix + "_" + name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
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
        Table other = (Table) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (fields == null) {
            if (other.fields != null)
                return false;
        } else if (!fields.equals(other.fields))
            return false;
        return true;
    }

}
