//package br.com.itau.pix.keyprocessor.infra.repository;
//
//import br.com.itau.pix.keyprocessor.infra.rest.in.CreateKeyRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.services.sqs.SqsAsyncClient;
//import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
//import java.time.ZonedDateTime;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class SqsService {
//
//    @Value("${cloud.aws.sqs.queue-name}")
//    private String queue;
//
//    @Value("${cloud.aws.end-point.uri}")
//    private String sqsUrl;
//
//    private final SqsAsyncClient sqsAsyncClient;
//    private final ObjectMapper objectMapper;
//
//    public void sendMessage(final CreateKeyRequest createKeyRequest) {
//        try {
//            log.info("sending message: {}", createKeyRequest.toString());
//            sqsAsyncClient.sendMessage(
//                    SendMessageRequest.builder()
//                            .queueUrl("http://localhost:4566/000000000000/test-queue")
//                            .messageBody(objectMapper.writeValueAsString(createKeyRequest))
//                            .build()
//            );
//            //todo retry
//        } catch (final Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//}
