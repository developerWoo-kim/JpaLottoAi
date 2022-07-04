package lotto.lottoAi.create.Service;

import lombok.RequiredArgsConstructor;
import lotto.lottoAi.create.repository.CreateLottoRepository;
import lotto.lottoAi.model.LottoOption;
import lotto.lottoAi.model.LottoRound;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService{

    private final CreateLottoRepository createLottoRepository;

    @Override
    public boolean optionApply(LottoOption lottoOption, int[] lottoNum) {
        System.out.println("설정된 옵션을 수행합니다.");
        boolean optionResult = true;

        if("Y".equals(lottoOption.getNumberOfExclusion())) {
            optionResult = numberOfExclusions(lottoNum, lottoOption);
        }

        if("Y".equals(lottoOption.getContinuityAppear())) {
            optionResult = continuityAppear(lottoNum);
        }

        if("Y".equals(lottoOption.getWinningNum())) {
            optionResult = winningNum(lottoNum);
        }

        if("Y".equals(lottoOption.getAllEven())) {
            optionResult = allEven(lottoNum);
        }

        if("Y".equals(lottoOption.getOddNum())) {
            optionResult = oddNum(lottoNum);
        }

        return optionResult;
    }

    @Override
    public boolean numberOfExclusions(int[] lottoNum, LottoOption lottoOption) {
        boolean optionResult = true;

        return optionResult;
    }

    @Override
    public boolean continuityAppear(int[] lottoNum) {
        boolean optionResult = true;
        List<LottoRound> allLottoRound = createLottoRepository.findAllLottoRound();

        for(int i = 0; i < allLottoRound.size(); i++) {


            allLottoRound.get(i).getFirstNum();
            allLottoRound.get(i).getSecondNum();
            allLottoRound.get(i).getThirdNum();
            allLottoRound.get(i).getFourthNum();
            allLottoRound.get(i).getFirstNum();
            allLottoRound.get(i).getFirstNum();
        }

        return true;
    }

    @Override
    public boolean winningNum(int[] lottoNum) {
        System.out.println("추첨번호가 기존 당첨번호와 동일한지 체크합니다.");
        boolean optionResult = true;
        List<LottoRound> allLottoRound = createLottoRepository.findAllLottoRound();

        for (LottoRound lottoRound : allLottoRound) {
            int winningNum[] = {
                    lottoRound.getFirstNum(), lottoRound.getSecondNum(),
                    lottoRound.getThirdNum(), lottoRound.getFourthNum(),
                    lottoRound.getFifthNum(), lottoRound.getSixthNum()
            };
            // 기존 당첨번호와 현재 추첨번호가 같으면 false를 리턴한다.
            if(Arrays.equals(lottoNum, winningNum)) {
                optionResult = Arrays.equals(lottoNum, winningNum);
            }
        }
        if(!optionResult) System.out.println("체킹되었습니다.");
        return optionResult;
    }

    // 모두 짝수 제외
    @Override
    public boolean allEven(int[] lottoNum) {
        System.out.println("추첨번호가 모두 짝수로 구성되어있는 체크합니다.");
        boolean optionResult = false;

        for (int lNum : lottoNum) {
            if(lNum % 2 == 1){
                optionResult = true;
            }
        }
        if(!optionResult) System.out.println("제외되었습니다.");
        return optionResult;
    }

    // 모두 홀수 제외
    @Override
    public boolean oddNum(int[] lottoNum) {
        System.out.println("추첨번호가 모두 홀수로 구성되어있는 체크합니다.");
        boolean optionResult = false;

        for (int lNum : lottoNum) {
            if(lNum % 2 != 1){
                optionResult = true;
            }
        }
        if(!optionResult) System.out.println("제외되었습니다.");
        return optionResult;
    }
}
