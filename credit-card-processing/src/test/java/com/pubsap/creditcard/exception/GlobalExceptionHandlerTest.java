package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.GlobalExceptionHandler;
import com.pubsap.creditcard.models.ApiError;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException;

    @Mock
    private WebRequest webRequest;

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void test_method_not_supported_when_put_not_supported_but_get_and_post_supported() throws Exception {
        Set<HttpMethod> mockedHttpMethodsSet = new HashSet<>(Arrays.asList(HttpMethod.GET,HttpMethod.POST));
        int expectedMethodStatusCode = HttpStatus.METHOD_NOT_ALLOWED.value();
        String expectedMessage = "PATCH method not allowed, Supported Methods are GET POST ";
        String expectedPath = "/test";

        //Behaviour
        when(httpRequestMethodNotSupportedException.getSupportedHttpMethods()).thenReturn(mockedHttpMethodsSet);
        when(httpRequestMethodNotSupportedException.getMessage()).thenReturn("PATCH method not allowed");
        when(webRequest.getDescription(false)).thenReturn("uri=/test");

        //SUT
        final ResponseEntity<Object> entity = globalExceptionHandler.handleException(httpRequestMethodNotSupportedException,webRequest);
        ApiError apiError = (ApiError) entity.getBody();

        //asserts
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(),expectedMethodStatusCode);
        assertTrue(apiError.getMessage().contains(expectedMessage));
        assertEquals(expectedPath,apiError.getPath());

        //verifies
        verify(httpRequestMethodNotSupportedException, times(4)).getSupportedHttpMethods();
        verify(httpRequestMethodNotSupportedException, times(2)).getMessage();
        verify(webRequest, times(1)).getDescription(anyBoolean());
    }

}

