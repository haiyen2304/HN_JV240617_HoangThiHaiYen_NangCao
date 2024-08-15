package hackathon.ra.service;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.run.Console;
import hackathon.ra.run.MusicManagement;

import java.util.Arrays;
import java.util.Scanner;


public class SearchService {
    public void management(Scanner scanner, Singer[] singers, Song[] songs) {
        do {
            try {
                System.out.println("********** SEARCH-MANAGEMENT ********** \n" +
                        "1.Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại \n" +
                        "2.Tìm kiếm ca sĩ theo tên hoặc thể loại \n" +
                        "3.Hiển thị danh sách bài hát theo thứ tự tên tăng dần \n" +
                        "4.Hiển thị 10 bài hát được đăng mới nhất \n" +
                        "5.Thoát");

                System.out.print("Nhập lựa chọn (1 - 5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên ca sỹ hoặc thể loại: ");
                        scanner.nextLine();
                        String textSearchSong = scanner.nextLine().trim().toLowerCase();
                        System.out.printf("| %-10s | %-30s | %-30s | %-20s | %-20s | %-30s | %-10s |\n",
                                "Song ID", "Song Name", "Descriptions", "Singer", "Song Writer", "Created Date", "Status");
                        for(Song s : songs){
                            if(s !=null){
                                if(s.getSinger().getSingerName().toLowerCase().contains(textSearchSong)||
                                   s.getSinger().getGenre().toLowerCase().contains(textSearchSong)){
                                   s.displayData();
                                }
                            }
                        }
                    break;


                    case 2:
                        System.out.print("Nhập tên ca sỹ hoặc thể loại: ");
                        scanner.nextLine();
                        String textSearchSinger = scanner.nextLine().trim().toLowerCase();

                        System.out.printf("| %-10s | %-20s | %-5s | %-20s | %-8s | %-15s |\n",
                                "Singer ID", "Singer Name", "Age", "Nationality", "Gender", "Genre");

                        for(Singer singer : singers) {
                            if (singer != null) {
                                if (singer.getSingerName().toLowerCase().contains(textSearchSinger)  ||
                                        singer.getGenre().toLowerCase().contains(textSearchSinger)
                                ) {
                                   singer.displayData();
                                }
                            }
                        }
                        break;
                    case 3:
                        Song[] sortedSongs = new Song[MusicManagement.songsIndex  + 1];
                        for (int i = 0; i < MusicManagement.songsIndex; i++) {
                            sortedSongs[i] = songs[i];
                        }

                        for (int i = 0; i < MusicManagement.songsIndex - 1; i++) {
                            int minIndex = i;
                            for (int j = i + 1; j < MusicManagement.songsIndex; j++) {
                                //j-min
                                if (sortedSongs[j].getSongName().compareToIgnoreCase(sortedSongs[minIndex].getSongName()) < 0) {
                                    minIndex = j;
                                }
                            }
                            // Hoán đổi các phần tử
                            Song temp = sortedSongs[minIndex];
                            sortedSongs[minIndex] = sortedSongs[i];
                            sortedSongs[i] = temp;
                        }

                        System.out.printf("| %-10s | %-30s | %-30s | %-20s | %-20s | %-30s | %-10s |\n",
                                "Song ID", "Song Name", "Descriptions", "Singer", "Song Writer", "Created Date", "Status");

                        for(Song song : sortedSongs) {
                            if (song != null) {
                                song.displayData();
                            }
                        }

                        break;
                    case 4:
                        int start = MusicManagement.songsIndex - 1 - 10;
                        if (start < 0) {
                            start = 0;
                        }
                        System.out.printf("| %-10s | %-30s | %-30s | %-20s | %-20s | %-30s | %-10s |\n",
                                "Song ID", "Song Name", "Descriptions", "Singer", "Song Writer", "Created Date", "Status");

                        for (int i = start; i < MusicManagement.songsIndex; i++) {
                            songs[i].displayData();
                        }
                        break;
                    case 5:
                        Console.exit("Thoát quản lý search ... \n\n");
                        return;
                    default:
                        Console.message("Lựa chọn chỉ từ 1 đến 5 \n");
                }
            } catch (Exception e) {
                Console.error("SearchService input choice: " + e.getMessage());
                System.out.println();
                scanner.nextLine();
            }
        } while (true);
    }
}
