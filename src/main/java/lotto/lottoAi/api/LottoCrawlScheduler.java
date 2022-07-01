package lotto.lottoAi.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lotto.lottoAi.model.LottoRound;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;

@Component
@EnableAsync
@RequiredArgsConstructor
public class LottoCrawlScheduler {

    private final EntityManager em;

//    @Scheduled(cron = "0 0 21 * * 6")
//    @Scheduled(cron = "0 0/1 * * * *")
    @Transactional
    public void lottoCrawlScheduler() {
        //20200725 = 921
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
}
