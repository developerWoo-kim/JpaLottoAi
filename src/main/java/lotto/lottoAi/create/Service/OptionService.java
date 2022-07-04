package lotto.lottoAi.create.Service;

import lotto.lottoAi.model.LottoOption;

public interface OptionService {

    public boolean optionApply(LottoOption lottoOption, int[] lottoNum);

    public boolean numberOfExclusions(int[] lottoNum, LottoOption lottoOption);

    public boolean continuityAppear(int[] lottoNum);

    public boolean winningNum(int[] lottoNum);

    public boolean allEven(int[] lottoNum);

    public boolean oddNum(int[] lottoNum);
}
