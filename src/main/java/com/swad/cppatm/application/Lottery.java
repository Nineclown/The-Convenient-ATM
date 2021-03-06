package com.swad.cppatm.application;


import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;



/**
 * Lottery class
 */
public class Lottery {
    private int week;
    private int[] numbers = new int[6];

    /**
     * Lottery constructor
     *
     * @param week
     * @param numbers
     */
    public Lottery(int week, int[] numbers) {
        this.week = week;
        System.arraycopy(numbers, 0, this.numbers, 0, 6);
    }

    /**
     * Check lottery's result
     *
     * @return Lottery winning prize's price
     */
    public int checkResult() {
        String url = "http://lotto.kaisyu.com/api?method=check&numlist=[[" + numbers[0] + "," + numbers[1] + "," + numbers[2] + "," + numbers[3] + "," + numbers[4] + "," + numbers[5] + "]]&gno=" + week;
        int rank = 0;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            if ((inputLine = in.readLine()) != null) {
                rank = Integer.parseInt(inputLine.substring(2, 3));
            }

        } catch (IOException e) {
            //Network Error
            return 0;
        }

        switch (rank) {
            case 1:
                return 50000000;
            case 2:
                return 5000000;
            case 3:
                return 500000;
            case 4:
                return 50000;
            case 5:
                return 5000;
            default:
                return 0;
        }
    }
}
