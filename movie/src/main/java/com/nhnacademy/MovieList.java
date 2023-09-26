package com.nhnacademy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MovieList {

    // movieId, movie
    private final HashMap<Integer, Movie> movieList;

    //String -> 영화 제목, Integer -> movieId
    private Map<String, Integer> movieIndexList;

    public MovieList(String path) {
        this.movieList = new HashMap<>();
        this.movieIndexList = new HashMap<>();

        readFile(path);
    }

    /**
     *
     * @param path
     * 파일 경로를 받아서 영화파일을 읽어와서 movielist에
     * 추가하는 메서드
     */
    private void readFile(String path) {
        File file = new File(path);

        try {
            FileReader csvData = new FileReader(file);
            if (csvData.read() == -1) {
                throw new IOException("잘못된 경로입니다.");
            }

            BufferedReader br = new BufferedReader(csvData);


            String line = br.readLine();
            while ((line = br.readLine()) != null) {

                line = findDoubleComma(line);
                line = findBreakLine(line, br);

                List<String> dataList = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                StringTokenizer st = new StringTokenizer(line, ",");


                // 한 문자열에서 ","를 기준으로 자른다.
                while (st.hasMoreTokens()) {
                    String part = st.nextToken();

                    // "로 시작하는 문자열은 "로 끝나는 문자열을 찾을 때까지 계속 붙여준다.
                    StringTokenizer partToken = new StringTokenizer(part, "\"");
                    while (partToken.hasMoreTokens()) {
                        String partString = partToken.nextToken();
//                        System.out.println(partString);
                        if (part.charAt(0) == '\"') {
                            if (partString.equals(part.substring(1, part.length() - 1))) {
                                sb.append(partString);
                                dataList.add(sb.toString());
                                sb.setLength(0);
                                continue;
                            } else {
                                // " "안에 "가 존재하는 경우
                                if ((part.charAt(part.length() - 1) == '\"')) {
                                    sb.append(part);
                                    dataList.add(sb.toString());
                                    sb.setLength(0);
                                    while (partToken.hasMoreTokens()) {
                                        partToken.nextToken();
                                    }
                                    continue;
                                }
                                while ((part.charAt(part.length() - 1) != '\"')) {

                                    // " "안에 ,가 존재하는 경우
//                                    if (partString.charAt(partString.length() + 1) == '\"') {
//                                        sb.setLength(0);
//                                        sb.append(part);
//                                        while (partToken.hasMoreTokens()) {
//                                            partToken.nextToken();
//                                        }
//                                        part = st.nextToken();
//                                        sb.append(",").append(part);
//                                        break;
//                                    }

                                    if (partString.charAt(partString.length() - 1) == ' ' && part.charAt(part.length() - 1) == '\"') {
                                        sb.setLength(0);
                                        sb.append(part);
                                        while (partToken.hasMoreTokens()) {
                                            partToken.nextToken();
                                        }
                                        String temp = st.nextToken();
                                        sb.append(",").append(temp);
                                        break;
                                    } else {
                                        sb.append(partString);
                                        while (st.hasMoreTokens()) {
                                            while(partToken.hasMoreTokens()){
                                                sb.append(partToken.nextToken());
                                            }
                                            String temp = st.nextToken();
                                            sb.append(",").append(temp);
                                            if (temp.charAt(temp.length() - 1) == '\"') {
                                                break;
                                            }
                                        }
                                        break;
                                    }

                                }
                            }
                            dataList.add(sb.toString());
                            sb.setLength(0);
                            continue;
                        } else {
                            sb.append(partString);
                        }
                        dataList.add(sb.toString());
                        sb.setLength(0);
                    }
                }

                addMovie(dataList);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    private static String findDoubleComma(String line) {
        while (true) {
            int commaIndex = line.indexOf(",,");
            if (commaIndex == -1) {
                break;
            }
            line = line.substring(0, commaIndex + 1) + "NULL" + line.substring(commaIndex + 1);
        }
        return line;
    }

    private static String findBreakLine(String line, BufferedReader br) throws IOException {
        int breakLineIndex = line.indexOf("...");
        if (line.endsWith("...")) {
            line = line.substring(0, breakLineIndex + 3) + br.readLine().trim();
        }
        return line;
    }

    /**
     *
     * @param recodes
     * movie를 읽어서 각 의미별로 분리시켜서 movielist에 추하가는 메서드
     */
    private void addMovie(List<String> recodes) {
        // TODO: Key 값(Set<String>), Value Movie 객체

        Movie movie = new Movie();
        movie.addData(recodes);

        this.movieList.put(movie.getMovieId(), movie);
        if (!(movie.getKoreanTitle().equals("NULL"))) {
            this.movieIndexList.put(movie.getKoreanTitle(), movie.getMovieId());
        }
        this.movieIndexList.put(movie.getTitle(), movie.getMovieId());
    }

    public Movie searchMovie(String searchName) {
        int index = 0;

        if (movieIndexList.get(searchName) == null) {
            return null;
        } else {
            index = movieIndexList.get(searchName);
        }

        Movie searchedMovie = movieList.get(index);
        return searchedMovie;
    }
}
