package aggregatedRevenueManagementSystem.controllers;

import aggregatedRevenueManagementSystem.dtos.ResponseStatus;
import aggregatedRevenueManagementSystem.dtos.RevenueRequestDTO;
import aggregatedRevenueManagementSystem.dtos.RevenueResponseDTO;
import aggregatedRevenueManagementSystem.models.AggregateRevenue;
import aggregatedRevenueManagementSystem.service.RevenueService;

public class RevenueController {

    private RevenueService revenueService;

    public RevenueController(RevenueService revenueService){
        this.revenueService = revenueService;
    }

    public RevenueResponseDTO calculateRevenue(RevenueRequestDTO revenueRequestDTO){
        RevenueResponseDTO responseDTO = new RevenueResponseDTO();
        try{
            AggregateRevenue aggregateRevenue = revenueService.calculateRevenue(revenueRequestDTO.getUserID(),
                    revenueRequestDTO.getRevenueQueryType());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setAggregateRevenue(aggregateRevenue);
        }
        catch (Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return responseDTO;
        }
    }



}
