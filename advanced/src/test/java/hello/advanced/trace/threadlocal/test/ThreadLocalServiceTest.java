package hello.advanced.trace.threadlocal.test;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = ()-> {
            service.logic("useA");
        };

        Runnable userB = ()-> {
            service.logic("useB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        threadA.start();
        // sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start();
        sleep(2000); //메인 쓰레드 종료 대기

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
