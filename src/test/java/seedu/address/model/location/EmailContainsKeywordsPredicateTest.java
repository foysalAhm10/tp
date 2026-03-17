package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LocationBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "alice@example.com";
        String secondPredicateKeyword = "bob@example.com";

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different location -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate("alice");
        assertTrue(predicate.test(new LocationBuilder().withEmail("alice@example.com").build()));

        // Mixed-case keyword
        predicate = new EmailContainsKeywordsPredicate("aLiCe");
        assertTrue(predicate.test(new LocationBuilder().withEmail("alice@example.com").build()));

        // Substring
        predicate = new EmailContainsKeywordsPredicate("example");
        assertTrue(predicate.test(new LocationBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate("alice");
        assertFalse(predicate.test(new LocationBuilder().withEmail("bob@example.com").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "alice@example.com";
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(keyword);
        String expected = EmailContainsKeywordsPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
