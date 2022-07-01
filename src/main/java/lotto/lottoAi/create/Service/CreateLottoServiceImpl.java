package lotto.lottoAi.create.Service;

import lombok.RequiredArgsConstructor;
import lotto.lottoAi.create.repository.CreateLottoRepository;
import lotto.lottoAi.model.Lotto;
import lotto.lottoAi.model.LottoOption;
import lotto.lottoAi.model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateLottoServiceImpl implements CreateLottoService {

    private final OptionService optionService;
    private final CreateLottoRepository createLottoRepository;

    @Override
    public List<Lotto> createLotto(Member member, LottoOption lottoOption, int cnt) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i=0; i < cnt; i++) {
            Boolean optionResult = true;

            int lottoNum[] = createLottoNumVer1();

            optionResult = optionService.optionApply(lottoOption, lottoNum);

            if(optionResult) {
                Lotto lotto = new Lotto();
                lotto.setMember(member);
                lotto.setNum1(lottoNum[0]);
                lotto.setNum2(lottoNum[1]);
                lotto.setNum3(lottoNum[2]);
                lotto.setNum4(lottoNum[3]);
                lotto.setNum5(lottoNum[4]);
                lotto.setNum6(lottoNum[5]);
                lotto.setCreateDate(LocalDateTime.now());

                lottoList.add(lotto);
            }
        }

        createLottoRepository.createLotto(lottoList);

        return lottoList;
    }

    @Override
    public int[] createLottoNumVer1() {
        int lottoNum[] = new int[6];
        //2. 로또 숫자 범위 1~45
        int min = 1;
        int max = 45;
        //3. 숫자는 랜덤
        Random rand = new Random();
        //4. 랜덤숫자 추출
        for(int i=0; i<lottoNum.length; i++) {
            // nextInt(45) 이면 해당 범위가 0 ~ 44 이므로 마지막에 최소값인 min을 더해줍니다.
            lottoNum[i] = (rand.nextInt((max - min) + 1) + min);

            //5. 중복제거
            for(int j = 0; j < i;j++) {
                if (lottoNum[i] == lottoNum[j]) {
                    i--;
                }
                //6. 오름차순
                if(lottoNum[i] < lottoNum[j]) {
                    int lottoNumTemp = lottoNum[i];
                    lottoNum[i] = lottoNum[j];
                    lottoNum[j] = lottoNumTemp;
                }
            }
        }
        System.out.print("Ver1.Lotto 선택 숫자는? ");
        // 6. 출력
        for (int i = 0; i < lottoNum.length; i++) {
            System.out.print(lottoNum[i]+ " ");
        }

        return lottoNum;
    }

    @Override
    public int[] createLottoNumVer2() {
        //ver2.
        int lotto[] = new int[6];
        System.out.print("Ver2.Lotto 선택 숫자는? ");

        for(int x = 0; x <lotto.length; x++ ) {
            lotto[x] = (int)(Math.random()*45)+1;

            for(int y =0; x<y; x++) {
                if(lotto[x] == lotto[y]) {
                    x-=1;
                }
            }
        }
        // 오름차순
        Arrays.sort(lotto);
        // 출력
        for (int x = 0; x < lotto.length; x++) {
            System.out.print(lotto[x]+" ");
        }

        return lotto;
    }
}
