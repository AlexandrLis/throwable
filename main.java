import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // ФИО
        String name = yourName(scan); 
        String [] arrayName = name.split(" ");
        String firstName = arrayName[0];       
        // дата рождения
        birthday(scan, name, firstName);
        // номер телефона
        callNumber(scan, name, firstName);
        // пол
        yourSex(scan, name, firstName);
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // try ФИО
    public static String yourName(Scanner scan){
        String name = "";
        try {
            String [] arrayFullNames = functionEnterName(scan);
            name = functionName(scan, arrayFullNames);
            String [] arrayName = name.split(" ");
            String firstName = arrayName[0];
            try (FileWriter nameWriter = new FileWriter(firstName, true)){
                nameWriter.write(name + " ");
                nameWriter.flush();
            } catch (Exception e) {
                System.out.println("ошибка записи имени в файл");
            }
        } catch (CastNameException e) {
            System.out.println(e.getMessage());
            yourName(scan);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            yourName(scan);
        }
        return name;
    }
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // try дата рождения
    public static void birthday(Scanner scan, String name, String firstName){
        try {
            String [] arrayFullEnterData = functionEnterData(scan);
            String data = functionData(scan, arrayFullEnterData);
            try (FileWriter nameWriter = new FileWriter(firstName, true)){
                nameWriter.write(data + " ");
                nameWriter.flush();
            } catch (Exception e) {
                System.out.println("ошибка записи даты рождения в файл");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            birthday(scan, name, firstName);
        }
    }
    // // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // // try номер телефона
    public static void callNumber(Scanner scan, String name, String firstName){
        try {
            String [] arrayFullNumberPhone = functionNumberPhone(scan);
            functionNumber(arrayFullNumberPhone, scan);
            String number = String.join("", arrayFullNumberPhone);
            try (FileWriter nameWriter = new FileWriter(firstName, true)){
                nameWriter.write(number + " ");
                nameWriter.flush();
            } catch (Exception e) {
                System.out.println("ошибка записи номера телефона в файл");
            }
        } catch (NumberElementsException e) {
            System.out.println(e.getMessage());
            callNumber(scan, name, firstName);   
        } catch (RuntimeException e) {
            System.out.println("в номере должны быть только цифры");
            callNumber(scan, name, firstName);
        }
    }
    // // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // // try пол
    public static void yourSex(Scanner scan, String name, String firstName){
        try {
            String sex = functionSex(scan);
            try (FileWriter nameWriter = new FileWriter(firstName, true)){
                nameWriter.write(sex + "\n");
                nameWriter.flush();
            } catch (Exception e) {
                System.out.println("ошибка записи пола в файл");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            yourSex(scan, name, firstName);
        }
    }
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // функции ФИО
    public static String [] functionEnterName(Scanner scan){
        System.out.println("Введите ваши фамилию имя отчество: ");
        String names = scan.nextLine();

        String [] arrayFullNames = names.split(" ");
        if(arrayFullNames.length != 3){
            throw new RuntimeException("вы ввели фамилию имя и отчество не полностью либо использовали лишнее слово");
        }
        return arrayFullNames;
    }

    public static String functionName(Scanner scan, String [] arrayFullNames){
        
        String [] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        
        String firstName = arrayFullNames[0];
        String [] arrayFirstName = firstName.split("");
        int count1 = 0;
        for (String item1 : arrayFirstName) {
            for (String elemAlphabet : alphabet) {
                if(item1.equals(elemAlphabet)){
                    count1 += 1;
                }
            }
        }

        String secondName = arrayFullNames[1];
        String [] arraySecondName = secondName.split("");
        int count2 = 0;
        for (String item2 : arraySecondName) {
            for (String elemAlphabet : alphabet) {
                if(item2.equals(elemAlphabet)){
                    count2 += 1;
                }
            }
        }

        String thirdName = arrayFullNames[2];
        String [] arrayThirdName = thirdName.split("");
        int count3 = 0;
        for (String item3 : arrayThirdName) {
            for (String elemAlphabet : alphabet) {
                if(item3.equals(elemAlphabet)){
                    count3 += 1;
                }
            }
        }
        if(count1 != arrayFirstName.length || count2 != arraySecondName.length || count3 != arrayThirdName.length){
            throw new CastNameException();
        }
        return firstName + " " + secondName + " " + thirdName;
    }

    // =================================
    // функции дата рождения
    public static String [] functionEnterData(Scanner scan){
        System.out.println("введите вашу дату рождения в формате дд.мм.гггг");
        String data = scan.nextLine();
        String [] dataArray = data.split("\\.");

        if(dataArray.length != 3){
            throw new RuntimeException("вы ввели не три числа, введите корректную дату рождения");
        }
        return dataArray;

    }



    public static String functionData(Scanner scan, String [] arrayFullEnterData){
        String stroka1 = arrayFullEnterData[0];
        Integer num1 = Integer.parseInt(stroka1);

        String stroka2 = arrayFullEnterData[1];
        Integer num2 = Integer.parseInt(stroka2);

        String stroka3 = arrayFullEnterData[2];
        Integer num3 = Integer.parseInt(stroka3);

        if(num3 < 1900 || num3 > 2020){
            throw new RuntimeException("вы не могли родиться в " + num3 + " году");
        }

        if(num2 < 1 || num2 > 12){
            throw new RuntimeException("не существует " + num2 + " месяца");
        }

        if(num1 < 1){
            throw new RuntimeException("в месяце не существует дня с порядковым номером меньше 1");
        }
        // даты и месяцы
        if(num1 > 31 && num2 == 1 || num1 > 31 && num2 == 3 || num1 > 31 && num2 == 5 || num1 > 31 && num2 == 7 || num1 > 31 && num2 == 8 || num1 > 31 && num2 == 10 || num1 > 31 && num2 == 12){
            throw new RuntimeException("в " + num2 + " месяце порядковый номер дня не может быть больше " + 31);
        }
        if(num1 > 30 && num2 == 4 || num1 > 30 && num2 == 6 || num1 > 30 && num2 == 9 || num1 > 30 && num2 == 11){
            throw new RuntimeException("в " + num2 + " месяце порядковый номер дня не может быть больше " + 30);
        }
        if(num2 == 2 && num3%4 == 0 && num3/4%100 != 0 && num1 > 29){
            throw new RuntimeException("во 2 месяце " + num3 + " года порядковый номер дня не может быть больше 29");
        }
        if(num2 == 2 && (num3%4 != 0 || (num3%4 != 0 && num3/4%100 != 0)) && num1 > 28){
            throw new RuntimeException("во 2 месяце " + num3 + " года  порядковый номер дня не может быть больше 28");
        }
        
        return stroka1 + "." + stroka2 + "." + stroka3;
    }
    // =================================
    // функции номер телефона
    public static String [] functionNumberPhone(Scanner scan){
        System.out.println("введите ваш номер телефона:");
        String phoneNumber = scan.nextLine();
        String[] phoneNumberArray = phoneNumber.split("");

        if(phoneNumber.length() != 11){
            throw new NumberElementsException();
        }
        return phoneNumberArray;
    }

    public static void functionNumber(String [] arrayFullNumberPhone, Scanner scan){
        for (String items : arrayFullNumberPhone) {
            Integer symbol = Integer.parseInt(items);
        }
        
    }

    // --------------------------------------------------------------
    // функция пол
    public static String functionSex(Scanner scan){
        System.out.println("введите ваш пол:");
        String sex = scan.nextLine();
        if(!sex.equals("f") && !sex.equals("m")){
            throw new RuntimeException("укажите корректный пол - m или f");
        }
        return sex;
    }
}






