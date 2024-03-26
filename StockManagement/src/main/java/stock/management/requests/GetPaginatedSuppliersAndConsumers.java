package stock.management.requests;

import stock.management.enums.ProductType;

import java.sql.Timestamp;

public class GetPaginatedSuppliersAndConsumers {
    private Integer page;
    private Integer count;
    private String supplierInn;

    public GetPaginatedSuppliersAndConsumers(){}
    public GetPaginatedSuppliersAndConsumers(Integer page, Integer count, String supplierInn) {
        this.page = page;
        this.count = count;
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

    public String getSupplierInn() {
        return supplierInn;
    }

    public void setSupplierInn(String supplierInn) {
        this.supplierInn = supplierInn;
    }
}
