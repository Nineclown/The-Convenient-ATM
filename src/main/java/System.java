import java.util.Date;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class System {
    private int cashAmount;
    private int selectedCardNumber;

    public void selectFunction(FunctionType function) {

    }

    public void enterAccountInfo(Bank bank, String accountNo) {

    }

    public void enterBill(int[] billAmount) {

    }

    public void enterBillAsDollar(int[] billAmount) {

    }

    public void enterPassword(int password) {

    }

    public void enterBillAmountToWithdraw(int cashAmount) {

    }

    public void enterBillAmountToWithdrawAsDollar(int cashAmount) {

    }

    public int[] calcBillAmount(int cashAmount) {
        return null;
    }

    public double getCurrency() {
        double currency = 0;

        String url = "https://free.currencyconverterapi.com/api/v5/convert?q=USD_KRW&compact=ultra";
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
                currency = Double.parseDouble(inputLine.substring(inputLine.indexOf(':')+1, inputLine.length()-1));
            }
        }catch(Exception e){
            
        }
        return currency;
    }

    public void enterCashAmountToTransfer(int cashAmount) {

    }

    public void enterTotalCashAmountToGet(int cashAmount) {

    }

    public void enterNumberOfUsers(int userNumber) {

    }

    public void enterPeriodToQuery(Date start, Date end) {

    }

    public void enterUserId(String userId) {

    }

    public void selectCard(String cardNumber) {

    }

    public void requestStopCard(String cardNumber) {

    }

    public static void main(String [] args) throws Exception {
    }
}
