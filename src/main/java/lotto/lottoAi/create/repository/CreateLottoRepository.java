package lotto.lottoAi.create.repository;

import lombok.RequiredArgsConstructor;
import lotto.lottoAi.model.Lotto;
import lotto.lottoAi.model.LottoRound;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreateLottoRepository {

    private final EntityManager em;

    public Lotto findOne(Long id) {
        return em.find(Lotto.class, id);
    }

    public void createLotto(List<Lotto> lottos) {
        for(Iterator<Lotto> it = lottos.iterator(); it.hasNext();) {
            Lotto lotto = it.next();

            em.persist(lotto);
        }
    }

    public List<LottoRound> findAllLottoRound() {
        return em.createQuery("select l from LottoRound l", LottoRound.class)
                .getResultList();
    }

}
