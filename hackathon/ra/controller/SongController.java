package hackathon.ra.controller;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.run.Console;
import hackathon.ra.service.SongService;

import java.util.Scanner;

public class SongController {

    public void management(Scanner scanner, Song[] songs, Singer[] singers) {

        SongService songService = new SongService();

        do {
            try {
                System.out.println("********** SONG-MANAGEMENT ********** \n" +
                        "1.Nhập vào số lượng bài hát cần thêm và nhập thông tin cần thêm mới (có validate dữ liệu nhập vào) \n" +
                        "2.Hiển thị danh sách tất cả bài hát đã lưu trữ \n" +
                        "3.Thay đổi thông tin bài hát theo mã id \n" +
                        "4.Xóa bài hát theo mã id \n" +
                        "5.Thoát");

                System.out.print("Nhập lựa chọn (1 - 5): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        songService.addSongs(scanner, singers, songs);
                        break;
                    case 2:
                        songService.displaySongs(songs);
                        break;
                    case 3:
                        songService.updateSong(scanner, singers, songs);
                        break;
                    case 4:
                        songService.deleteSong(scanner, singers, songs);
                        break;
                    case 5:
                        Console.exit("Thoát quản lý bài hát ... \n\n");
                        return;
                    default:
                        Console.message("Lựa chọn chỉ từ 1 đến 5 \n");
                }
            } catch (Exception e) {
                Console.error("SongService input choice: " + e.getMessage());
                System.out.println();
                scanner.nextLine();
            }
        } while (true);
    }

}
