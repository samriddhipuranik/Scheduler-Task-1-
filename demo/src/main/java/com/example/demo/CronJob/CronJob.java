package com.example.demo.CronJob;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class CronJob {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRate = 180000) // running 3 mins or we can write - (cron = "*/30 * * * * *")
    public void cronJob() {
        String key = "SomeRandomKey";
        String value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            // Expiry time 6 mins
            redisTemplate.opsForValue().set(key, "SomeDummyValue", 6, TimeUnit.MINUTES);
            System.out.println(key +", when no key is present: " + "SomeDummyValue");
        } else {
            System.out.println("Value for key, " + key + ": " + value);
        }
    }
}

