package aggregatedRevenueManagementSystem.utils;

import aggregatedRevenueManagementSystem.models.Pair;
import aggregatedRevenueManagementSystem.models.RevenueQueryType;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Pair<Date, Date> getStartAndEndDateByQueryType(RevenueQueryType revenueQueryType){

        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);

        int financialYearStart = year;

        Calendar startCal = (Calendar) calender.clone();
        Calendar endCal = (Calendar) calender.clone();

        switch(revenueQueryType){
            case CURRENT_FY -> {
                startCal.set(financialYearStart, Calendar.JANUARY);
                endCal.set(financialYearStart, Calendar.DECEMBER);
            }
            case PREVIOUS_FY -> {
                startCal.set(financialYearStart - 1, Calendar.JANUARY);
                endCal.set(financialYearStart - 1, Calendar.DECEMBER);
            }
            case CURRENT_MONTH -> {
                startCal.set(Calendar.DAY_OF_MONTH, 1);
                endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            case PREVIOUS_MONTH -> {
                startCal.add(Calendar.MONTH, - 1);
                startCal.set(Calendar.DAY_OF_MONTH, 1);
                endCal.add(Calendar.MONTH, -1);
                endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));

            }
            default -> throw new IllegalAccessException("Invalid Query Type")
        }

        return new Pair<>(startCal.getTime(), endCal.getTime());
    }

    public static boolean equalDates(Date date1, Date date2){
        if(date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear() && date1.getDate() == date2.getDate()){
            return true;
        }
        return false;
    }
}
