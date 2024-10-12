package Homework;

import java.util.*;

public class DataBase {

    private ArrayList<User> users;

    public DataBase() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Integer passwordReset(int page) {
        Scanner in = new Scanner(System.in);
        String line;
        User user = null;
        System.out.println("\n\tСброс пароля\nВведите email:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, Ваш email:");
            line = in.nextLine();
        }
        user = getUserByEmail(line);
        while(page == 1){
            System.out.println("Для продолжения процесса смены пароля, введите 1\nДля возварщения на предыдущую страницу введите 2");
            line = in.nextLine();
            while(!(line.equals("1") || line.equals("2"))) {
                System.out.println("Ошибка, можно вводить только 1 и 2, попробуйте ещё раз:");
                line = in.nextLine();
            }
            if(line.equals("1")) {
                if (user == null) {
                    System.out.println("Введите, пожалуйста, действующий email:");
                }
                line = in.nextLine();
                while (line.isEmpty()) {
                    System.out.println("Пустая строка, введите, пожалуйста, Ваш email:");
                    line = in.nextLine();
                }
                user = getUserByEmail(line);
                if (user != null) {
                    System.out.println("Введите новый пароль:");
                    while (line.isEmpty()) {
                        System.out.println("Пустая строка, введите, пожалуйста, новый пароль:");
                        line = in.nextLine();
                    }
                    user.setPassword(line);
                    page--;
                }
            }else{
                page--;
            }
        }
        return page;
    }

    public Integer refactorUser(User user, int page){
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("\n\tСтраница редактирования Пользоваталя");
        System.out.println("Редактировать:\nИмя - 1\nEmail - 2\nПароль - 3\nНазад - 4");
        line = in.nextLine();
        while(!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4"))) {
            System.out.println("Ошибка, можно вводить только 1,2,3 и 4, попробуйте ещё раз:");
            line = in.nextLine();
        }
        if(line.equals("1")){
            System.out.println("\n\tСмена имени\nВведите ваше новое имя:");
            line = in.nextLine();
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, Ваше новое имя:");
                line = in.nextLine();
            }
            user.setName(line);
        }else if(line.equals("2")){
            boolean emailIsTrue = false;
            System.out.println("\n\tСмена Email\nВведите Ваш новый email:");
            while(!emailIsTrue){
                line = in.nextLine();
                while(line.isEmpty()){
                    System.out.println("Пустая строка, введите, пожалуйста, Ваш email:");
                    line = in.nextLine();
                }
                if(getUserByEmail(line)==null){
                    emailIsTrue = true;
                }else{
                    System.out.println("Пользователь с данным email уже зарегистрирован, введите, пожалуйста, другой email:");
                }

            }
            user.setEmail(line);
        }else if(line.equals("3")){
            changePassword(user);
        }else{
            page--;
        }
        return page;
    }


    public void changePassword(User user){
        System.out.println("\n\tСмена пароля\nДля смены пароля введите ваш нынешний пароль: ");
        Scanner in = new Scanner(System.in);
        String line;
        Boolean isTrue = false;
        line = in.nextLine();
        while(!isTrue){
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, Ваш пароль:");
                line = in.nextLine();
            }
            if(user.getPassword().equals(line)){
                System.out.println("Ваш пароль верный");
                isTrue = true;
            }else {
                System.out.println("Неверный пароль, попробуйте ещё раз");
                line = in.nextLine();
            }
        }
        System.out.println("Введите новый пароль:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, Ваш новый пароль:");
            line = in.nextLine();
        }
        System.out.println("Успешно");
    }

    public void addUser(User user){
        users.add(user);
        System.out.println("Пользователь зарегистрирован");
    }

    public void removeUser(User user){
        users.remove(user);
        System.out.println("Пользователь удалён");
    }

    public void getAllUsers(){
        for(User user : users){
            System.out.println(user);
        }
    }

    public User getUserByEmail(String email){
        User user1 = null;
        for(User user : users){
            if(user.getEmail().equals(email)){
                user1 = user;
            }

        }
        if(user1 == null){
            System.out.println("Пользователь с таким email не был найден");
        }
        return user1;
    }

    public int findHabitByName(User user, int page){
        Habit habit = null;
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("\n\tСтранциа поиска привычки");
        System.out.println("Введите название привычки:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, название привычки:");
            line = in.nextLine();
        }
        String habitName = line;
        habit = user.habitFindByName(habitName);
        if(habit == null){
            System.out.println("Привычки с таким названием не существует");
        }else{
            page++;
            while(page == 2){
                System.out.println("\n\tСтраница привычки пользователя");
                System.out.println("Привычка - '" + habitName + "'\nВведите:\n1 - Отметить привычку в календаре сегодня\n" +
                        "2 - Отметить в календаре за прошлый день\n3 - История\n4 - Статистика\n5 - Удалить\n6 - Назад");
                line = in.nextLine();
                while(!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4") || line.equals("5") || line.equals("6"))) {
                    System.out.println("Ошибка, можно вводить только 1,2,3,4 и 5, попробуйте ещё раз:");
                    line = in.nextLine();
                }
                if(line.equals("1")){
                    putHabitInCalendarToday(user, habit);
                }else if(line.equals("2")){
                    putHabitInCalendarLastDay(user, habit);
                }else if(line.equals("3")){
                    showHabitHistory(user, user.getHabitDateMap());
                }else if(line.equals("4")){
                    habitStatistics(user, user.getHabitDateMap());
                }else if(line.equals("5")){
                    removeHabitFromUser(user, habit);
                    page--;
                }else{
                    page--;
                }
            }
        }
        return page;
    }

    public void habitStreak(User user, HashMap<Calendar, Habit> habitHistory){
        LinkedList<Calendar> habitHistoryList = new LinkedList<>();
        Habit habit = habitHistory.entrySet().iterator().next().getValue();
        int count = -1;
        int lastDay;
        int day;
        for (Map.Entry<Calendar, Habit> entry : habitHistory.entrySet()) {
            habitHistoryList.add(entry.getKey());
        }
        habitHistoryList.sort( (a, b) -> { return -1 * a.compareTo(b); });
        lastDay = habitHistoryList.get(0).get(Calendar.DAY_OF_MONTH);
        for (Calendar calendar : habitHistoryList) {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            if(lastDay >= day){
                count++;
            }else{
                break;
            }
            lastDay = day;
        }
        System.out.println("Серия '" + habit.getHabitName() + "' - " + count);
    }

    public void habitSuccessExecution(User user, HashMap<Calendar, Habit> habitHistory, String period){
        LinkedList<Calendar> habitHistoryList = new LinkedList<>();
        int percent = 0;
        int count = 0;
        int day;
        for (Map.Entry<Calendar, Habit> entry : habitHistory.entrySet()) {
            habitHistoryList.add(entry.getKey());
        }
        habitHistoryList.sort( (a, b) -> { return -1 * a.compareTo(b); });
        Calendar calendar = Calendar.getInstance();
        if(period.equals("1")){
            if(calendar.get(Calendar.DAY_OF_MONTH) == habitHistoryList.get(0).get(Calendar.DAY_OF_MONTH)){
                percent = 100;
            }else{
                percent = 0;
            }
            System.out.println("Процент успешного выполнения привычек за день " + percent + "%");
        }else if(period.equals("2")){
            day = calendar.get(Calendar.DAY_OF_WEEK);
            int firstDay = calendar.get(Calendar.DAY_OF_MONTH) - (day - 1);
            for(Calendar calendar1 : habitHistoryList){
                if(calendar1.get(Calendar.DAY_OF_MONTH) >= firstDay && calendar1.get(Calendar.DAY_OF_MONTH) <= firstDay + 6){
                    count++;
                }
            }
            percent = count * 100 / 7;
            System.out.println("Процент успешного выполнения привычек за неделю " + percent + "%");
        }else{
            percent = habitHistoryList.size() * 100 / calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println("Процент успешного выполнения привычек за месяц " + percent + "%");
        }

    }

    public void habitStatistics(User user, HashMap<Calendar, Habit> habitHistory){
        LinkedList<Habit> habitHistoryList = new LinkedList<>();
        for (Map.Entry<Calendar, Habit> entry : habitHistory.entrySet()) {
            habitHistoryList.add(entry.getValue());
        }
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("\n\tСтатистика привычки '" + habitHistoryList.get(0).getHabitName() +"за:\n");
        System.out.println("День - 1, Неделю - 2, Месяц - 3");
        line = in.nextLine();
        while(!(line.equals("1") || line.equals("2") || line.equals("3"))) {
            System.out.println("Ошибка, можно вводить только 1,2 и 3, попробуйте ещё раз:");
            line = in.nextLine();
        }
        if(line.equals("1")){
            habitStreak(user, habitHistory);
            habitSuccessExecution(user, habitHistory, line);
        }else if(line.equals("2")){
            habitStreak(user, habitHistory);
            habitSuccessExecution(user, habitHistory, line);
        }else{
            habitStreak(user, habitHistory);
            habitSuccessExecution(user, habitHistory, line);
        }
    }

    public void allHabitsStatistics(User user){
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("\n\tСтатистика привычек за:\n");
        System.out.println("День - 1, Неделю - 2, Месяц - 3");
        line = in.nextLine();
        while(!(line.equals("1") || line.equals("2") || line.equals("3"))) {
            System.out.println("Ошибка, можно вводить только 1,2 и 3, попробуйте ещё раз:");
            line = in.nextLine();
        }
        for(Habit habit : user.getHabitList()){
            System.out.println("\nПривычка '" + habit.getHabitName() + "':\n");
            if(line.equals("1")){
                habitStreak(user, user.getHabitDateMap());
                habitSuccessExecution(user, user.getHabitDateMap(), line);
            }else if(line.equals("2")){
                habitStreak(user, user.getHabitDateMap());
                habitSuccessExecution(user, user.getHabitDateMap(), line);
            }else{
                habitStreak(user, user.getHabitDateMap());
                habitSuccessExecution(user, user.getHabitDateMap(), line);
            }
        }
    }

    public void showHabitHistory(User user, HashMap<Calendar, Habit> habitHistory){
        System.out.println("\n\tИстория привычки за месяц\n");
        LinkedList<Calendar> habitHistoryList = new LinkedList<>();
        for (Map.Entry<Calendar, Habit> entry : habitHistory.entrySet()) {
            habitHistoryList.add(entry.getKey());
        }
        Collections.sort(habitHistoryList);
        System.out.println("День\tНеделя месяца\tМесяц");
        for (Calendar calendar : habitHistoryList) {
            System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + "  \t" + calendar.get(Calendar.WEEK_OF_MONTH) + "\t\t\t\t" + calendar.get(Calendar.MONTH));
        }
    }

    public void putHabitInCalendarLastDay(User user, Habit habit){
        Calendar calendar = Calendar.getInstance();
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("\n\tОтметка привычки");
        System.out.println("Введите число дня этого месяца, в который вы бы хотели добавить привычку:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, число:");
            line = in.nextLine();
        }
        Integer day = Integer.valueOf(line);
        while(day > calendar.get(Calendar.DAY_OF_MONTH)){
            System.out.println("Введите, пожалуйста, прошедшее число этого месяца:");
            line = in.nextLine();
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, число:");
                line = in.nextLine();
            }
            day = Integer.valueOf(line);
        }
        calendar.set(Calendar.DAY_OF_MONTH, day);
        user.putHabitInCalendarThisDay(habit, calendar);
        System.out.println("Привычка отмечена - " + calendar + "число\n");
    }

    public void putHabitInCalendarToday(User user, Habit habit){
        Calendar calendar = Calendar.getInstance();
        user.putHabitInCalendarThisDay(habit, calendar);
        System.out.println("Привычка отмечена\n");
    }

    public void removeHabitFromUser(User user, Habit habit){
        user.removeHabit(habit);
        System.out.println("Привычка '" + habit.getHabitName() + "' удалена\n");
    }

    public void getAllHabits(User user){
        System.out.println("Ваши привычки:\n" + user.getHabitList());
    }

    public void createHabitToUser(User user){
        Scanner in = new Scanner(System.in);
        String direction = "\n'";
        String line;
        Habit habit = new Habit();
        System.out.println("\n\tСоздание привычки");
        System.out.println("Введите название Вашей Привычки:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, название Вашей привычки:");
            line = in.nextLine();
        }
        habit.setHabitName(line);
        System.out.println("Введите описание Вашей Привычки:");
        line = in.nextLine();
        while(!line.isEmpty()){
            direction += (line + "\n");
            line = in.nextLine();
        }
        direction += "'";
        habit.setDirection(direction);
        System.out.println("Введите частоту привычка, если ежедневно напишите 1, если еженедельно напишите 2:");
        line = in.nextLine();
        while(!(line.equals("1") || line.equals("2"))) {
            System.out.println("Ошибка, можно вводить только 1 и 2, попробуйте ещё раз:");
            line = in.nextLine();
        }
        if(line.equals("1")){
            habit.setFrequency("Ежедневно");
        }else{
            habit.setFrequency("Еженедельно");
        }
        Calendar calendar = Calendar.getInstance();
        habit.setTimeOfCreation(calendar);
        user.addHabit(habit);
        System.out.println("Добавлена привычка '" + habit.getHabitName() + "'");
    }

    public void registry(){
        Scanner in = new Scanner(System.in);
        String line;
        Boolean emailIsTrue = false;
        User user = new User();
        System.out.println("\n\tРегистрация");
        System.out.println("Введите Ваше имя:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, Ваше имя:");
            line = in.nextLine();
        }
        user.setName(line);
        line = null;
        System.out.println("Введите Ваш email:");
        while(!emailIsTrue){
            line = in.nextLine();
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, Ваш email:");
                line = in.nextLine();
            }
            if(getUserByEmail(line)==null){
                emailIsTrue = true;
            }else{
                System.out.println("Пользователь с данным email уже зарегистрирован, введите, пожалуйста, другой email:");
            }

        }
        user.setEmail(line);
        line = null;
        System.out.println("Введите Ваш пароль:");
        line = in.nextLine();
        while(line.isEmpty()){
            System.out.println("Пустая строка, введите, пожалуйста, Ваше имя:");
            line = in.nextLine();
        }
        user.setPassword(line);
        users.add(user);
        System.out.println("Аккаунт создан");
    }

    public User authorization(){
        Scanner in = new Scanner(System.in);
        String line;
        Boolean isTrue = false;
        User user = null;
        System.out.println("\n\tАвторизация");
        System.out.println("Введите Ваш email:");
        line = in.nextLine();
        while(!isTrue){
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, Ваш Email:");
                line = in.nextLine();
            }
            user = getUserByEmail(line);
            if(user != null){
                isTrue = true;
            }else{
                line = in.nextLine();
            }
        }
        isTrue = false;
        System.out.println("Введите Ваш пароль:");
        line = in.nextLine();
        while(!isTrue){
            while(line.isEmpty()){
                System.out.println("Пустая строка, введите, пожалуйста, Ваш пароль:");
                line = in.nextLine();
            }
            if(user.getPassword().equals(line)){
                System.out.println("Авторизация прошла успешно");
                isTrue = true;
            }else {
                System.out.println("Неверный пароль, попробуйте ещё раз");
                line = in.nextLine();
            }
        }
        System.out.println("Добро пожаловать");
        return user;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "users=" + users +
                '}';
    }
}
