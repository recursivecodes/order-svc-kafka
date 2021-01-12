package codes.recursive.domain;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Order {

    private Long id;
    private Integer customerId;
    private Double totalCost;
    private ShipmentStatus shipmentStatus;

    public Order(Long id, Integer customerId, Double totalCost, ShipmentStatus shipmentStatus) {
        this.id = id;
        this.customerId = customerId;
        this.totalCost = totalCost;
        this.shipmentStatus = shipmentStatus != null ? shipmentStatus : ShipmentStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
