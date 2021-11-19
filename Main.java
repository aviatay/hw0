import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    /**
     * Prints a message according to a given grade.
     *
     * It is guaranteed that the grade is within the range [0, 100].
     *
     * @param grade The grade
     */
    public static void gradeMessage(int grade) {
        int newGrade = grade/10;
        switch (newGrade){
            case 10:
                System.out.println("Excellent");
                break;
            case 9:
                System.out.println("Great");
                break;
            case 8:
                System.out.println("Very Good");
                break;
            case 7:
                System.out.println("Good");
                break;
            default:
                System.out.println("OK");
        }
    }

    /**
     * Compresses a given string.
     *
     * The compression process is done by replacing a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     *
     * It is guaranteed that the string contains only letters (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        String compressedString = "";
        int countLetter = 0;
        for (int i=0; i< stringToCompress.length(); i++){
            countLetter++;
            if ( (i+1 >= stringToCompress.length()) || (stringToCompress.charAt(i) != stringToCompress.charAt(i+1) )) {
                compressedString = compressedString + stringToCompress.charAt(i) + countLetter;
                countLetter = 0;
            }
        }
        return compressedString;
    }

    /**
     * Decompresses a given string.
     *
     * The decompression process is done by duplicating each sequence of characters
     * according to the number which appears after the sequence.
     *
     * It is guaranteed that the string is a legal compressed string.
     *
     * @param compressedString The string to decompress
     * @return The decompressed string
     */

    public static String decompressString(String compressedString) {
        String decompressedString = "";
        int startStr = 0;
        int endStr = -1;
        int endInt = 0;
        int asciiNum = 0;
        String newSubstring;
        int fullNum=0;
        for (int i=0; i < compressedString.length() ; i++) {
            endStr++;
            int charNum = compressedString.charAt(i);
            while (charNum < 'A') {
                asciiNum = charNum - '0';
                fullNum = fullNum*10 + asciiNum;
                if (i < (compressedString.length() - 1)){
                    i++;
                    charNum = compressedString.charAt(i);
                }
                else{
                    break;
                }
            }
            endInt = i;
            newSubstring = compressedString.substring(startStr, endStr);
            for (int j=0; j<fullNum; j++){
                decompressedString = decompressedString + newSubstring;
                if ((endInt+1 < compressedString.length()) && (j == fullNum - 1)){
                    startStr= endInt;
                    endStr = endInt;
                    endInt++;
                }
            }
            fullNum = 0;
        }
        return decompressedString;
    }

    /**
     * Calculates the tax of a given salary.
     *
     * The tax is calculated according to the tax brackets method.
     * The tax is rounded up to two decimal places.
     *
     * @param salary The salary
     * @return The tax for the given salary
     */

    public static double calculateTax(int salary) {
        double tax = 0.0;

        int newSalary = salary/5000;
        switch (newSalary){
            case 0:
                tax = 0.1 * salary;
                break;
            case 1:
                tax = 500 + (salary - 5000)*0.14;
                break;
            case 2:
                tax = 500 + 700 + (salary - 10000)*0.2;
                break;
            case 3:
                tax = 500 + 700 + 1000 + (salary - 15000)*0.31;
                break;
            case 4:
                tax = 500 + 700 + 1000 + 1550 + (salary - 20000)*0.35;
                break;
            default:
                tax = 500 + 700 + 1000 + 1550 + 1750 + (salary - 25000)*0.5;
        }
        return tax;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Tests for part A
        System.out.println("---------- Tests for part A ----------");
        int numberOfGrades = scanner.nextInt();
        for (int i = 0; i < numberOfGrades; i++) {
            int grade = scanner.nextInt();
            gradeMessage(grade);
        }

        // Tests for part B1
        System.out.println("\n---------- Tests for part B1 ----------");
        int numberOfStringsToCompress = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfStringsToCompress; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            System.out.println("The compressed version of " + stringToCompress + " is " + compressedString);
        }

        // Tests for part B2
        System.out.println("\n---------- Tests for part B2 ----------");
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " + compressedString + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        System.out.println("\n---------- Tests for parts B1 and B2 ----------");
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            boolean isEqual = stringToCompress.equals(decompressedString);
            System.out.println("decompress(compress(" + stringToCompress + ")) == " + stringToCompress + "? " + isEqual);
        }

        // Tests for part C
        System.out.println("\n---------- Tests for part C ----------");
        int numberOfSalaries = scanner.nextInt();
        for (int i = 0; i < numberOfSalaries; i++) {
            int salary = scanner.nextInt();
            double tax = calculateTax(salary);
            System.out.println("The tax for salary of " + salary + "₪ is " + tax + "₪");
        }
    }
}
