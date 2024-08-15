package hackathon.ra.service;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.run.Console;
import hackathon.ra.run.Input;
import hackathon.ra.run.MusicManagement;

import java.util.Scanner;

public class SingerService {
    public void management(Scanner scanner, Singer[] singers, Song[] songs) {
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
                        int quantity = Input.inputPositiveInt(scanner, "Nhập số lượng ca sỹ cần thêm: ");
                        scanner.nextLine();

                        for(int i = 0; i < quantity; i++) {
                            Console.message("Nhập ca sỹ thứ " + MusicManagement.singersIndex + "\n");
                            Singer singer = new Singer();
                            singer.inputData(scanner);
                            singers[MusicManagement.singersIndex] = singer;
                            Console.message("Nhập xong ca sỹ thứ " + MusicManagement.singersIndex + " ... \n");
                            MusicManagement.singersIndex = MusicManagement.singersIndex + 1;
                        }
                        break;
                    case 2:
                        System.out.printf("| %-10s | %-20s | %-5s | %-20s | %-8s | %-15s |\n",
                                "Singer ID", "Singer Name", "Age", "Nationality", "Gender", "Genre");

                        for (int i = 0; i < MusicManagement.singersIndex; i++) {
                            singers[i].displayData();
                        }
                        break;
                    case 3:
                        int idUpdate = Input.inputPositiveInt(scanner, "Nhập id ca sỹ cần sửa: ");

                        int indexUpdate = -1;
                        for (int i = 0; i < MusicManagement.singersIndex; i++) {
                            if (singers[i].getSingerId() == idUpdate) {
                                indexUpdate = i;
                                break;
                            }
                        }

                        if (indexUpdate == -1) {
                            Console.message("Không tim thấy ca sỹ có id = " + idUpdate + "\n");
                        } else {
                            Singer singer = new Singer();
                            scanner.nextLine();
                            Console.message("Nhập lại thông tin cho ca sỹ \n");
                            singer.inputData(scanner);
                            singer.setSingerId(idUpdate);
                            singers[indexUpdate] = singer;
                            Console.message("Đã cập nhật xong \n");
                        }
                        break;
                    case 4:
                        int idDelete = Input.inputPositiveInt(scanner, "Nhập id ca sỹ cần xóa: ");

                        boolean findSong = false;
                        for (Song song: songs) {
                            if (song != null) {
                                if (idDelete == song.getSinger().getSingerId()) {
                                    Console.message("Ca sỹ này có bài hát nên không xóa được\n");
                                    findSong = true;
                                    break;
                                }
                            }
                        }
                        if (findSong) {
                            break;
                        }

                        int indexDelete = -1;
                        for (int i = 0; i < MusicManagement.singersIndex; i++) {
                            if (singers[i].getSingerId() == idDelete) {
                                indexDelete = i;
                                break;
                            }
                        }

                        if (indexDelete == -1) {
                            Console.message("Không tim thấy ca sỹ có id = " + idDelete + "\n");
                        } else {
                            for (int i = indexDelete; i < MusicManagement.singersIndex; i++) {
                                singers[i] = singers[i + 1];
                            }
                            singers[MusicManagement.singersIndex] = null;
                            MusicManagement.singersIndex = MusicManagement.singersIndex - 1;
                            Console.message("Đã xóa xong \n");
                        }
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
