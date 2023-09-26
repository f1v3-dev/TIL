package com.nhnacademy;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String path = "src/main/resources/Movie.csv"; // 파일 경로

        MovieList movieList = new MovieList(path); // 파일 읽기
        System.out.println(path + "파일을 읽었습니다.");


        // TODO: 사용자 검색하는 부분 구현
        String input;
        System.out.println("====================================");
        while (true) {
            System.out.println();

            System.out.println("1. 영화 제목으로 검색하기");
            System.out.println("2. 영화 년도로 검색하기");
            System.out.print("검색 옵션(숫자)를 입력하세요 (종료 : 0): ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 1:
                    System.out.println("검색할 영화 제목을 입력하세요 (종료: 엔터)");
                    input = sc.nextLine();

                    if (input.isEmpty()) {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }

                    findByTitle(input, movieList);
                    break;

                case 2:
                    System.out.println("검색할 영화 년도를 입력하세요 (종료: 엔터)");
                    input = sc.nextLine();

                    if (input.isEmpty()) {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }

                    findByYear(input, movieList);
                    break;

                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }

        }

    }

    private static void findByYear(String year, MovieList movieList) {
        List<Movie> searchedMovies = movieList.findByYear(year);

        if (searchedMovies.isEmpty()) {
            System.out.println("해당 년도에 개봉한 영화가 없습니다.");
            return;
        }

        int count = 1;
        for (Movie searchedMovie : searchedMovies) {
            System.out.println((count++) + ": " + searchedMovie.getKoreanTitle() + " (" + searchedMovie.getTitle() + ")");

        }
    }

    public static void findByTitle(String title, MovieList movieList) {
        List<Movie> searchedMovie = movieList.findByTitleName(title);

        if (searchedMovie == null) {
            System.out.println("찾으시는 영화가 없습니다.");

        } else {
            System.out.println("[영화 정보]");
            for (Movie movie : searchedMovie) {
                System.out.println(movie);
            }
        }

    }

}