package com.jpa.entitymanage.entity;

import javax.persistence.*;

@Entity
@Table(name = "senyi_deptment", schema = "jpademo", catalog = "")
public class SenyiDeptmentEntity {
    private int depId;
    private String depName;
    private String deptCode;
    private String note;

    @Id
    @Column(name = "dep_id")
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Basic
    @Column(name = "dep_name")
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "dept_code")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SenyiDeptmentEntity that = (SenyiDeptmentEntity) o;

        if (depId != that.depId) return false;
        if (depName != null ? !depName.equals(that.depName) : that.depName != null) return false;
        if (deptCode != null ? !deptCode.equals(that.deptCode) : that.deptCode != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = depId;
        result = 31 * result + (depName != null ? depName.hashCode() : 0);
        result = 31 * result + (deptCode != null ? deptCode.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
