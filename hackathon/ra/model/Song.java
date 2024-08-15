package hackathon.ra.model;

import hackathon.ra.run.Console;
import hackathon.ra.run.MusicManagement;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Song {

    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private Date createdDate;
    private boolean songStatus;

    public Song() {}

    public Song(String songId, String songName, String descriptions, Singer singer, String songWriter, Date createdDate, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.createdDate = createdDate;
        this.songStatus = songStatus;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public void inputData(Scanner scanner, Singer[] singers) {
        songId = inputSongId(scanner);
        songName = inputSongName(scanner);
        descriptions = inputDescriptions(scanner);
        singer = inputSinger(scanner, singers);
        songWriter = inputSongWriter(scanner);
        songStatus = inputSongStatus(scanner);
        createdDate = new Date();
    }

    public void inputDataUpdate(Scanner scanner, Singer[] singers) {
        songName = inputSongName(scanner);
        descriptions = inputDescriptions(scanner);
        singer = inputSinger(scanner, singers);
        songWriter = inputSongWriter(scanner);
        songStatus = inputSongStatus(scanner);
    }

    private Singer inputSinger(Scanner scanner, Singer[] singers) {
        Console.message("Id các ca sỹ: ");
        for (Singer singer: MusicManagement.singers) {
            if (singer != null) {
                Console.message(singer.getSingerId() + " ");
            }
        }
        System.out.println();

        System.out.print("Nhập id ca sỹ bạn chọn: ");
        int idSinger;

        while (true) {
            try {
                idSinger = scanner.nextInt();
                for (Singer singer: singers) {
                    if (idSinger == singer.getSingerId()) {
                        return singer;
                    }
                }
                Console.message("Nhập id ca sỹ bạn chọn: ");
            }
            catch (Exception e) {
                scanner.nextLine();
                Console.message("Nhập id ca sỹ bạn chọn: ");
            }
        }
    }

    public String inputSongId(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nhập mã bài hát ('S' + XXX): ");
        String newSongId;
        Pattern pattern = Pattern.compile("^S\\w{3}$");

        while (true) {
            try {
                newSongId = scanner.nextLine().trim();
                Matcher matcher = pattern.matcher(newSongId);
                if (matcher.matches()) {

                    boolean check=false;
                    for (Song song: MusicManagement.songs) {
                        if (song != null) {
                            if (song.getSongId().equals(newSongId)) {
                                 check=true;
                                 break;
                            }
                        }
                    }

                    if(check){
                        Console.message("Mã bài hát đã bị trùng !!! \n");
                    }else {
                        return newSongId;
                    }
                }
                Console.message("Nhập mã bài hát ('S' + XXX): ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private String inputSongName(Scanner scanner) {
        System.out.print("Nhập tên bài hát: ");
        String newSongName;

        while (true) {
            try {
                newSongName = scanner.nextLine().trim();
                if (!newSongName.isEmpty()) {
                    return newSongName;
                }
                Console.message("Nhập tên bài hát: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private String inputDescriptions(Scanner scanner) {
        System.out.print("Nhập mô tả bài hát: ");
        String newDescriptions;

        while (true) {
            try {
                newDescriptions = scanner.nextLine().trim();
                if (!newDescriptions.isEmpty()) {
                    return newDescriptions;
                }
                Console.message("Nhập mô tả bài hát: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private String inputSongWriter(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nhập tác giả bài hát: ");
        String newSongWriter;

        while (true) {
            try {
                newSongWriter = scanner.nextLine().trim();
                if (!newSongWriter.isEmpty()) {
                    return newSongWriter;
                }
                Console.message("Nhập tác giả bài hát: ");
            }
            catch (Exception e) {
                Console.error(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private boolean inputSongStatus(Scanner scanner) {
        System.out.print("Nhập trạng thái (true-hát / false-cấm): ");
        boolean newSongStatus;

        while (true) {
            try {
                newSongStatus = scanner.nextBoolean();
                return newSongStatus;
            }
            catch (Exception e) {
                scanner.nextLine();
                Console.message("Nhập trạng thái (true-Hát / false-Cấm): ");
            }
        }
    }



    public void displayData() {
        System.out.printf("| %-10s | %-30s | %-30s | %-20s | %-20s | %-30s | %-10s |\n",
                songId,
                songName,
                descriptions != null ? descriptions : "N/A",
                singer != null ? singer.getSingerName() : "N/A",
                songWriter,
                createdDate.toString(),
                songStatus ? "Hát" : "Cấm");
    }

}
