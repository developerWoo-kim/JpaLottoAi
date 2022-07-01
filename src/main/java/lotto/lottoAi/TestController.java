package lotto.lottoAi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lotto.lottoAi.create.Service.CreateLottoServiceImpl;
import lotto.lottoAi.create.repository.CreateLottoRepository;
import lotto.lottoAi.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestController {

    private final testMethod testMethod;

    @PostConstruct
    public void init() {
        testMethod.test2();
        testMethod.test3();
        testMethod.test();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class testMethod {
        private final EntityManager em;
        private final CreateLottoRepository createLottoRepository;
        private final CreateLottoServiceImpl createLottoService;

        public void test() {

            Member member = new Member();
            member.setGrade(Grade.Basic);
            member.setName("김건우");
            em.persist(member);

            LottoOption lottoOption = new LottoOption();
            lottoOption.setNumberOfExclusion("Y");
            lottoOption.setWinningNum("Y");
            lottoOption.setContinuityAppear("N");
            lottoOption.setAllEven("N");
            lottoOption.setOddNum("N");
            lottoOption.setMember(member);
            em.persist(lottoOption);

            List<Lotto> lottoList = createLottoService.createLotto(member, lottoOption, 10);
            System.out.println("lottoList = " + lottoList.toString());

        }

        public void test2() {
            String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=921";

            UriComponents uriComponents = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .build();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> exchange = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, String.class);

            String body = exchange.getBody();
            JsonObject obj = JsonParser.parseString(body).getAsJsonObject();
            System.out.println("obj = " + obj);

            LottoRound lottoRound = new LottoRound();
            lottoRound.createLottoRound(obj);

            em.persist(lottoRound);
        }

        public void test3() {
            String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=922";

            UriComponents uriComponents = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .build();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> exchange = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, String.class);

            String body = exchange.getBody();
            JsonObject obj = JsonParser.parseString(body).getAsJsonObject();
            System.out.println("obj = " + obj);

            LottoRound lottoRound = new LottoRound();
            lottoRound.createLottoRound(obj);

            em.persist(lottoRound);
        }
    }
}
