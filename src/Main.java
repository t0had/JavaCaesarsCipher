import java.util.Locale;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] output = {};
        String pathInPutFile;
        String pathOutPutFile;
        String buffer = "";
        int shift;

        System.out.print("Введите путь к входному файлу: ");
        try {
            pathInPutFile = in.next();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathInPutFile))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                buffer = buffer + line + "\n";
                line = bufferedReader.readLine();
            }
            buffer = buffer.toLowerCase();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.print("Введите путь к выходному файлу: ");
        try {
            pathOutPutFile = in.next();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Введите значение сдвига элементов: ");
        try {
            shift = in.nextInt();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Выберите метод:\n1 - шифрование текста\n2 - дешифрование текста");
        try{
            switch(in.nextInt()) {
                case 1:
                    try (FileWriter fileWriter = new FileWriter(pathOutPutFile)) {
                        fileWriter.write(Encryption(buffer.toCharArray(), shift));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return;
                    }
                    System.out.println("Шифрование завершено! Файл с шифрованным текстом находится в данном файле: " + pathOutPutFile);
                    break;
                case 2:
                    try (FileWriter fileWriter = new FileWriter(pathOutPutFile)) {
                        fileWriter.write(Decryption(buffer.toCharArray(), shift));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return;
                    }
                    System.out.println("Дешифрование завершено! Файл с дешифрованным текстом находится в данном файле: " + pathOutPutFile);
                    break;
                default:
                    break;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static char[] Encryption(char[] inPut, int shift){
        char[] outPut = inPut;
        char[] ruAlphabet = {
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
                'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
                'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
        };
        char[] enAlphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };
        for (int i = 0; i < outPut.length - 1; i++){
            for(int r = 0; r < ruAlphabet.length; r++){
                if(outPut[i] == ruAlphabet[r]){
                    int newPos = (r + shift) % ruAlphabet.length;
                    if (newPos < 0){
                        newPos += ruAlphabet.length;
                    }
                    outPut[i] = ruAlphabet[newPos];
                    break;
                }
            }
            for(int e = 0; e < enAlphabet.length; e++){
                if(outPut[i] == enAlphabet[e]){
                    int newPos = (e + shift) % enAlphabet.length;
                    if (newPos < 0){
                        newPos += enAlphabet.length;
                    }
                    outPut[i] = enAlphabet[newPos];
                    break;
                }
            }
        }
        return outPut;
    }
    public static char[] Decryption(char[] inPut, int shift){
        char[] outPut = inPut;
        char[] ruAlphabet = {
                'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й',
                'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
                'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
        };
        char[] enAlphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };
        for (int i = 0; i < outPut.length; i++){
            for(int r = 0; r < ruAlphabet.length; r++){
                if(outPut[i] == ruAlphabet[r]){
                    int newPos = (r - shift) % ruAlphabet.length;
                    if (newPos < 0){
                        newPos += ruAlphabet.length;
                    }
                    outPut[i] = ruAlphabet[newPos];
                    break;
                }
            }
            for(int e = 0; e < enAlphabet.length; e++){
                if(outPut[i] == enAlphabet[e]){
                    int newPos = (e - shift) % enAlphabet.length;
                    if (newPos < 0){
                        newPos += enAlphabet.length;
                    }
                    outPut[i] = enAlphabet[newPos];
                    break;
                }
            }
        }
        return outPut;
    }
}