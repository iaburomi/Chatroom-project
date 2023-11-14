package Project.Commons;

import java.io.Serializable;
import java.util.List;

public class Payload implements Serializable {
    // read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;// change this if the class changes
    /**
     * Determines how to process the data on the receiver's side
     */
    private PayloadType payloadType;
    public PayloadType getPayloadType() {
        return payloadType;
    }
    //IAA47
    //11/13/23
    private List<String> playerIds;
    private String category;
    private String question;
    private List<String> answerOptions;
    private int roundDuration;  // in seconds
    private int selectedAnswerIndex;
    private int points;
     private String clientName;
    private String targetRoom;

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }
    //Iaa47
    //11/13/23
    public String getTriviaInfoAsString() {
        return String.format("Category: %s\nQuestion: %s\nOptions: %s", getCategory(), getQuestion(), String.join(", ", getAnswerOptions()));
    }
    public List<String> getPlayerIds() {
        return playerIds;
    }
    public void setPlayerIds(List<String> playerIds) {
        this.playerIds = playerIds;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public List<String> getAnswerOptions() {
        return answerOptions;
    }
    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }
    //iaa47
    //11/13/23
    public int getRoundDuration() {
        return roundDuration;
    }
    public void setRoundDuration(int roundDuration) {
        this.roundDuration = roundDuration;
    }
    private long endRoundTime;
    public long getEndRoundTime() {
        return endRoundTime;
    }
    public void setEndRoundTime(long endRoundTime) {
        this.endRoundTime = endRoundTime;
    }
    //iaa47
    //11/13/23
    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }
    public void setSelectedAnswerIndex(int selectedAnswerIndex) {
        this.selectedAnswerIndex = selectedAnswerIndex;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    /**
     * Who the payload is from
     */
    public String getClientName() {
        return clientName;
    }
    public String getTargetRoom() {
        return targetRoom;
    }
    public void setTargetRoom(String targetRoom) {
        this.targetRoom = targetRoom;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    private long clientId;
    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
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
    @Override
    public String toString() {
        return String.format("Type[%s],ClientId[%s,] ClientName[%s], Message[%s]", getPayloadType().toString(),
                getClientId(), getClientName(),
                getMessage());
    }
}