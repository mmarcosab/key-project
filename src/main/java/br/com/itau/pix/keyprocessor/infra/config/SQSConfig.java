//package br.com.itau.pix.keyprocessor.infra.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.internal.StaticCredentialsProvider;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.AmazonSQSClient;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.URI;
//
//@Configuration
//public class SQSConfig {
//
//    @Value("${cloud.aws.region.static}")
//    private String region;
//
//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKeyId;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretAccessKey;
//
//    @Value("${cloud.aws.end-point.uri}")
//    private String sqsUrl;
//
//    @Bean
//    public AmazonSQSAsync amazonSQSAsync() {
//        return AmazonSQSAsync.builder()
//                .endpointOverride(URI.create(sqsUrl))
//                .region(Regions.US_EAST_1)
//                .credentialsProvider(StaticCredentialsProvider.create(new AWSCredentials() {
//                    @Override
//                    public String accessKeyId() {
//                        return accessKeyId;
//                    }
//
//                    @Override
//                    public String secretAccessKey() {
//                        return secretAccessKey;
//                    }
//                }))
//                .build();
//    }
//
//    @Bean
//    public AmazonSQSClient amazonSQSClient() {
//        return AmazonSQSClient.builder()
//                .endpointOverride(URI.create(sqsUrl))
//                .region(Region.of(region))
//                .credentialsProvider(StaticCredentialsProvider.create(new AwsCredentials() {
//                    @Override
//                    public String accessKeyId() {
//                        return accessKeyId;
//                    }
//
//                    @Override
//                    public String secretAccessKey() {
//                        return secretAccessKey;
//                    }
//                }))
//                .build();
//    }
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }
//}
