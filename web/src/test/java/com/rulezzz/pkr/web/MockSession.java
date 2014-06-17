package com.rulezzz.pkr.web;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockSession {

    public static HttpSession createMockSession() {
        HttpSession session = mock(HttpSession.class);
        final Map<String, Object> sessionAttributes = new HashMap<>();
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                sessionAttributes.put((String) args[0], args[1]);
                return null;
            }
        }).when(session).setAttribute(anyString(), anyObject());
        when(session.getAttribute(anyString())).then(new Answer<Object>() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return sessionAttributes.get(invocation.getArguments()[0]);
            }
        });
        return session;
    }
}
