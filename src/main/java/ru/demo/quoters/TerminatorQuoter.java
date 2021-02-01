package ru.demo.quoters;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Аннотация @DeprecatedClass сообщает спрингу, что необходимо вместо бина этого класса, создать
 * бин, указанный в аннотации. Реализуется с помощью BeanFactoryPostProcessor, который позволяет
 * внести свои настройки в BeanFactory перед началом его работы.
 */
@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
        System.out.println("repeat = " + repeat);
    }

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    /**
     * Все методы с этой аннотацией будут запускать после того как все бины уже настроены
     */
    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }
}
