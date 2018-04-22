package ch.admin.seco.jobs.services.jobadservice.infrastructure.service.reference.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.GeoPoint;
import ch.admin.seco.jobs.services.jobadservice.domain.jobadvertisement.Location;

@RunWith(MockitoJUnitRunner.class)
public class DefaultLocationServiceTest {

	@InjectMocks
	private DefaultLocationService testedObject;

	@Mock
	private LocationApiClient locationApiClient;

	@Test
	public void shouldEnrichCodes() {
		// GIVEN
		Location location = new Location.Builder()
				.setCountryIsoCode("CH")
				.setCity("Bern")
				.setCantonCode("canton")
				.setCommunalCode("communal")
				.setPostalCode("2222")
				.setRegionCode("region")
				.setRemarks("remarks")
				.setCoordinates(new GeoPoint(1., 1.))
				.build();
		List<LocationResource> locationResources = Arrays.asList(
				new LocationResource(UUID.randomUUID(), "Kharkiv", "6666", "communal", "canton", "region", new GeoPointResource(2., 3.)),
				new LocationResource(UUID.randomUUID(), "Bern", "7777", "12", "34", "Bern", new GeoPointResource(3., 5.))
		);
		given(locationApiClient.findLocationByPostalCode("2222"))
				.willReturn(locationResources);

		// WHEN
		Location enriched = testedObject.enrichCodes(location);

		assertThat(enriched.getRemarks()).isEqualTo("remarks");
		assertThat(enriched.getCantonCode()).isEqualTo("34");
		assertThat(enriched.getCity()).isEqualTo("Bern");
		assertThat(enriched.getCommunalCode()).isEqualTo("12");
		assertThat(enriched.getCountryIsoCode()).isEqualTo("CH");
		assertThat(enriched.getPostalCode()).isEqualTo("2222");
		assertThat(enriched.getRegionCode()).isEqualTo("Bern");
		assertThat(enriched.getCoordinates().getLatitude()).isEqualTo(3.);
		assertThat(enriched.getCoordinates().getLongitude()).isEqualTo(5.);
	}

	@Test
	public void shouldNotEnrichCodesWhenNoMatchingCity() {
		// GIVEN
		Location location = new Location.Builder()
				.setCountryIsoCode("CH")
				.setCity("Bern")
				.setCantonCode("canton")
				.setCommunalCode("communal")
				.setPostalCode("2222")
				.setRegionCode("region")
				.build();
		List<LocationResource> locationResources = Collections.singletonList(
				new LocationResource(UUID.randomUUID(), "Kharkiv", "6666", "communal", "canton", "region", new GeoPointResource(2., 3.))
		);
		given(locationApiClient.findLocationByPostalCode("2222"))
				.willReturn(locationResources);

		// WHEN
		Location enriched = testedObject.enrichCodes(location);

		assertThat(enriched).isSameAs(location);
	}

	@Test
	public void shouldNotEnrichCodesForNotManagedCountry() {
		// GIVEN
		Location location = new Location.Builder()
				.setCountryIsoCode("UA")
				.setCity("Kyiv")
				.setPostalCode("2222")
				.build();

		// WHEN
		Location enriched = testedObject.enrichCodes(location);

		assertThat(enriched).isSameAs(location);
	}
}