package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.PayrollWrapper;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PayrollCard extends UiPart<Region> {

    private static final String FXML = "PayrollListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final PayrollWrapper payroll;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox vBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PayrollCard(PayrollWrapper payroll, int displayedIndex) {
        super(FXML);
        this.payroll = payroll;
        id.setText(displayedIndex + ". ");
        name.setText(payroll.getPerson().getName().value);
        phone.setText(payroll.getPerson().getPhone().value);
        Label bankDetails = new Label(payroll.getPerson().getBankDetails().value);
        Label hoursWorked = new Label(payroll.getHoursWorked() + " hours");
        Label pay = new Label("$" + payroll.getPay());
        vBox.getChildren().addAll(bankDetails, hoursWorked, pay);
    }
}
