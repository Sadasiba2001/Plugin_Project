package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.command.WriteCommandAction;

public class CaseChange extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR);

        if (editor != null) {
            SelectionModel selectionModel = editor.getSelectionModel();
            String selectedText = selectionModel.getSelectedText();
            System.out.println("The selected text: " + selectedText);
            if (selectedText != null) {
                System.out.println("Entered into If condition;");
                String upperCaseText = selectedText.toUpperCase();
                System.out.println("Change the text case");

                // Get the document
                Document document = editor.getDocument();
                CaretModel caretModel = editor.getCaretModel();

                // Wrap the document modification in a write action
                WriteCommandAction.runWriteCommandAction(project, () -> {
                    document.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), upperCaseText);
                    System.out.println("The text modified successfully");

                    // Move the caret to the end of the replaced selection
                    caretModel.moveToOffset(selectionModel.getSelectionEnd());

                    // Remove selection
                    selectionModel.removeSelection();
                });

            } else {
                Messages.showMessageDialog(project, "No text selected", "Upper Case Action", Messages.getInformationIcon());
            }
        }
    }
}
