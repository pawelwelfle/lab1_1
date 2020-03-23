package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

public class Product {

    private String Id;

    private String Name;

    private Date SnapshotDate;

    private String Type;

    private Money Price;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getSnapshotDate() {
        return SnapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        SnapshotDate = snapshotDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Money getPrice() {
        return Price;
    }

    public void setPrice(Money price) {
        this.Price = price;
    }
}
