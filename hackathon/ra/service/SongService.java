package hackathon.ra.service;

import hackathon.ra.model.Singer;
import hackathon.ra.model.Song;
import hackathon.ra.run.Console;
import hackathon.ra.run.Input;
import hackathon.ra.run.MusicManagement;

import java.util.Objects;
import java.util.Scanner;

public class SongService {

    public void addSongs(Scanner scanner,Singer[] singers ,Song[] songs) {
        int quantity = Input.inputPositiveInt(scanner, "Nhập số lượng bài hát cần thêm: ");

        for(int i = 0; i < quantity; i++) {
            Console.message("Nhập bài hát thứ " + MusicManagement.songsIndex + "\n");
            Song song = new Song();
            song.inputData(scanner, singers);
            songs[MusicManagement.songsIndex] = song;
            Console.message("Nhập bài hát thứ " + MusicManagement.songsIndex + " ... \n");
            MusicManagement.songsIndex = MusicManagement.songsIndex + 1;
        }
    }

    public void displaySongs(Song[] songs) {
        System.out.printf("| %-10s | %-30s | %-30s | %-20s | %-20s | %-30s | %-10s |\n",
                "Song ID", "Song Name", "Descriptions", "Singer", "Song Writer", "Created Date", "Status");

        for (int i = 0; i < MusicManagement.songsIndex; i++) {
            songs[i].displayData();
        }
    }

    public void updateSong(Scanner scanner, Singer[] singers, Song[] songs) {
        System.out.print("Nhập id bài hát cần sửa: ");
        String idUpdate = scanner.nextLine();

        int indexUpdate = -1;
        for (int i = 0; i < MusicManagement.songsIndex; i++) {
            if (Objects.equals(songs[i].getSongId(), idUpdate)) {
                indexUpdate = i;
                break;
            }
        }

        if (indexUpdate == -1) {
            Console.message("Không tim thấy bài hát có id = " + idUpdate + "\n");
        } else {
            Song song = new Song();
            Console.message("Nhập lại thông tin cho bài hát \n");
            song.inputDataUpdate(scanner, singers);
            song.setSongId(songs[indexUpdate].getSongId());
            song.setCreatedDate(songs[indexUpdate].getCreatedDate());
            songs[indexUpdate] = song;
            Console.message("Đã cập nhật xong \n");
        }
    }

    public void deleteSong(Scanner scanner, Singer[] singers, Song[] songs) {
        System.out.print("Nhập id bài hát cần xóa: ");
        String idDelete = scanner.nextLine();

        int indexDelete = -1;
        for (int i = 0; i < MusicManagement.songsIndex; i++) {
            if (Objects.equals(songs[i].getSongId(), idDelete)) {
                indexDelete = i;
                break;
            }
        }

        if (indexDelete == -1) {
            Console.message("Không tim thấy bài hát có id = " + idDelete + "\n");
        } else {
            for (int i = indexDelete; i < MusicManagement.songsIndex; i++) {
                songs[i] = songs[i + 1];
            }
            songs[MusicManagement.songsIndex] = null;
            MusicManagement.songsIndex = MusicManagement.songsIndex - 1;
            Console.message("Đã xóa xong \n");
        }
    }
}

