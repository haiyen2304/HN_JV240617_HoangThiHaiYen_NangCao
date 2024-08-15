package hackathon.ra.run;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.service.SearchService;
import hackathon.ra.service.SingerService;
import hackathon.ra.service.SongService;

import java.util.Scanner;

public class MusicManagement {

    public static final int size = 100;

    public static Singer[] singers = new Singer[size];
    public static int singersIndex = 0;

    public static Song[] songs = new Song[size];
    public static int songsIndex = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SingerService singerService = new SingerService();
        SongService songService = new SongService();
        SearchService searchService = new SearchService();

        do {
            try {
                System.out.println("********** MUSIC-MANAGEMENT ********** \n" +
                        "1. Quản lý ca sĩ \n" +
                        "2. Quản lý bài hát \n" +
                        "3. Tìm kiếm bài hát \n" +
                        "4. Thoát ");

                System.out.print("Nhập lựa chọn (1 - 4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Console.message("(1). Quản lý ca sĩ ... \n\n");
                        singerService.management(scanner, singers, songs);
                        break;
                    case 2:
                        Console.message("(2). Quản lý bài hát ... \n\n");
                        songService.management(scanner, songs, singers);
                        break;
                    case 3:
                        Console.message("(3). Tìm kiếm bài hát ... \n\n");
                        searchService.management(scanner, singers, songs);
                        break;
                    case 4:
                        Console.exit("Thoát chương trình ... ");
                        scanner.close();
                        return;
                    default:
                        Console.message("Lựa chọn chỉ từ 1 đến 4 \n");
                }
            } catch (Exception e) {
                Console.error("MusicManagement input choice: " + e.getMessage());
                System.out.println();
                scanner.nextLine();
            }
        } while (true);
    }
}
