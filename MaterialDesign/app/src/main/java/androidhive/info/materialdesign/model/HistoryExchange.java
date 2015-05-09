package androidhive.info.materialdesign.model;

import java.util.ArrayList;

/**
 * Created by sonpx on 5/8/15.
 */
public class HistoryExchange {

    private String cardname;
    private String amount;
    private String exchangedate;
    private String status;

    public HistoryExchange(String cardname, String amount, String exchangedate, String status) {
        this.cardname = cardname;
        this.amount = amount;
        this.exchangedate = exchangedate;
        this.status = status;
    }

    public HistoryExchange() {

    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }









}
