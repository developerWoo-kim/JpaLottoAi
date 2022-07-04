package lotto.lottoAi.model;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter
public class LottoRound {

    @Id @GeneratedValue
    @Column(name = "LOTTO_ROUND_ID")
    private Long id;

    private BigDecimal totSell = BigDecimal.ZERO;    //총 판매금액
    private String roundDate;    //추첨일
    private String whichRound;      //몇 회차
    private BigDecimal winnerMoney = BigDecimal.ZERO;    //1등 상금
    private BigDecimal allWinnersMoney = BigDecimal.ZERO;    //총 1등 당첨자들 합산금액
    private int firstNum;   //번호1
    private int secondNum;   //번호2
    private int thirdNum;   //번호3
    private int fourthNum;   //번호4
    private int fifthNum;   //번호5
    private int sixthNum;   //번호6
    private int bonusNum;   //보너스 번호

    public void createLottoRound(JsonObject object) {
        this.totSell = object.get("totSellamnt").getAsBigDecimal();
        this.roundDate = object.get("drwNoDate").getAsString();
        this.whichRound = object.get("drwNo").getAsString();
        this.winnerMoney = object.get("firstWinamnt").getAsBigDecimal();
        this.allWinnersMoney = object.get("firstAccumamnt").getAsBigDecimal();
        this.firstNum = object.get("drwtNo1").getAsInt();
        this.secondNum = object.get("drwtNo2").getAsInt();
        this.thirdNum = object.get("drwtNo3").getAsInt();
        this.fourthNum = object.get("drwtNo4").getAsInt();
        this.fifthNum = object.get("drwtNo5").getAsInt();
        this.sixthNum = object.get("drwtNo6").getAsInt();
        this.bonusNum = object.get("bnusNo").getAsInt();
    }

    // 이전 주의 당첨번호와 중복되는 번호가 있는지 판단하는 편의 메소드
    public void preNumDupleCheck(int week, int currIdx, List<LottoRound> lottoRound) {

    }

}
