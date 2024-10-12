package Homework;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int page = 0;
        Boolean work = true;
        Boolean accountIsOnline = true;
        DataBase dataBase = new DataBase();
        Scanner in = new Scanner(System.in);
        String line;
        User admin = new User("admin", "admin@mail.ru", "admin");
        dataBase.addUser(admin);
        User user = null;
        while(work){
            while(page == 0){
                System.out.println("\n\tВход");
                System.out.println("Для авторизации, введите 1");
                System.out.println("Для регистрации, введите 2");
                line = in.nextLine();
                while(!(line.equals("1") || line.equals("2"))) {
                    System.out.println("Ошибка, можно вводить только 1 и 2, попробуйте ещё раз:");
                    line = in.nextLine();
                }
                if(line.equals("1")) {
                    System.out.println("\n\tАвторизация\nДля авторизации, введите 1\nДля сброса пароля, введите 2\nНазад - 3");
                    line = in.nextLine();
                    while (!(line.equals("1") || line.equals("2") || line.equals("3"))) {
                        System.out.println("Ошибка, можно вводить только 1, 2 и 3, попробуйте ещё раз:");
                        line = in.nextLine();
                    }
                    if (line.equals("1")) {
                        user = dataBase.authorization();
                        page++;
                        accountIsOnline = true;
                    } else if (line.equals("2")){
                        page++;
                        page = dataBase.passwordReset(page);
                    }
                }else {
                    dataBase.registry();
                    user = dataBase.authorization();
                    page++;
                    accountIsOnline = true;
                }
            }
            while(accountIsOnline){
                if(user.equals(admin)){
                    while(page == 1){
                        System.out.println("\n\tСтраница Администратора");
                        System.out.println("Введите для:\nСписок пользователей - 1\nНайти пользователя - 2\nВыйти из аккаунта - 3");
                        line = in.nextLine();
                        while(!(line.equals("1") || line.equals("2") || line.equals("3"))) {
                            System.out.println("Ошибка, можно вводить только 1,2 и 3, попробуйте ещё раз:");
                            line = in.nextLine();
                        }
                        if(line.equals("1")){
                            dataBase.getAllUsers();
                        }else if(line.equals("2")){
                            System.out.println("Введите email искомого пользователя:");
                            line = in.nextLine();
                            User foundUser = null;
                            while(line.isEmpty()){
                                System.out.println("Пустая строка, введите, пожалуйста, email пользователя:");
                                line = in.nextLine();
                            }
                            foundUser = dataBase.getUserByEmail(line);
                            if(foundUser == null){
                                System.out.println("Пользователя с данным email не существует");
                            }else{
                                page++;
                                while(page == 2){
                                    System.out.println("\n\tУправление пользователем");
                                    System.out.println("Введите для:\nПривычки пользователя - 1\nУдалить пользователя - 2\nНазад - 3");
                                    line = in.nextLine();
                                    while(!(line.equals("1") || line.equals("2") || line.equals("3"))) {
                                        System.out.println("Ошибка, можно вводить только 1,2 и 3, попробуйте ещё раз:");
                                        line = in.nextLine();
                                    }
                                    if(line.equals("1")){
                                        dataBase.getAllHabits(foundUser);
                                    }else if(line.equals("2")) {
                                        dataBase.removeUser(foundUser);
                                        page--;
                                    }else{
                                        page--;
                                    }
                                }
                            }
                        }else{
                            page = 0;
                            accountIsOnline = false;
                        }
                    }
                }else{
                    while(page == 1){
                        System.out.println("\n\tСтраница Пользователя");
                        System.out.println("Введите для:\nСоздать новую привычку - 1\nПросмотр всех привычек - 2\nНайти привычку - 3\n" +
                                "Отчёт по привычкам - 4\nРедактирование профиля - 5\nУдалить аккаунт - 6\nВыйти из аккаунта - 7");
                        line = in.nextLine();
                        while(!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4") || line.equals("5") || line.equals("6") || line.equals("7"))) {
                            System.out.println("Ошибка, можно вводить только 1,2,3,4,5,6 и 7, попробуйте ещё раз:");
                            line = in.nextLine();
                        }
                        if(line.equals("1")){
                            dataBase.createHabitToUser(user);
                        }else if(line.equals("2")){
                            dataBase.getAllHabits(user);
                        }else if(line.equals("3")){
                            page = dataBase.findHabitByName(user, 1);
                        }else if(line.equals("4")){
                            dataBase.allHabitsStatistics(user);
                        }else if(line.equals("5")) {
                            page = dataBase.refactorUser(user, page);
                        }else if(line.equals("6")){
                            dataBase.removeUser(user);
                            page = 0;
                            accountIsOnline = false;
                        }else{
                            page = 0;
                            accountIsOnline = false;
                        }
                    }
                }
            }
        }
    }
}

