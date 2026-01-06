package Utility;

import Exception.InvalidCGPAException;

public class Validator {
    public static void validateCGPA(double cgpa) throws InvalidCGPAException {
        if (cgpa < 0.0 || cgpa > 4.0) {
            throw new InvalidCGPAException("CGPA must be between 0.00 and 4.00");
        }
    }
}
