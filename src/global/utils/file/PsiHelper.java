package global.utils.file;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import global.utils.Logger;

/**
 * Created by Arsen on 06.02.2017.
 */
public class PsiHelper {

    public static PsiDirectory getPsiDirByPath(Project project, String path) {
        VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
        if (virtualFile == null) {
            Logger.log("getPsiDirByPath virtualFile is NULL");
            return null;
        }

        PsiDirectory psiParentDir = PsiManager.getInstance(project).findDirectory(virtualFile);
        if (psiParentDir == null) {
            Logger.log("getPsiDirByPath psiDirectory is NULL");
            return null;
        }

        return psiParentDir;
    }

    public static PsiFile getPsiFileByPath(Project project, String path) {
        VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
        if (virtualFile == null) {
            Logger.log("getPsiFileByPath virtualFile is NULL");
            return null;
        }

        PsiFile psiFile = PsiManager.getInstance(project).findFile(virtualFile);
        if (psiFile == null) {
            Logger.log("getPsiFileByPath psiFile is NULL");
            return null;
        }

        return psiFile;
    }

}