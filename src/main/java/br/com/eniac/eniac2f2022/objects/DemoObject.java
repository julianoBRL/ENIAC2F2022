package br.com.eniac.eniac2f2022.objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder("name,country,telephone,email")
public class DemoObject {
	private String name;
	private String country;
	private String telephone;
	private String email;
}
