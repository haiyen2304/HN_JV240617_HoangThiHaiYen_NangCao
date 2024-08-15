package hackathon.ra.controller;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.run.Console;
import hackathon.ra.service.SingerService;

import java.util.Scanner;

public class SingerController {
    public void management(Scanner scanner, Singer[] singers, Song[] songs) {

        SingerService singerService = new SingerService();

        do {
            try {
                System.out.println("********** SINGER-MANAGEMENT ********** \n" +
                        "1.Nhập vào số lượng ca sĩ cần thêm và nhập thông tin cần thêm mới (có validate dữ liệu nhập vào) \n" +
                        "2.Hiển thị danh sách tất cả ca sĩ đã lưu trữ \n" +
                        "3.Thay đổi thông tin ca sĩ theo mã id \n" +
                        "4.Xóa ca sĩ theo mã id (kiểm tra xem nếu ca sĩ có bài hát thì không xóa được) \n" +
                        "5.Thoát");

                System.out.print("Nhập lựa chọn (1 - 5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        singerService.addSingers(scanner, singers);
                        break;
                    case 2:
                        singerService.displaySingers(singers);
                        break;
                    case 3:
                        singerService.updateSinger(scanner, singers);
                        break;
                    case 4:
                        singerService.deleteSinger(scanner, singers, songs);
                        break;
                    case 5:
                        Console.exit("Thoát quản lý ca sỹ ... \n\n");
                        return;
                    default:
                        Console.message("Lựa chọn chỉ từ 1 đến 5 \n");
                }
            } catch (Exception e) {
                Console.error("SingerService input choice: " + e.getMessage());
                System.out.println();
                scanner.nextLine();
            }
        } while (true);
    }
}
