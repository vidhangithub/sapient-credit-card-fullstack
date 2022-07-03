package com.pubsap.creditcard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.pubsap.creditcard.exceptions.ErrorMessages;
import com.pubsap.creditcard.models.ApiError;
import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.repository.CreditCardProcessingRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static com.pubsap.creditcard.util.TestUtil.getTestUtil;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@WithMockUser
class CreditCardProcessingApplicationTests {

	@Autowired
	private CreditCardProcessingRepo creditCardProcessingRepo;

	@Autowired
	MockMvc mockMvc;

	ObjectMapper om = JsonMapper.builder()
			.findAndAddModules()
			.build();
	Map<String, CreditCard> testData;

	@BeforeEach
	public void init() {
		creditCardProcessingRepo.deleteAll();
		testData = getTestUtil().getTestData();
	}

	@Test
	public void test_credit_card_creation_with_valid_data() throws Exception {
		CreditCard expectedRecord = testData.get("validCard1");
		CreditCard actualRecord = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), CreditCard.class);
		assertTrue(creditCardProcessingRepo.findById(actualRecord.getId()).isPresent());

		expectedRecord = testData.get("validCard2");
		actualRecord =  om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), CreditCard.class);
		assertTrue(creditCardProcessingRepo.findById(actualRecord.getId()).isPresent());

	}

	@Test
	public void test_get_all_created_credit_cards() throws Exception {
		Map<String, CreditCard> testData = new HashMap<>();
		for (Map.Entry<String, CreditCard> stringCreditCardEntry : getTestUtil().getTestData().entrySet()) {
			if ("validCard1,validCard2".contains(stringCreditCardEntry.getKey())) {
				if (testData.put(stringCreditCardEntry.getKey(), stringCreditCardEntry.getValue()) != null) {
					throw new IllegalStateException("Duplicate key");
				}
			}
		}

		List<CreditCard> expectedRecords = new ArrayList<>();
		for (Map.Entry<String, CreditCard> kv : testData.entrySet()) {
			expectedRecords.add(om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
							.contentType("application/json")
							.content(om.writeValueAsString(kv.getValue())))
					.andDo(print())
					.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), CreditCard.class));
		}
		expectedRecords.sort(Comparator.comparing(CreditCard::getId));

		List<CreditCard> actualRecords = om.readValue(mockMvc.perform(get("/api/v1/cc-process/credit-cards"))
				.andDo(print())
				.andExpect(jsonPath("$.*", isA(ArrayList.class)))
				.andExpect(jsonPath("$.*", hasSize(expectedRecords.size())))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<List<CreditCard>>() {
		});

		for (int i = 0; i < expectedRecords.size(); i++) {
			Assertions.assertTrue(new ReflectionEquals(expectedRecords.get(i)).matches(actualRecords.get(i)));
		}
	}

	@Test
	public void test_method_not_allowed_check() throws Exception {
		CreditCard expectedRecord = getTestUtil().getTestData().get("validCard1");
		om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), CreditCard.class);

		mockMvc.perform(get("/api/v1/cc-process/credit-card"))
				.andExpect(status().isMethodNotAllowed());

		mockMvc.perform(put("/api/v1/cc-process/credit-card"))
				.andExpect(status().isMethodNotAllowed());

		mockMvc.perform(patch("/api/v1/cc-process/credit-card"))
				.andExpect(status().isMethodNotAllowed());

		mockMvc.perform(delete("/api/v1/cc-process/credit-card"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void test_credit_card_creation_failed_when_luhn10_non_compliant_credit_card_number_provided() throws Exception {
		CreditCard expectedRecord = testData.get("invalidCardLuhnFailed");
		final ApiError apiError = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(), ApiError.class);
		assertEquals(ErrorMessages.INVALID_CREDIT_CARD.getErrorMsg(),apiError.getMessage());
	}

	@Test
	public void test_credit_card_creation_failed_when_alpha_numeric_credit_card_number_provided() throws Exception {
		CreditCard expectedRecord = testData.get("invalidCardNumberFormat");
		final ApiError apiError = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(), ApiError.class);
		assertEquals(ErrorMessages.INVALID_CREDIT_CARD.getErrorMsg(),apiError.getMessage());
	}

	@Test
	public void test_credit_card_creation_failed_when_blank_user_provided() throws Exception {
		CreditCard expectedRecord = testData.get("blankUserNameCard");
		final ApiError apiError = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(), ApiError.class);
		assertEquals(ErrorMessages.INVALID_USER_NAME.getErrorMsg(),apiError.getMessage());
	}

	@Test
	public void test_credit_card_creation_failed_when_null_user_provided() throws Exception {
		CreditCard expectedRecord = testData.get("nullUserNameCard");
		final ApiError apiError = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(), ApiError.class);
		assertEquals(ErrorMessages.INVALID_USER_NAME.getErrorMsg(),apiError.getMessage());
	}

	@Test
	public void test_credit_card_creation_failed_when_null_credit_limit_provided() throws Exception {
		CreditCard expectedRecord = testData.get("nullCreditLimit");
		final ApiError apiError = om.readValue(mockMvc.perform(post("/api/v1/cc-process/credit-card")
						.contentType("application/json")
						.content(om.writeValueAsString(expectedRecord)))
				.andDo(print())
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString(), ApiError.class);
		assertEquals(ErrorMessages.INVALID_CREDIT_CARD.getErrorMsg(),apiError.getMessage());
	}
}
