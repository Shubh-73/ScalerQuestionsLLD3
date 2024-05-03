package aggregatedRevenueManagementSystem.dtos;

public class RevenueRequestDTO {

    private String revenueQueryType;
    private long userID;

    public String getRevenueQueryType() {
        return revenueQueryType;
    }

    public void setRevenueQueryType(String revenueQueryType) {
        this.revenueQueryType = revenueQueryType;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
