package amazonOA;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.
 *
 * Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.
 *
 * The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.
 *
 * Example 1:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2", "song3", "song4", "song8"],
 *    "Emma":  ["song5", "song6", "song7"]
 * },
 * songGenres = {
 *    "Rock":    ["song1", "song3"],
 *    "Dubstep": ["song7"],
 *    "Techno":  ["song2", "song4"],
 *    "Pop":     ["song5", "song6"],
 *    "Jazz":    ["song8", "song9"]
 * }
 *
 * Output: {
 *    "David": ["Rock", "Techno"],
 *    "Emma":  ["Pop"]
 * }
 *
 * Explanation:
 * David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
 * Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
 *
 * Example 2:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2"],
 *    "Emma":  ["song3", "song4"]
 * },
 * songGenres = {}
 *
 * Output: {
 *    "David": [],
 *    "Emma":  []
 * }
 */
public class FavoriteGenres {

    /*
    // Approach is simple: initialize a mapping of songsToGenre.
// Then, using our mapping, we iterate through each song (we don't know if songs have multiple genres, so we handle that here), and count up the genres while keeping a 'maximum count'.
// After that, we find out all of the songs that are equal to our maximum mapping.

Runtime analysis is as follows: Opening, we start off with O(Genres * MaxSongCount). Then, we follow up with O(User * (Song * Genres)). We know that looping through each song takes O(genre) time, as you must iterate through every single genre the song is part of. So, our solution is pretty rough here at O(USG).

As far as I can tell, there aren't any unnecessary steps. You MUST iterate through every single song to check out what genre they are part of, and we do this as efficient as possible (via a O(1) hash table lookup).

As for space, at the most, we store every single song, genre, node, and user. O(n * m + m * s), where n is the number of users, and m is the amount of genres, and s is the number of songs.
     */

    public Map<String, List<String>> getFavouriteGenres (Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        // name, genres
        Map<String, List<String>> result = new HashMap<>();

        // songName, genres
        Map<String, List<String>> songsToGenre = new HashMap<>();

        // Initialize songsToGenre
        for (String genre : genreMap.keySet()) {
            List<String> songList = genreMap.get(genre);
            for (String song : songList) {
                if(songsToGenre.containsKey(song)) {
                    songsToGenre.get(song).add(genre);   // add a new genre to the list.
                }
                else {
                    List<String> songGenres = new ArrayList<String>(); // initialize list of songs, and add to list
                    songGenres.add(genre);
                    songsToGenre.put(song, songGenres);
                }
            }
        }

        // Iterate through userMap, lookup song, and keep a running count for each genre that appears for each song.
        for (String user : userMap.keySet()) {
            List<String> favoriteSongs = userMap.get(user);

            // <Genre, Count>
            Map<String, Integer> genreCount = new HashMap<>();
            int maxCount = 0;
            List<String> favoriteGenres = new ArrayList<String>();

            for(String song : favoriteSongs) {
                if(songsToGenre.containsKey(song)) {
                    // Loop through every genre, iterate the count. While we iterate, we check if it's the max value.
                    List<String> genresInSong = songsToGenre.get(song);
                    for (String genre : genresInSong) {
                        if(genreCount.containsKey(genre)) {
                            genreCount.replace(genre, genreCount.get(genre) + 1); // just iterate by 1

                            // if the getCount is the same as the maxCount, go ahead and add it in to the list.
                            if(maxCount == genreCount.get(genre)) {
                                favoriteGenres.add(genre);
                            }
                            // otherwise, clear the favorite genre list, and update the list.
                            else if ( maxCount < genreCount.get(genre)) {
                                favoriteGenres.clear();
                                maxCount = genreCount.get(genre);
                                favoriteGenres.add(genre);
                            }
                        }
                        else {
                            genreCount.put(genre, 1);
                        }
                    }
                }
            }

            result.put(user, favoriteGenres);
        }

        return result;
    }







    /**
     * Time Complexity: O(u * s) where u is number of users and s is the number of songs
     * Space Complexity: O(s) where s is the number of songs in the list
     */
    /*

    public Map<String, List<String>> getFavouriteGenres(
            Map<String, List<String>> userSongs,
            Map<String, List<String>> songGenres) {

        if(userSongs == null || userSongs.size() == 0) return Collections.emptyMap();

        *//*Populating Users with empty Favourite Genres*//*
        Map<String, List<String>> favouriteGenres = new HashMap<>();
        userSongs.keySet().forEach(user -> favouriteGenres.put(user, new ArrayList<>()));

        if(songGenres == null || songGenres.size() == 0) {
            return favouriteGenres;
        }

        *//*Populating the Song and Genre in Map for Easy Access*//*
        Map<String, String> songGenreMap = new HashMap<>();
        songGenres.forEach((key, value) -> {
            for (String song : value) {
                songGenreMap.put(song, key);
            }
        });

        *//*For Every user get the songs listened and get the genres and No Of Times Listened*//*

        userSongs.forEach((user, songsListened) -> {
            Map<String, Integer> genreToFrequency = new HashMap<>();
            AtomicInteger favGenreCount = new AtomicInteger(0);

            *//*populate genreToFrequency map that is Genre to Number of times heard*//*
            songsListened.forEach(song -> {
                String genre = songGenreMap.get(song);
                int count = genreToFrequency.getOrDefault(genre, 0) + 1;
                genreToFrequency.put(genre, count);
                favGenreCount.set(Math.max(favGenreCount.get(), count));
            });

            *//*Add all genres that are equal to Favourite Genre Count*//*
            genreToFrequency.forEach((genre, count) -> {
                if(count == favGenreCount.get()) {
                    List<String> favGenreList = favouriteGenres.getOrDefault(user, new ArrayList<>());
                    favGenreList.add(genre);
                    favouriteGenres.put(user, favGenreList);
                }
            });
        });
        return favouriteGenres;
    }*/

    /*Test Cases*/
    public static void main(String[] args) {
        /* User has more than one favourite genre */
        Map<String, List<String>> userSongs = new HashMap<>();
        userSongs.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        userSongs.put("Emma", Arrays.asList("song5", "song6", "song7"));

        Map<String, List<String>> songGenres = new HashMap<>();
        songGenres.put("Rock", Arrays.asList("song1", "song3"));
        songGenres.put("Dubstep", Collections.singletonList("song7"));
        songGenres.put("Techno", Arrays.asList("song2", "song4"));
        songGenres.put("Pop", Arrays.asList("song5", "song6"));
        songGenres.put("Jazz", Arrays.asList("song8", "song9"));
        System.out.println(new FavoriteGenres().getFavouriteGenres(userSongs, songGenres));

        /* User has only one favourite genre */
        userSongs = new HashMap<>();
        userSongs.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8", "song10"));
        userSongs.put("Emma", Arrays.asList("song5", "song6", "song7"));

        songGenres = new HashMap<>();
        songGenres.put("Rock", Arrays.asList("song1", "song3", "song10"));
        songGenres.put("Dubstep", Collections.singletonList("song7"));
        songGenres.put("Techno", Arrays.asList("song2", "song4"));
        songGenres.put("Pop", Arrays.asList("song5", "song6"));
        songGenres.put("Jazz", Arrays.asList("song8", "song9"));
        System.out.println(new FavoriteGenres().getFavouriteGenres(userSongs, songGenres));

        /* When there is no genre info */
        userSongs = new HashMap<>();
        userSongs.put("David", Arrays.asList("song1", "song2"));
        userSongs.put("Emma", Arrays.asList("song3", "song4"));
        System.out.println(new FavoriteGenres().getFavouriteGenres(userSongs, Collections.emptyMap()));

        /* When there are no song and genre info */
        System.out.println(new FavoriteGenres().getFavouriteGenres(Collections.emptyMap(), Collections.emptyMap()));
    }
}
