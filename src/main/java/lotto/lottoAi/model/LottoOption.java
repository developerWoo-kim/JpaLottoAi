package lotto.lottoAi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class LottoOption {

    @Id @GeneratedValue
    @Column(name = "LOTTO_OPTION_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String numberOfExclusion;
    private String continuityAppear;
    private String winningNum;
    private String allEven;
    private String oddNum;

    private String numberOfExclusionArr;

}
