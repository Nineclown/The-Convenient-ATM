import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Lottery {
    private int week = 0;
    private int[] numbers  = new int[6];

    public Lottery(int week, int[] numbers){
        this.week = week;
        for(int i = 0 ; i < 6 ; i++){
            this.numbers[i] = numbers[i];
        }
    }

    public int checkResult()
    {
        String url = "http://lotto.kaisyu.com/api?method=check&numlist=[[" + numbers[0]+ "," + numbers[1]+ ","+ numbers[2]+ ","+ numbers[3]+ ","+ numbers[4]+ ","+ numbers[5] + "]]&gno="+week;
        int rank = 0;
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
            // optional default is GET
            con.setRequestMethod("GET");
        
            //add request header 헤더를 만들어주는것.
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            if((inputLine = in.readLine()) != null){
                rank = Integer.parseInt(inputLine.substring(2,3));
            }
                
            
            
            
        }catch(Exception e){
            
        }
        
        switch(rank){
            case 1: return 50000000;
            case 2: return 5000000;
            case 3: return 500000;
            case 4: return 50000;
            case 5: return 5000;
            default : return 0;
        }
    }
}
