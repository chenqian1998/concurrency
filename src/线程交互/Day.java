package 线程交互;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum Day {
    MONDAY("周一"), TUESDAY("周二"), WEDNESDAY("周三"), THURSDAY("周四"), FRIDAY("周五"), SATURDAY("周六"), SUNDAY("周日");


    private String date;

    private Day(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static Day forEach_DayEnum(String date){
        Day[] days = Day.values();
        for(Day day:days){
            if(date.equals(day.getDate())){
                return day;
            }
        }
        return null;
    }
}
