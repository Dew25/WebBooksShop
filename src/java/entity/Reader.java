/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Melnikov
 */
@Entity
@XmlRootElement
public class Reader implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String phone;
    private int money;

    public Reader() {
    }

    public Reader(String firstname, String lastname, String phone, int money) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.money = money;
    }
    public Reader(String firstname, String lastname, String phone, String money) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.setMoney(money);
    }
    public Reader(String firstname, String lastname, String phone, double money) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.setMoney(money);
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Reader{" 
                + "id=" + id 
                + ", firstname=" + firstname 
                + ", lastname=" + lastname 
                + ", phone=" + phone 
                + ", money=" + money 
                + '}';
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.firstname);
        hash = 37 * hash + Objects.hashCode(this.lastname);
        hash = 37 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reader other = (Reader) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public int getMoney() {
        return money;
    }
    public String getMoneyStr() {
        Double dMoney = (double)this.money/100;
        return dMoney.toString();
    }
    public double getMoneyDouble() {
        return (double)this.money/100;
    }
    

    public void setMoney(int money) {
        this.money = money;
    }
    public void setMoney(String money) throws NumberFormatException {
        money = money.trim().replaceAll(",", ".");
        try {
            double dMoney = Double.parseDouble(money);
            this.money = (int)(dMoney * 100);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неправильный формат числа");
        }
    }
    public void setMoney(double money) {
        this.money = (int)(money * 100);
    }
    
}
