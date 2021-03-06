/*
 * The MIT License
 *
 * Copyright 2014 junichi11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.junichi11.netbeans.showpath.ui;

import com.junichi11.netbeans.showpath.ui.actions.CopyPathAction;
import com.junichi11.netbeans.showpath.ui.actions.OpenPathAction;
import java.io.File;
import java.util.Collection;
import javax.swing.JPanel;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 *
 * @author junichi11
 */
public class ShowPathPanel extends JPanel implements LookupListener {

    private static final long serialVersionUID = 2563506726237130752L;
    private static final ShowPathPanel INSTANCE = new ShowPathPanel();

    private Lookup.Result result = null;

    public static ShowPathPanel getInstance() {
        return INSTANCE;
    }

    private ShowPathPanel() {
        initComponents();
        copyButton.addActionListener(new CopyPathAction());
        openButton.addActionListener(new OpenPathAction());
        addLookupListener();
    }

    private void addLookupListener() {
        result = Utilities.actionsGlobalContext().lookupResult(FileObject.class);
        result.addLookupListener(this);
    }

    public String getFullPath() {
        return fullPathTextField.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathLabel = new javax.swing.JLabel();
        fullPathTextField = new javax.swing.JTextField();
        copyButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(pathLabel, org.openide.util.NbBundle.getMessage(ShowPathPanel.class, "ShowPathPanel.pathLabel.text")); // NOI18N

        fullPathTextField.setEditable(false);
        fullPathTextField.setText(org.openide.util.NbBundle.getMessage(ShowPathPanel.class, "ShowPathPanel.fullPathTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(copyButton, org.openide.util.NbBundle.getMessage(ShowPathPanel.class, "ShowPathPanel.copyButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(openButton, org.openide.util.NbBundle.getMessage(ShowPathPanel.class, "ShowPathPanel.openButton.text")); // NOI18N
        openButton.setToolTipText(org.openide.util.NbBundle.getMessage(ShowPathPanel.class, "ShowPathPanel.openButton.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(pathLabel)
                .addComponent(fullPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(copyButton)
                .addComponent(openButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyButton;
    private javax.swing.JTextField fullPathTextField;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel pathLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent lookupEvent) {
        Lookup.Result r = (Lookup.Result) lookupEvent.getSource();
        Collection c = r.allInstances();
        if (!c.isEmpty()) {
            FileObject fo = (FileObject) c.iterator().next();
            if (fo != null) {
                File file = FileUtil.toFile(fo);
                if (file != null) {
                    fullPathTextField.setText(file.getAbsolutePath());
                    return;
                }
            }
        }

        fullPathTextField.setText(""); // NOI18N
    }
}
