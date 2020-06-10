import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;
import pojo.Translation;
import util.HttpUtils;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.intellij.openapi.application.ApplicationManager.*;

public class EnglishTeacherAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }
        //调用有道API
        //http://fanyi.youdao.com/openapi.do?keyfrom=fadabvaa&key=522071532&type=data&doctype=json&version=1.1&q=Microsoft
        String baseUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=fadabvaa&key=522071532&type=data&doctype=json&version=1.1&q=";

        String ret = null;

            ret = HttpUtils.getRequest(baseUrl + selectedText, null);

        Gson gson = new Gson();
        Translation translation = gson.fromJson(ret, Translation.class);
        //显示

        showPopupBalloon(mEditor,translation.toString());

    }


    private void showPopupBalloon(final Editor editor, final String result) {
        getApplication().invokeLater(new Runnable() {
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                        .setFadeoutTime(10000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}
