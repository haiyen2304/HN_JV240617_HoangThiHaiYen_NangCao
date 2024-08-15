package hackathon.ra.model;

import hackathon.ra.run.Console;

import java.util.Scanner;

public class Singer {
    private static int idCounter = 1; // Counter to generate unique singerId

    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender; // true -> male, false -> female
    private String genre;

    public Singer() {
    }

    public Singer(int singerId,String singerName, int age, String nationality, boolean gender, String genre) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    // Getter và Setter
    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Tuổi phải lớn hơn 0 ... ");
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void inputData(Scanner scanner) {
        singerId = idCounter++;
        singerName = inputSingerName(scanner);
        age = inputAge(scanner);
        nationality = inputNationality(scanner);
        gender = inputGender(scanner);
        genre = inputGenre(scanner);
    }

    private String inputSingerName(Scanner scanner) {

//        scanner.nextLine();

        System.out.print("Nhập tên ca sỹ: ");
        String newSingerName;

        while (true) {
            try {
                newSingerName = scanner.nextLine().trim();
                if (!newSingerName.isEmpty()) {
                    return newSingerName;
                }
                Console.message("Nhập tên ca sỹ: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private int inputAge(Scanner scanner) {
        System.out.print("Nhập tuổi ca sỹ: ");
        int newAge;

        while (true) {
            try {
                newAge = scanner.nextInt();
                if (newAge > 0 && newAge <= 120) {
                    return newAge;
                }
                Console.message("Nhập tuổi ca sỹ: ");
            }
            catch (Exception e) {
                Console.message("Nhập tuổi ca sỹ: ");
                scanner.nextLine();
            }
        }
    }

    private String inputNationality(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nhập quốc tịch ca sỹ: ");
        String newNationality;

        while (true) {
            try {
                newNationality = scanner.nextLine().trim();
                if (!newNationality.isEmpty()) {
                    return newNationality;
                }
                Console.message("Nhập quốc tịch ca sỹ: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private boolean inputGender(Scanner scanner) {
        System.out.print("Nhập giới tính ca sỹ (true-nam / false-nữ): ");
        boolean newGender;

        while (true) {
            try {
                newGender = scanner.nextBoolean();
                return newGender;
            }
            catch (Exception e) {
                scanner.nextLine();
                Console.message("Nhập tính ca sỹ (true-nam / false-nữ): ");
            }
        }
    }

    private String inputGenre(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nhập thể loại hát: ");
        String newGenre;

        while (true) {
            try {
                newGenre = scanner.nextLine().trim();
                if (!newGenre.isEmpty()) {
                    return newGenre;
                }
                Console.message("Nhập thể loại hát: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public void displayData() {
        System.out.printf("| %-10s | %-20s | %-5d | %-20s | %-8s | %-15s |\n",
                singerId, singerName, age, nationality, gender ? "Nam" : "Nữ", genre);
    }

}
