package br.com.itau.pix.keyprocessor.infra.jpa;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity")
public class KeyPixJpa {

    @Id
    @Column(name = "id", length = 36)
    private String id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "type", length = 9)
    private String type;
    @Column(name = "value", length = 77)
    private String value;
    @Column(name = "account_type", length = 10)
    private String accountType;
    @Column(name = "agency_number", length = 4)
    private String agencyNumber;
    @Column(name = "account_number", length = 8)
    private String accountNumber;
    @Column(name = "account_holder_first_name")
    private String accountHolderName;
    @Column(name = "account_holder_last_name", length = 45)
    private String accountHolderLastName;
    @Column(name = "date_time_inclusion")
    private LocalDateTime dateTimeInclusion;
    @Column(name = "date_time_update")
    private LocalDateTime dateTimeUpdate;
    @Column(name = "active")
    private Integer active;

    protected KeyPixJpa() {

    }

    public KeyPixJpa(String id, String name, String type, String value, String accountType, String agencyNumber,
                     String accountNumber, String accountHolderName, String accountHolderLastName, LocalDateTime dateTimeInclusion,
                     LocalDateTime dateTimeUpdate, Integer active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderLastName = accountHolderLastName;
        this.dateTimeInclusion = dateTimeInclusion;
        this.dateTimeUpdate = dateTimeUpdate;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountHolderLastName() {
        return accountHolderLastName;
    }

    public LocalDateTime getDateTimeInclusion() {
        return dateTimeInclusion;
    }

    public LocalDateTime getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public Integer getActive() {
        return active;
    }


    protected void setId(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    protected void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    protected void setAccountHolderLastName(String accountHolderLastName) {
        this.accountHolderLastName = accountHolderLastName;
    }

    protected void setDateTimeInclusion(LocalDateTime dateTimeInclusion) {
        this.dateTimeInclusion = dateTimeInclusion;
    }

    protected void setDateTimeUpdate(LocalDateTime dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }

    protected void setActive(Integer active) {
        this.active = active;
    }
}
