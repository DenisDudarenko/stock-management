package stock.management.requests;

import stock.management.enums.ProductType;

import java.sql.Timestamp;

public class GetPaginatedProducts {
    private Integer page;
    private Integer count;
    private ProductType type;
    private Timestamp lifeTime;
    private String supplierInn;

    public GetPaginatedProducts(Integer page, Integer count, ProductType type, Timestamp lifeTime, String supplierInn){
        this.page = page;
        this.count = count;
        this.type = type;
        this.lifeTime = lifeTime;
        this.supplierInn = supplierInn;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Timestamp lifeTime) {
        this.lifeTime = lifeTime;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getSupplierInn() {
        return supplierInn;
    }

    public void setSupplierInn(String supplierInn) {
        this.supplierInn = supplierInn;
    }
}
