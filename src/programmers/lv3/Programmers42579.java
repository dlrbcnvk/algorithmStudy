package programmers.lv3;

import java.util.*;

/**
 * 베스트앨범
 */
public class Programmers42579 {

    class Genre implements Comparable<Genre> {
        String genre;
        ArrayList<Song> songs;

        public Genre(String genre, ArrayList<Song> songs) {
            this.genre = genre;
            this.songs = songs;
        }

        public int totalPlays() {
            int plays = 0;
            for (Song song : this.songs) {
                plays += song.play;
            }
            return plays;
        }

        @Override
        public int compareTo(Genre genre) {
            if (this.totalPlays() > genre.totalPlays()) {
                return -1;
            }
            return 1;
        }
    }


    class Song implements Comparable<Song> {
        int id;
        String genre;
        int play;

        public Song(int id, String genre, int play) {
            this.id = id;
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Song song) {
            if (this.play > song.play) {
                return -1;
            } else if (this.play < song.play) {
                return 1;
            } else {
                if (this.id < song.id) {
                    return -1;
                } else if (this.id > song.id) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, ArrayList<Song>> songMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Song song = new Song(i, genre, play);
            if (songMap.containsKey(genre)) {
                ArrayList<Song> songs = songMap.get(genre);
                songs.add(song);
                songMap.put(genre, songs);
            } else {
                ArrayList<Song> songs = new ArrayList<>();
                songs.add(song);
                songMap.put(genre, songs);
            }
        }

        Set<String> genreSet = songMap.keySet();
        ArrayList<Genre> genreArray = new ArrayList<>();
        for (String genre : genreSet) {
            ArrayList<Song> songs = songMap.get(genre);
            Collections.sort(songs);
            Genre genreInstance = new Genre(genre, songs);
            genreArray.add(genreInstance);
        }
        Collections.sort(genreArray);

        ArrayList<Integer> result = new ArrayList<>();
        for (Genre genre : genreArray) {
            ArrayList<Song> songs = genre.songs;
            result.add(songs.get(0).id);
            if (songs.size() >= 2) {
                result.add(songs.get(1).id);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Programmers42579 programmers42579 = new Programmers42579();
        int[] solution = programmers42579.solution(
                new String[]{"pop", "pop", "pop", "rap", "rap"},
                new int[]{45, 50, 40, 60, 70}
        );

        for (int i : solution) {
            System.out.print(i + " ");
        }
    }
}
