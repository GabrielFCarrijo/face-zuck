package br.com.faceZuck.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	private String country;
	private String city;
	private String state;
}
