package Homework;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Habit {
    private String habitName;
    private String direction;
    private String frequency;
    private Calendar timeOfCreation;

    public Habit(String habitName, String direction, String frequency) {
        this.habitName = habitName;
        this.direction = direction;
        this.frequency = frequency;
        this.timeOfCreation = Calendar.getInstance();
    }

    public Calendar getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Calendar timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public Habit() {
        this.timeOfCreation = Calendar.getInstance();
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        int time = timeOfCreation.get(Calendar.MONTH);
        String mounth = null;
        if(time == 1){
            mounth = "Январь";
        }else if(time == 2){
            mounth = "Февраль";
        }else if(time == 3){
            mounth = "Март";
        }else if(time == 4){
            mounth = "Апрель";
        }else if(time == 5){
            mounth = "Май";
        }else if(time == 6){
            mounth = "Июнь";
        }else if(time == 7){
            mounth = "Июль";
        }else if(time == 8){
            mounth = "Август";
        }else if(time == 9){
            mounth = "Сентябрь";
        }else if(time == 10){
            mounth = "Октябрь";
        }else if(time == 11){
            mounth = "Ноябрь";
        }else if(time == 12){
            mounth = "Декабрь";
        }
        return "Привычка - " + habitName + "\n" +
                "Описание - " + direction + "\n" +
                "Частота - " + frequency + "\n" +
                "Дата создания - " + timeOfCreation.get(Calendar.DAY_OF_MONTH) + " " + mounth + " " + timeOfCreation.get(Calendar.YEAR);
    }
}
