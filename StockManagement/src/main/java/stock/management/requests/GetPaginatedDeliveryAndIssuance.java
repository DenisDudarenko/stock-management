package stock.management.requests;

import stock.management.enums.OperationType;
import stock.management.enums.ProductType;

import java.sql.Timestamp;

public class GetPaginatedDeliveryAndIssuance {
    private Integer page;
    private Integer count;
    private OperationType type;
    private Timestamp operationTime;

    public GetPaginatedDeliveryAndIssuance(Integer page, Integer count, OperationType type, Timestamp operationTime) {
        this.page = page;
        this.count = count;
        this.type = type;
        this.operationTime = operationTime;
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

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Timestamp operationTime) {
        this.operationTime = operationTime;
    }
}
