package model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * entity
 */
public class Car implements Serializable {

    public Car() {
    }

    public Car(String marque, String clazz, String model, BigDecimal price) {
        this.marque = marque;
        this.clazz = clazz;
        this.model = model;
        this.price = price;
    }

    private int id;
    private String marque;
    private String clazz;
    private String model;
    private BigDecimal price;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", marque='" + marque + '\'' +
                ", clazz='" + clazz + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price;
    }
}
