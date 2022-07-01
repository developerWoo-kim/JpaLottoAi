package lotto.lottoAi.create.Service;

import lombok.RequiredArgsConstructor;
import lotto.lottoAi.create.repository.CreateLottoRepository;
import lotto.lottoAi.model.LottoOption;
import lotto.lottoAi.model.LottoRound;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService{

    private final CreateLottoRepository createLottoRepository;

    @Override
    public boolean optionApply(LottoOption lottoOption, int[] lottoNum) {

        Boolean optionResult = true;

        if("Y".equals(lottoOption.getNumberOfExclusion())) {
            optionResult = numberOfExclusions(lottoNum);
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
    public boolean numberOfExclusions(int[] lottoNum) {

//        Arrays.stream(lottoNum).anyMatch();
        return true;
    }

    @Override
    public boolean continuityAppear(int[] lottoNum) {

        return true;
    }

    @Override
    public boolean winningNum(int[] lottoNum) {
        boolean optionResult = true;
        List<LottoRound> allLottoRound = createLottoRepository.findAllLottoRound();

        for (LottoRound lottoRound : allLottoRound) {
            int winningNum[] = {
                    lottoRound.getFirstNum(), lottoRound.getSecondNum(),
                    lottoRound.getThirdNum(), lottoRound.getFourthNum(),
                    lottoRound.getFifthNum(), lottoRound.getSixthNum()
            };

            optionResult = Arrays.equals(lottoNum, winningNum);
        }

        return optionResult;
    }

    @Override
    public boolean allEven(int[] lottoNum) {
        return true;
    }

    @Override
    public boolean oddNum(int[] lottoNum) {
        return true;
    }
}
