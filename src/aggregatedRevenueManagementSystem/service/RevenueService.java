package aggregatedRevenueManagementSystem.service;

import aggregatedRevenueManagementSystem.exceptions.UnAuthorisedAccess;
import aggregatedRevenueManagementSystem.exceptions.UserNotFoundException;
import aggregatedRevenueManagementSystem.models.*;

import java.util.Optional;
import java.util.Date;
import java.util.List;

import aggregatedRevenueManagementSystem.repositories.DailyRevenueRepository;
import aggregatedRevenueManagementSystem.repositories.UserRepository;
import aggregatedRevenueManagementSystem.utils.DateUtils;

public class RevenueService {

    private DailyRevenueRepository dailyRevenueRepository;
    private UserRepository userRepository;

    public RevenueService(DailyRevenueRepository dailyRevenueRepository, UserRepository userRepository){
        this.dailyRevenueRepository = dailyRevenueRepository;
        this.userRepository = userRepository;
    }

    public AggregateRevenue calculateRevenue(long userId, String queryType) throws UnAuthorisedAccess, UserNotFoundException {

        //get the user from the user repo to find out if the user is authorised or not
        Optional<User> userOptional = userRepository.findById(userId);
        //User repo returns optional object
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User does not exist in the database");
        }
        //if the optional object is not null, then we can get the user object from the optional
        User user = userOptional.get();

        // 2. we need to determine if the user we got has the billing privileges
        if(user.getUserType() != UserType.BILLING){
            throw new UnAuthorisedAccess("You are not allowed to access the service");
            //throw exception if the user is not allowed
        }

        RevenueQueryType revenueQueryType = RevenueQueryType.valueOf(queryType);
        //we need to find out what kind of query we do want after parsing the input string

        /*
        * there are few options of what can be the query.
        *
        * 1. it can be CURRENT_MONTH query.
        * 2. it can be CURRENT_FY query
        * 3. it can be for previous month and previous year.
        *
        * in all the cases we need to determine the start date and end date.
        * Date is already a column in the db, hence we can get it from db.
        *
        * there is a function in the repo from where we can provide the input of dates and get
        * the list of daily revenue between those dates.
        *
        * We need to find out start date and end date as per our query.
        *
        * For that, we have created a DateUtil class in which we can provide start and end dates
        * based on the query type.
        * We will get pair of dates, in which we will get start and end date.
        *
        * */

        Pair<Date, Date> dates = DateUtils.getStartAndEndDateByQueryType(revenueQueryType);
        Date startDate = dates.getLeft();
        Date endDate = dates.getRight();
        List<DailyRevenue> dailyRevenueBetweenDates = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate, endDate);

        double totalRevenueFromFood = dailyRevenueBetweenDates.stream().mapToDouble(DailyRevenue::getRevenueFromSales).sum();
        double totalGstCollection = dailyRevenueBetweenDates.stream().mapToDouble(DailyRevenue::getTotalGst).sum();
        double totalServiceChargeCollection = dailyRevenueBetweenDates.stream().mapToDouble(DailyRevenue::getTotalServiceCharge).sum();

        AggregateRevenue aggregateRevenue = new AggregateRevenue();

        aggregateRevenue.setTotalRevenue(totalRevenueFromFood + totalGstCollection + totalServiceChargeCollection);
        aggregateRevenue.setFromDate(startDate);
        aggregateRevenue.setToDate(endDate);
        aggregateRevenue.setRevenueFromFood(totalRevenueFromFood);
        aggregateRevenue.setTotalGst(totalGstCollection);
        aggregateRevenue.setTotalServiceCharge(totalServiceChargeCollection);

        return aggregateRevenue;
    }

}
