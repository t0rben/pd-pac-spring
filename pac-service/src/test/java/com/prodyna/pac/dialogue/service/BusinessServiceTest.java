package com.prodyna.pac.dialogue.service;

import com.prodyna.pac.dialogue.beanpostprocessor.LoggerPostProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LoggerPostProcessor.class, BusinessService.class})
@MockBean(classes = {PrototypeScopedService.class, RequestScopedService.class, SessionScopedService.class, ServiceA.class})
public class BusinessServiceTest {

    @Autowired
    private BusinessService businessService;

    @Test
    public void loggerAvailable() {

        final Field logger = ReflectionUtils.findField(BusinessService.class, "logger");
        ReflectionUtils.makeAccessible(logger);
        final Object field = ReflectionUtils.getField(logger, businessService);

        Assert.assertNotNull(field);
        Assert.assertTrue(field instanceof org.slf4j.Logger);
    }

}
