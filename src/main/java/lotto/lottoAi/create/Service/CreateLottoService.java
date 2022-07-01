package lotto.lottoAi.create.Service;

import lotto.lottoAi.model.Lotto;
import lotto.lottoAi.model.LottoOption;
import lotto.lottoAi.model.Member;

import java.util.List;

public interface CreateLottoService {

    public List<Lotto> createLotto(Member member, LottoOption lottoOption, int cnt);

    public int[] createLottoNumVer1();

    public int[] createLottoNumVer2();
}
