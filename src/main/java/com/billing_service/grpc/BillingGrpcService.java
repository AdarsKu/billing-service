package com.billing_service.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import com.billing_service.mail.CustomerCreatedEvent;
import com.billing_service.mail.CustomerEmailService;
import com.proto.BillingRequest;
import com.proto.BillingResponse;
import com.proto.BillingServiceGrpc.BillingServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;;

@GrpcService
public class BillingGrpcService  extends BillingServiceImplBase {
	
	
	private final ApplicationEventPublisher eventPublisher;
	private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
	
	// constructor 
	public BillingGrpcService(ApplicationEventPublisher eventPublisher) {
		super();
		this.eventPublisher=eventPublisher;
	}
	
	
		
	@Override
	public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> observer) {
		
		log.info("create billing account request received {}",billingRequest.toString());
		
		// busines logic calculation etc
		
		 eventPublisher.publishEvent(
	                new CustomerCreatedEvent(billingRequest.getEmail(), billingRequest.getName())
	        );
		
		BillingResponse response= BillingResponse.newBuilder()
				.setAccountId("1234")
				.setStatus("ACTIVE")
				.build();
		
		
		
		observer.onNext(response);
		observer.onCompleted();
	}

}
