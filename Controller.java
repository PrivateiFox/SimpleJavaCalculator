/*
 * A controller class for handling the various calculator functions.
 */
package q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Published: 25/12/2021
 *
 * @author Saar
 */
public class Controller {
    private static final String ZERO_DISPLAY_VALUE = "0";
    private static final int INVERT_VALUE = -1;
    private double totalValue = 0;
    private char currentAction = ' ';

    @FXML
    private Button deletionButton;

    @FXML
    private Button divisionButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button multiplicationButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button subtractionButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button additionButton;

    @FXML
    private Button invertButton;

    @FXML
    private Button zeroButton;

    @FXML
    private Button dotButton;

    @FXML
    private Button summaryButton;

    @FXML
    private TextField numberDisplay;

    /**
     * Adds a character to the end of the number display String.
     */
    private void addCharacterToDisplay(char character) {
        String displayValue = numberDisplay.getText();
        if (displayValue.equals(ZERO_DISPLAY_VALUE)){
            if (character == '.') numberDisplay.setText(displayValue + character); // Add a dot when the number is zero
            else numberDisplay.setText(String.valueOf(character));
        }
        else {
            if (character == '.' && displayValue.contains(".")) return; // Don't allow adding more than one dot
            numberDisplay.setText(displayValue + character);
        }
    }

    /**
     * A generic calculator number button handler
     * We get the character to add by reading the text on the button label.
     * @param event the button click event, passed to us by javafx
     */
    @FXML
    void addCharacterFromButton(ActionEvent event) {
        this.addCharacterToDisplay(((Button)event.getSource()).getText().charAt(0));
    }

    /**
     * A generic calculator operator button handler
     * We get the operation to use by reading the text on the button label.
     * @param event the button click event, passed to us by javafx
     */
    @FXML
    void operateOnValues(ActionEvent event) {
        this.summariseValuesLogic();
        this.currentAction = ((Button)event.getSource()).getText().charAt(0);
        this.numberDisplay.setText(ZERO_DISPLAY_VALUE);
    }

    /**
     * Handles flipping the displayed number between positive/negative(+/-)
     * @param event Unused
     */
    @FXML
    void invertNumber(ActionEvent event) {
        double displayDouble = Double.parseDouble(numberDisplay.getText());
        this.numberDisplay.setText(String.valueOf(displayDouble * INVERT_VALUE));
    }

    /**
     * Handles the reset current operation button(C)
     * @param event Unused
     */
    @FXML
    void revertCurrentOperation(ActionEvent event) {
        this.currentAction = ' ';
        this.numberDisplay.setText(ZERO_DISPLAY_VALUE);
        this.summariseValuesLogic();
    }

    /**
     * Handles the summarise operation button(=)
     * @param event Unused
     */
    @FXML
    void summariseValues(ActionEvent event) {
        this.summariseValuesLogic();
    }

    /**
     * Summarises the currently displayed number with the one stored in this.TotalValue
     * The operation that will be performed is read from this.currentAction
     */
    private void summariseValuesLogic() {
        String displayValue = this.numberDisplay.getText();
        updateTotal(displayValue);
        updateNumberDisplay();
        this.currentAction = ' ';
    }

    /**
     * Reads from this.currentAction, and performs the stored operation on the stored total(this.totalValue) against
     * the newly-entered value(displayValue)
     */
    private void updateTotal(String displayValue) {
        if (this.currentAction == ' ') this.totalValue = Double.parseDouble(displayValue);
        if (this.currentAction == '+') this.totalValue += Double.parseDouble(displayValue);
        if (this.currentAction == '-') this.totalValue -= Double.parseDouble(displayValue);
        if (this.currentAction == '*') this.totalValue *= Double.parseDouble(displayValue);
        if (this.currentAction == '/') this.totalValue /= Double.parseDouble(displayValue);
    }

    /**
     * Sets the numberDisplay text to be the value of this.totalValue.
     */
    private void updateNumberDisplay() {
        if (this.totalValue == 0) this.numberDisplay.setText(ZERO_DISPLAY_VALUE);
        else this.numberDisplay.setText(String.valueOf(this.totalValue));
    }


}

