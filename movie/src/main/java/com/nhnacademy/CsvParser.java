package com.nhnacademy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


/**
 * csv 파일을 읽어 HashMap에 저장
 * key: movieId, value: Movie
 */
public class CsvParser {

    private final HashMap<Integer, Movie> movieList = new HashMap<>();

    public CsvParser(String path) {
        readFile(path);

    }


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

                                    if (partString.charAt(partString.length() - 1) == ' ' &&
                                            part.charAt(part.length() - 1) == '\"') {
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
                                            while (partToken.hasMoreTokens()) {
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

    private void addMovie(List<String> recodes) {

        Movie movie = new Movie();
        movie.addData(recodes);

        this.movieList.put(movie.getMovieId(), movie);
    }

    public HashMap<Integer, Movie> getMovieList() {
        return movieList;
    }

}
