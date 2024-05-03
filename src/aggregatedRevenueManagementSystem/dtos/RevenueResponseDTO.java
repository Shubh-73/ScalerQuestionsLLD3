package aggregatedRevenueManagementSystem.dtos;

import aggregatedRevenueManagementSystem.models.AggregateRevenue;

public class RevenueResponseDTO {

    private AggregateRevenue aggregateRevenue;
    private ResponseStatus responseStatus;

    public AggregateRevenue getAggregateRevenue() {
        return aggregateRevenue;
    }

    public void setAggregateRevenue(AggregateRevenue aggregateRevenue) {
        this.aggregateRevenue = aggregateRevenue;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
