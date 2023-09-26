package org.wecancodeit.hometask.Models;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HouseholdMember {

    @Id
    @GeneratedValue
    private long householdMemberId;

    private String name;
    private String role;
    private String username;
    private String password;
    private int cashAmount;
    
    
    @ManyToOne
    private Household household;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Task> tasks;

    public HouseholdMember() {
    }

    public long getHouseholdMemberId() {
        return householdMemberId;
    }

    public HouseholdMember(String name, Household household) {
        this.name = name;
        this.household = household;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 public void addHousehold(Household household) {
        this.household = household;
    }

    public Household getHousehold() {
        return household;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

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
        HouseholdMember other = (HouseholdMember) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HouseholdMember [name=" + name + "]";
    }
}
