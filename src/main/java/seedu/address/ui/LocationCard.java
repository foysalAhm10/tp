package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.location.Location;

/**
 * An UI component that displays information of a {@code Location}.
 */
public class LocationCard extends UiPart<Region> {

    private static final String FXML = "LocationListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Location location;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label postalCode;
    @FXML
    private Label visitDate;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code LocationCard} with the given {@code Location} and index to display.
     */
    public LocationCard(Location location, int displayedIndex) {
        super(FXML);
        this.location = location;
        id.setText(displayedIndex + ". ");
        name.setText(location.getName().fullName);
        phone.setText("Phone: " + location.getPhone().map(p -> p.value).orElse("-"));
        address.setText("Address: " + location.getAddress().map(a -> a.value).orElse("-"));
        email.setText("Email: " + location.getEmail().map(e -> e.value).orElse("-"));
        postalCode.setText("Postal Code: " + location.getPostalCode().map(p -> p.value).orElse("-"));
        visitDate.setText("Visit Date: " + location.getVisitDate().map(Object::toString).orElse("-"));
        location.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
