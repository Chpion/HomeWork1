package Homework;

import java.util.*;

public class User {
    private String name;
    private String email;
    private String password;
    private ArrayList<Habit> habitList;
    private HashMap<Calendar, Habit> habitDateMap;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.habitList = new ArrayList<>();
        this.habitDateMap = new HashMap<>();
    }

    public HashMap<Calendar, Habit> getHabitDateMap() {
        return habitDateMap;
    }

    public void setHabitDateMap(HashMap<Calendar, Habit> habitDateMap) {
        this.habitDateMap = habitDateMap;
    }

    public void putHabitInCalendarThisDay(Habit habit, Calendar calendar){
        habitDateMap.put(calendar, habit);
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
        Calendar calendar = Calendar.getInstance();
        habitDateMap.put(calendar, habit);
    }

    public void removeHabit(Habit habit){
        habitList.remove(habit);
    }

    public Habit habitFindByName(String name){
        Habit habitIsFound = null;
        for(Habit habit : habitList){
            if(habit.getHabitName().equals(name)){
                habitIsFound = habit;
            }
        }
        return habitIsFound;
    }

    public User() {
        habitList = new ArrayList<>();
        habitDateMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Habit> getHabitList() {
        return habitList;
    }

    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(habitList, user.habitList) && Objects.equals(habitDateMap, user.habitDateMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, habitList, habitDateMap);
    }

    @Override
    public String toString() {
        return  "Имя - " + name + "\n" +
                "Email - " + email +
                "\nПривычки:\n" + habitList;
    }
}
