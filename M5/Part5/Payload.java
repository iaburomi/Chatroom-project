package M5.Part5;

import java.io.Serializable;
import java.util.List;

public class Payload implements Serializable {
    // read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;// change this if the class changes
    // iaa47
    // 11/13/23
    // Potential answers for a question
    private List<String> potentialAnswers;

    public List<String> getPotentialAnswers() {
        return potentialAnswers;
    }

    public Payload() {
    }

    public void setPotentialAnswers(List<String> potentialAnswers) {
        this.potentialAnswers = potentialAnswers;
    }

    private String selectedAnswer;

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    /**
     * Order of selection
     */
    private int selectionOrder;

    public int getSelectionOrder() {
        return selectionOrder;
    }

    public void setSelectionOrder(int selectionOrder) {
        this.selectionOrder = selectionOrder;
    }

    /**
     * Determines how to process the data on the receiver's side
     */
    private PayloadType payloadType;

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Who the payload is from
     */
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Generic text based message
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Generic number for example sake
     */
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Shows Player IDs
    private List<String> playerIds;

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }

    @Override
    public String toString() {
        return String.format(
                "Type[%s], Number[%s], Message[%s], PotentialAnswers[%s], SelectedAnswer[%s], SelectionOrder[%s]",
                getPayloadType().toString(), getNumber(), getMessage(), getPotentialAnswers(), getSelectedAnswer(),
                getSelectionOrder());
    }
}