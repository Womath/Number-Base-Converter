package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Converter {
    int source;
    int target;
    BigInteger number;
    BigDecimal decimalNumber;
    String input;

    Converter (int target, BigInteger number) {
        this.target = target;
        this.number = number;
    }

    Converter (int source, int target, String input) {
        this.source = source;
        this.target = target;
        this.input = input;
    }

    public void convertFrom() {
        BigInteger tempBigInteger = number;
        ArrayList<String> result = new ArrayList<>();
        while (tempBigInteger.compareTo(BigInteger.ZERO) > 0) {
            int remainder = tempBigInteger.remainder(BigInteger.valueOf(target)).intValue();
            if (remainder > 9) {
                result.add(0, String.valueOf((char) (97 + remainder - 10)));
            } else {
                result.add(0, String.valueOf(remainder));
            }
            tempBigInteger = tempBigInteger.divide(BigInteger.valueOf(target));
        }

        for (String s : result) {
            System.out.print(s);
        }
        System.out.println();
    }

    public void convertFloatingNumber() {
        BigInteger tempInteger = this.number;
        BigDecimal tempDecimal = this.decimalNumber;
        ArrayList<String> result = new ArrayList<>();
        if (tempInteger.compareTo(BigInteger.ZERO) == 0) {
            result.add(0, "0");
        }
        while (tempInteger.compareTo(BigInteger.ZERO) > 0) {
            int remainder = tempInteger.remainder(BigInteger.valueOf(target)).intValue();
            if (remainder > 9) {
                result.add(0, String.valueOf((char) (97 + remainder - 10)));
            } else {
                result.add(0, String.valueOf(remainder));
            }
            tempInteger = tempInteger.divide(BigInteger.valueOf(target));
        }

        result.add(".");

        int counter = 0;
        while (counter < 5) {
            tempDecimal = tempDecimal.multiply(BigDecimal.valueOf(target));
            int digit = tempDecimal.intValue();
            if (digit > 9) {
                result.add(String.valueOf((char) (97 + digit - 10)));
            } else {
                result.add(String.valueOf(digit));
            }
            counter++;
            if (tempDecimal.compareTo(BigDecimal.ONE) >= 0) {
                tempDecimal = tempDecimal.subtract(BigDecimal.valueOf(digit));
            }
        }

        for (String s : result) {
            System.out.print(s);
        }
        System.out.println();

    }

    public void convertToDecimal() {
        String[] numbers;
        if (input.contains(",")) {
            numbers = input.split(",");
        } else {
            numbers = input.split("\\.");
        }
        String[] integerPart = numbers[0].split("");
        String[] floatingPart = numbers[1].split("");
        BigDecimal number = BigDecimal.ZERO;
        BigInteger integerNumber = BigInteger.ZERO;

        for (int i = integerPart.length; i > 0; i--) {
            try {
                int digit = Integer.parseInt(integerPart[Math.abs(i - integerPart.length)]);
                integerNumber = integerNumber.add(BigInteger.valueOf((long) (digit * Math.pow(source, i - 1))));
            } catch (NumberFormatException e) {
                char c = integerPart[Math.abs(i - integerPart.length)].charAt(0);
                int digit = c - 97 + 10;
                integerNumber = integerNumber.add(BigInteger.valueOf((long) (digit * Math.pow(source, i - 1))));
            }
        }

        for (int i = floatingPart.length; i > 0; i--) {
            try {
                int digit = Integer.parseInt(floatingPart[Math.abs(i - floatingPart.length)]);
                number = number.add(BigDecimal.valueOf(digit * Math.pow(source, i - floatingPart.length - 1)));
            } catch (NumberFormatException e) {
                char c = floatingPart[Math.abs(i - floatingPart.length)].charAt(0);
                int digit = c - 97 + 10;
                number = number.add(BigDecimal.valueOf(digit * Math.pow(source, i - floatingPart.length - 1)));
            }
        }
        this.number = integerNumber;
        decimalNumber = number;
    }



}
