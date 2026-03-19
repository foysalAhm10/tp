package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LocationBuilder;

public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "91234567";
        String secondPredicateKeyword = "81234567";

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeyword);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy = new PhoneContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different location -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("9123");
        assertTrue(predicate.test(new LocationBuilder().withPhone("91234567").build()));

        // Substring
        predicate = new PhoneContainsKeywordsPredicate("456");
        assertTrue(predicate.test(new LocationBuilder().withPhone("91234567").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("123");
        assertFalse(predicate.test(new LocationBuilder().withPhone("98765432").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "9123";
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(keyword);
        String expected = PhoneContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
