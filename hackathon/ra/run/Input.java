package hackathon.ra.run;

import java.util.Scanner;

public class Input {

    public static int inputPositiveInt(Scanner scanner, String msg) {
        System.out.print(msg);
        int number;

        while (true) {
            try {
                number = scanner.nextInt();
                if (number > 0) {
                    return number;
                }
                Console.message(msg);
            }
            catch (Exception e) {
                scanner.nextLine();
                Console.message(msg);
            }
        }
    }

}
