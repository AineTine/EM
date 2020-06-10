import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.apache.http.util.TextUtils;
import sun.misc.resources.Messages;

import java.io.IOException;

public class TineAine extends AnAction {

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
        String url = "http://121.41.118.98/wordpress/?s=";
        try {
            Runtime.getRuntime().exec("cmd   /c   start   "+url+mEditor);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
