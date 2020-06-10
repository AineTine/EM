import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;

import java.awt.*;
import java.io.IOException;

import static com.intellij.openapi.application.ApplicationManager.getApplication;

public class EsaySearchAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取选择的内容
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }
        String url = "https://www.baidu.com/s?wd=";
        try {
            Runtime.getRuntime().exec("cmd   /c   start   "+url+selectedText);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
