package com.example.stock_exchange.model;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "status_name")
    private String statusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status statuses = (Status) o;

        if (id != statuses.id) return false;
        if (statusName != null ? !statusName.equals(statuses.statusName) : statuses.statusName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        return result;
    }
}
