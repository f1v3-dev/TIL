package com.nhnacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MovieList {

    // movieId, movie
    private final HashMap<Integer, Movie> movieList;

    public MovieList(String path) {

        // TODO: 파일 읽기
        CsvParser parser = new CsvParser(path);
        this.movieList = parser.getMovieList();
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movieList.values());
    }

    public List<Movie> findByTitleName(String searchName) {

        List<Movie> allMovies = findAll();
        List<Movie> findMovies = new ArrayList<>();


        while (true) {
            Movie findMovie = new Movie();
            int index = 0;
            if (searchName.matches(".*[가-힣]+.*")) {
                index = findIndexOfMovieByKoreanTitle(searchName, allMovies, findMovie);
            } else {
                index = findIndexOfMovieByTitle(searchName, allMovies, findMovie);
            }

            if (index < 0) {
                break;
            }

            findMovies.add(allMovies.get(index));
            allMovies.remove(index);
        }

        return findMovies;
    }

    public List<Movie> findByYear(String releaseYear) {
        List<Movie> allMovies = findAll();

        List<Movie> findMovies = new ArrayList<>();

        while (true) {
            Movie findByYearMovie = new Movie();
            int index = findIndexOfMovieByReleaseYear(releaseYear, allMovies, findByYearMovie);

            if (index < 0) {
                break;
            }

            findMovies.add(allMovies.get(index));
            allMovies.remove(index);
        }

        return findMovies;
    }

    private int findIndexOfMovieByReleaseYear(String releaseYear, List<Movie> findByReleaseYear, Movie findMovie) {
        int index;
        findByReleaseYear.sort(Comparator.comparing(Movie::getReleaseYear));
        index = Collections.binarySearch(findByReleaseYear, findMovie.findByReleaseYear(releaseYear),
                Comparator.comparing(Movie::getReleaseYear));
        return index;
    }

    private int findIndexOfMovieByTitle(String searchName, List<Movie> findByName, Movie findMovie) {
        int index;
        findByName.sort(Comparator.comparing(Movie::getTitle));
        index = Collections.binarySearch(findByName, findMovie.findByTitle(searchName),
                Comparator.comparing(Movie::getTitle));
        return index;
    }

    private int findIndexOfMovieByKoreanTitle(String searchName, List<Movie> findByName, Movie findMovie) {
        int index;
        findByName.sort(Comparator.comparing(Movie::getKoreanTitle));
        index = Collections.binarySearch(findByName, findMovie.findByKoreanTitle(searchName),
                Comparator.comparing(Movie::getKoreanTitle));
        return index;
    }
}
