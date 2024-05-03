package aggregatedRevenueManagementSystem.repositories;

import aggregatedRevenueManagementSystem.models.DailyRevenue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static aggregatedRevenueManagementSystem.utils.DateUtils.equalDates;


public class DailyRevenueRepository {
    private Map<Long, DailyRevenue> dailyRevenueMap;
    //this is the in-memory storage for revenue for a particular id(which is in a long format)

    private static long count = 0;

    public DailyRevenueRepository(){
        this.dailyRevenueMap = new HashMap<>();
    }

    public DailyRevenue save(DailyRevenue dailyRevenue){
        if(dailyRevenue.getId() == 0){
            dailyRevenue.setId(++count);
        }
        dailyRevenueMap.put(dailyRevenue.getId(), dailyRevenue);
        return dailyRevenue;

        //this method initiates the count of the id and subsequently, whenever a new entry is made
        //to the database, the database uupdates the count.
        //through this we are setting the id of the new entries.
    }

    public List<DailyRevenue> getDailyRevenueBetweenDates(Date startDate, Date endDate){

        //start date is given and end date is given
        //first, we will get the values from the map
        //then we will stream those values
        //now we want to filter out those streams of data based on given condition
        return dailyRevenueMap.values().stream().filter(
                //condition for the filter is that we want to filter it on the bases of dailyRevenue
                dailyRevenue -> {
                    //where date is greater start date and less than equal to end date
                    Date date = dailyRevenue.getDate();
                    return (date.compareTo(startDate) >= 0 || equalDates(date, startDate)) &&
                            (date.compareTo(endDate) <= 0 || equalDates(date, endDate));
                    //equalDates is a util we have created to find out if both the dates are same.
                }
        ).toList();
    }
}
