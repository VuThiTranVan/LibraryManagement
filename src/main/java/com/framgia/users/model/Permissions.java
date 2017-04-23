package com.framgia.users.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Permissions.java description table Permissions
 * 
 * @version 16/04/2017
 * @author phan.van.hieu@framgia.com
 */
@Entity
@Table(name = "Permissions", catalog = "Library")
public class Permissions implements java.io.Serializable{
	
	private static final long serialVersionUID = -5083671866629810625L;
	
    private Integer permissionsId;
    private String permissionName;
    private String description;
    private String deleteFlag;
    private Date dateCreate;
    private String userCreate;
    private Date dateUpdate;
    private String userUpdate;
    private Set<Users> users = new HashSet<Users>(0);

    public Permissions() {
    }

    public Permissions(Integer permissionsId, String permissionName, String description, String deleteFlag,
            Date dateCreate, String userCreate, Date dateUpdate, String userUpdate) {
        super();
        this.permissionsId = permissionsId;
        this.permissionName = permissionName;
        this.description = description;
        this.deleteFlag = deleteFlag;
        this.dateCreate = dateCreate;
        this.userCreate = userCreate;
        this.dateUpdate = dateUpdate;
        this.userUpdate = userUpdate;
    }

    public Permissions(Integer permissionsId, String permissionName, String description, String deleteFlag,
            Date dateCreate, String userCreate, Date dateUpdate, String userUpdate, Set<Users> users) {
        super();
        this.permissionsId = permissionsId;
        this.permissionName = permissionName;
        this.description = description;
        this.deleteFlag = deleteFlag;
        this.dateCreate = dateCreate;
        this.userCreate = userCreate;
        this.dateUpdate = dateUpdate;
        this.userUpdate = userUpdate;
        this.users = users;
    }

    @Id
    @Column(name = "permissionsId", unique = true, nullable = false)
    public Integer getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Column(name = "permissionName", nullable = false, length = 30)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "deleteFlag", nullable = false, length = 1)
    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreate", nullable = false)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "userCreate", nullable = false, length = 30)
    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateUpdate", nullable = false)
    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Column(name = "userUpdate", nullable = false, length = 30)
    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
