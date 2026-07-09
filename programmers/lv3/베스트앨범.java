import java.util.*;

class Solution {
    static class Song implements Comparable<Song> {
        int id;
        int play;

        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song o) {
            if (this.play == o.play) {
                return Integer.compare(this.id, o.id); 
            }
            return Integer.compare(o.play, this.play);
        }
    }

    static class Genre implements Comparable<Genre> {
        String name;
        int totalPlay;
        List<Song> songs = new ArrayList<>();

        public Genre(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Genre o) {
            return Integer.compare(o.totalPlay, this.totalPlay);
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();


        for (int i = 0; i < genres.length; i++) {
            String gName = genres[i];
            int pCount = plays[i];

            Genre genre = genreMap.computeIfAbsent(gName, k -> new Genre(k));
            
            genre.totalPlay += pCount;
            genre.songs.add(new Song(i, pCount));
        }

        List<Genre> genreList = new ArrayList<>(genreMap.values());
        Collections.sort(genreList);

        List<Integer> bestAlbum = new ArrayList<>();

        for (Genre genre : genreList) {
            Collections.sort(genre.songs);

            int count = Math.min(genre.songs.size(), 2);
            for (int i = 0; i < count; i++) {
                bestAlbum.add(genre.songs.get(i).id);
            }
        }

        int[] answer = new int[bestAlbum.size()];
        for (int i = 0; i < bestAlbum.size(); i++) {
            answer[i] = bestAlbum.get(i);
        }

        return answer;
    }
}