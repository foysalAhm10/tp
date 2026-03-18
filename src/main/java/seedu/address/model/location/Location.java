package seedu.address.model.location;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Location in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Location {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;
    private final Optional<PostalCode> postalCode;

    // Data fields
    private final Optional<Address> address;
    private final Optional<VisitDate> visitDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a Location, Optional fields may be empty.
     */
    public Location(Name name, Optional<Phone> phone, Optional<Email> email,
                    Optional<Address> address, Optional<PostalCode> postalCode,
                    Optional<VisitDate> visitDate, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, postalCode, visitDate, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.visitDate = visitDate;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return email;
    }

    public Optional<PostalCode> getPostalCode() {
        return postalCode;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public Optional<VisitDate> getVisitDate() {
        return visitDate;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both locations have the same name.
     * This defines a weaker notion of equality between two locations.
     */
    public boolean isSameLocation(Location otherLocation) {
        if (otherLocation == this) {
            return true;
        }

        return otherLocation != null
                && otherLocation.getName().equals(getName());
    }

    /**
     * Returns true if both locations have the same identity and data fields.
     * This defines a stronger notion of equality between two locations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return name.equals(otherLocation.name)
                && phone.equals(otherLocation.phone)
                && email.equals(otherLocation.email)
                && address.equals(otherLocation.address)
                && postalCode.equals(otherLocation.postalCode)
                && visitDate.equals(otherLocation.visitDate)
                && tags.equals(otherLocation.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, postalCode, visitDate, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone.map(Phone::toString).orElse("-"))
                .add("email", email.map(Email::toString).orElse("-"))
                .add("address", address.map(Address::toString).orElse("-"))
                .add("postalCode", postalCode.map(PostalCode::toString).orElse("-"))
                .add("visitDate", visitDate.map(VisitDate::toString).orElse("-"))
                .add("tags", tags)
                .toString();
    }
}
