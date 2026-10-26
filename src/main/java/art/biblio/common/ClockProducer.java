package art.biblio.common;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.time.Clock;
import java.time.ZoneId;

@ApplicationScoped 
public class ClockProducer {

    @Produces  @ApplicationScoped
    public Clock getClock() {
        return Clock.system(ZoneId.of("Europe/Paris"));
    }

}
