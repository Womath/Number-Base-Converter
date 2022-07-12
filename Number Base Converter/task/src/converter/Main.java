package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            int source = 0;
            int target = 0;
            if (scanner.hasNextInt()) {
                source = scanner.nextInt();
                target = scanner.nextInt();
            } else {
                String s = scanner.nextLine();
                if (s.equals("/exit")) {
                    System.exit(0);
                } else {
                    System.out.println("Wrong input!");
                    break;
                }
            }
            scanner.nextLine();
            while (true) {
                System.out.println("Enter number in base " + source + " to convert to base " + target + " (To go back type /back)");
                String input = scanner.nextLine();
                if (input.equals("/back")) {
                    break;
                } else if (input.contains(".") || input.contains(",")){
                    Converter converter = new Converter(source, target, input);
                    System.out.print("Conversion result: ");
                    converter.convertToDecimal();
                    converter.convertFloatingNumber();
                } else {
                    Converter converter = new Converter(target, new BigInteger(input, source));
                    System.out.print("Conversion result: ");
                    converter.convertFrom();
                }


            }
        }
    }

}
