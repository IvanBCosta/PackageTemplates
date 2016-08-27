package custom.view;

import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.GridBag;
import net.miginfocom.swing.MigLayout;
import utils.GridBagFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Arsen on 16.08.2016.
 */
public class SpoilerPane extends JPanel {

    private JButton jbToggle;
    private JComponent content;
    private Icon iconExpand;
    private Icon iconCollapse;
    private JBLabel jlTitle;

    // CollapsiblePanel

    public SpoilerPane(JComponent content, Icon iconExpand, Icon iconCollapse, String title) {
        this.content = content;
        this.iconExpand = iconExpand;
        this.iconCollapse = iconCollapse;
        jbToggle = new JButton();
        jbToggle.setOpaque(false);
        jbToggle.setBorderPainted(false);
        jbToggle.setBorder(new EmptyBorder(0, 0, 0, 0));
        jlTitle = new JBLabel(title);

        init();
    }

    public void init() {
        setLayout(new MigLayout());
        this.setBackground(content.getBackground());

//        add(jbToggle, bag.nextLine().next().fillCellVertically());
        add(jbToggle, "");
//        add(jlTitle, bag.next().fillCellVertically().anchor(GridBagConstraints.NORTHWEST));
        add(jlTitle, ", wrap");
        setCollapsed(true);

        this.jbToggle.addActionListener(e -> setCollapsed(!isCollapsed));
    }

    private boolean isInitialized;
    private boolean isCollapsed;

    protected void setCollapsed(boolean collapse) {
        try {
            if (collapse) {
                if (isInitialized) {
                    remove(content);
                }
            } else {
//                add(content, bag.nextLine().next().coverLine().setLine(1));
                add(content, "cell 0 1, span");
            }

            isCollapsed = collapse;
            jbToggle.setIcon(getIcon());

            if (collapse) {
                setFocused();
                setSelected(true);
            } else {
                content.requestFocusInWindow();
            }

//            notifyListners();
            revalidate();
            repaint();
        } finally {
            isInitialized = true;
        }
    }

    public void setFocused() {
        jbToggle.requestFocusInWindow();
    }

    public void setSelected(boolean selected) {
        jbToggle.setSelected(selected);
    }

    private Icon getIcon() {
        return isCollapsed ? iconExpand : iconCollapse;
    }

}
