package lotto.lottoAi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Lotto {

    @Id @GeneratedValue
    @Column(name = "LOTTO_ID")
    private Long id;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    @Override
    public String toString() {
        return "Lotto{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", member=" + member +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", num3=" + num3 +
                ", num4=" + num4 +
                ", num5=" + num5 +
                ", num6=" + num6 +
                '}';
    }
}
