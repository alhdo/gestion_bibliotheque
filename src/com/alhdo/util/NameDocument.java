package com.alhdo.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * Created by Castro Alhdo on 5/3/16.
 * File created af 3:17 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class NameDocument extends PlainDocument{
    private String text="";

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        try {
            text = getText(0,getLength());
            if((text + str).matches("/^[a-zA-Z]+$/")){
                super.insertString(offs, str, a);
            }

        }catch (Exception e){
            Log.e(e.toString());
        }

    }
}
