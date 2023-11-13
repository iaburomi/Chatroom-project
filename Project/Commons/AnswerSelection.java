package Project.Commons;

public class AnswerSelection {
    private String selectedAnswer;
    private int selectionOrder;

    public AnswerSelection(String selectedAnswer, int selectionOrder) {
        this.selectedAnswer = selectedAnswer;
        this.selectionOrder = selectionOrder;
    }
    public AnswerSelection() {
    }

    // Getter methods

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public int getSelectionOrder() {
        return selectionOrder;
    }
}
