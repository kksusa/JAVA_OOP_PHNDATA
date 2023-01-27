import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Basic
{
    static int CheckNumbers(String param) {
        Scanner iScanner = new Scanner(System.in);
        while (true) {
            System.out.printf(param + ": ");
            if (iScanner.hasNextInt() == true) {
                int number = iScanner.nextInt();
                if (number > 99 && number < 1000) {
                    return number;
                } else {
                    System.out.println("Номер введен неправильно.");
                    continue;
                }
            } else {
                System.out.println("Номер введен неправильно.");
                continue;
            }
        }
    }

    static ArrayList<Subscriber> Add(ArrayList<Subscriber> data)
    {
        Scanner iScanner = new Scanner(System.in);
        int phone = CheckNumbers("Введите номер телефона");
        for (int i = 0; i < data.size(); i++) {
            if (phone == data.get(i).getPhoneNumber()) {
                System.out.println("Такой номер уже есть в базе данных.");
                return data;
            } else continue;
        }
        System.out.print("Введите абонента: ");
        String name = iScanner.nextLine();
        data.add(new Subscriber(name, phone));
        System.out.println("Номер добавлен.");
        return data;
    }

    static ArrayList<Subscriber> Delete(ArrayList<Subscriber> data)
    {
        if (data.size() != 0)
        {
            boolean check = false;
            int phone = CheckNumbers("Введите номер телефона для удаления");
            for (int i = 0; i < data.size(); i++)
            {
                if (phone == data.get(i).getPhoneNumber())
                {
                    data.remove(i);
                    System.out.printf("Номер %d удален.\n", phone);
                    check = true;
                }
            }
            if (check == false) System.out.println("Такого номера нет.");
        }
        else System.out.println("База данных пуста.\nДобавьте что-нибудь через add.");
        return data;
    }

    static void List(ArrayList<Subscriber> data)
    {
        if (data.size() != 0)
        {
            for (int i = 0; i < data.size(); i++)
            {
                System.out.printf("Номер: %d, Абонент: %s\n", data.get(i).getPhoneNumber(), data.get(i).getName());
            }
        }
        else System.out.println("База данных пуста.\nДобавьте что-нибудь через add.");
    }

    static ArrayList <Subscriber> Read()
    {
        ArrayList<Subscriber> data = new ArrayList<>();
        String filename = "data.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            if (filename.isEmpty()) return data;
            else
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String array[] = line.split(" ");
                    data.add(new Subscriber(array[0], Integer.parseInt(array[1])));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    static void Write(ArrayList <Subscriber> data)
    {
        String filename = "data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (int i = 0; i < data.size(); i++)
            {
                writer.write(data.get(i).getName() + " " + data.get(i).getPhoneNumber() + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        ArrayList<Subscriber> DB = Read();
        System.out.println("Это система хрнения данных о внутренних номерах сотрудников предприятия.");
        System.out.println("Для записи телефона и пользователя введите add.");
        System.out.println("Для удаления телефона и пользователя введите delete.");
        System.out.println("Для вывода списка телефонов и пользователей введите list.");
        System.out.println("Для вывода списка команд введите help.");
        System.out.println("Для выхода из программы введите exit.");
        Scanner iScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду:");
            String cmd = iScanner.nextLine();
            if (cmd.equals("add"))
            {
                DB = Add(DB);
                Write(DB);
            }
            else if (cmd.equals("delete"))
            {
                DB = Delete(DB);
                Write(DB);
            }
            else if (cmd.equals("list")) List(DB);
            else if (cmd.equals("help")) {
                System.out.println("\nДля записи телефона и пользователя введите add;");
                System.out.println("Для удаления телефона и пользователя введите delete;");
                System.out.println("Для вывода списка телефонов и пользователей введите list;");
                System.out.println("Для выхода из программы введите exit.");
            } else if (cmd.equals("exit")) break;
            else System.out.println("Такой команды нет.\nДля вывода возможных команд введите help.");
        }
        iScanner.close();
    }
}

