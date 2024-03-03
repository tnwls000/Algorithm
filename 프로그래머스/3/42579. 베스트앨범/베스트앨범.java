import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genre = new HashMap<>();
        Map<String, PriorityQueue<Song>> play = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            if (genre.containsKey(genres[i])) {
                genre.put(genres[i], genre.get(genres[i]) + plays[i]);
            } else {
                genre.put(genres[i], plays[i]);
            }
            
            if (play.containsKey(genres[i])) {
                PriorityQueue<Song> pq = play.get(genres[i]);
                pq.add(new Song(plays[i], i));
                play.put(genres[i], pq);
            } else {
                PriorityQueue<Song> pq = new PriorityQueue<>((o1,o2) -> {
                    return o2.plays - o1.plays;
                });
                pq.add(new Song(plays[i], i));
                play.put(genres[i], pq);
            }
        }
        
        List<String> list = new ArrayList<>(genre.keySet());
        Collections.sort(list, (o1,o2) -> {
            return genre.get(o2) - genre.get(o1);
        });
        
        List<Integer> result = new ArrayList<>();
        for (String str : list) {
            PriorityQueue<Song> pq = play.get(str);
            result.add(pq.poll().originIdx); // 하나만 있으면 하나만 뽑음
            if (!pq.isEmpty()) result.add(pq.poll().originIdx); // 두 개 이상이면 하나 더 뽑음
        }
        
        return result.stream().filter(i -> i != -1).mapToInt(i->i).toArray();
    }
}

class Song {
    int plays;
    int originIdx;
    Song(int plays, int originIdx) {
        this.plays = plays;
        this.originIdx = originIdx;
    }
}