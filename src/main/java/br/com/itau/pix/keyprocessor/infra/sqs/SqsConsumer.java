package br.com.itau.pix.keyprocessor.infra.sqs;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SqsConsumer {

    @SqsListener("test-queue")
    public void queueListener(String message) {
        try {
            log.info(String.format("Mensagem Recebida: %s", message));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}