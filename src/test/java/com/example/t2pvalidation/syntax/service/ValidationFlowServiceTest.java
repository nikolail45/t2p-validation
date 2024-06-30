package com.example.t2pvalidation.syntax.service;

import com.example.t2pvalidation.utils.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ValidationFlowServiceTest {

    @InjectMocks
    private ValidationFlowService validationFlowService;

    @Mock
    private ValidationResult validationResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateNoUnboundFlows() {
        String validBpmnPath = "src/test/resources/bpmn/ValidTestCase.bpmn"; // Replace with your test BPMN file path
        String invalidBpmnPath = "src/test/resources/bpmn/InvalidTestCase.bpmn"; // Replace with your test BPMN file path

        ValidationResult validResult = validationFlowService.validateNoUnboundFlows(validBpmnPath);
        assertEquals("completed", validResult.getValidationStatus(), "The BPMN diagram should have no unbound flows.");
        assertTrue(validResult.getErrors().isEmpty(), "There should be no errors for a valid BPMN diagram.");

        ValidationResult invalidResult = validationFlowService.validateNoUnboundFlows(invalidBpmnPath);
        assertEquals("failed", invalidResult.getValidationStatus(), "The BPMN diagram should have unbound flows.");
        assertFalse(invalidResult.getErrors().isEmpty(), "There should be errors for an invalid BPMN diagram.");
    }
}
