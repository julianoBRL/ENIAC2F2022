package br.com.eniac.eniac2f2022.utils;

import com.github.javafaker.Faker;

import br.com.eniac.eniac2f2022.enums.OutputTypes;
import br.com.eniac.eniac2f2022.objects.DemoObject;

public class ObjectGenerator {
	
	public static String generateRandomDemoObject(OutputTypes type, Faker faker) throws Exception {
		
		DemoObject object = DemoObject.builder()
				.country(faker.country().name())
				.email(faker.internet().emailAddress())
				.telephone(faker.phoneNumber().cellPhone())
				.name(faker.name().firstName())
				.build();

		return ObjectConverter.writeAsString(type, object);
	}
	
}
