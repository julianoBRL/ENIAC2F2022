package br.com.eniac.eniac2f2022.objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WSResponse {
	private String status;
	private int statusN;
	private String message;
	private String uuid;
	private boolean expired;
	private String expireIn;
}
