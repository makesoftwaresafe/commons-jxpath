/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* Generated By:JavaCC: Do not edit this line. TokenMgrError.java Version 3.0 *
 *
 * !!!MODIFIED BY DMITRI PLOTNIKOV - DO NOT REGENERATE!!!
 */

package org.apache.commons.jxpath.ri.parser;

public class TokenMgrError extends Error {
    /*
     * Ordinals for various reasons why an Error of this type can be thrown.
     */

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Lexical error occurred.
     */
    static final int LEXICAL_ERROR = 0;
    /**
     * An attempt wass made to create a second instance of a static token manager.
     */
    static final int STATIC_LEXER_ERROR = 1;
    /**
     * Tried to change to an invalid lexical state.
     */
    static final int INVALID_LEXICAL_STATE = 2;
    /**
     * Detected (and bailed out of) an infinite loop in the token manager.
     */
    static final int LOOP_DETECTED = 3;

    /**
     * Replaces unprintable characters by their escaped (or Unicode escaped) equivalents in the given string
     *
     * @param str TODO
     * @return TODO
     */
    public static final String addEscapes(final String str) {
        final StringBuilder retval = new StringBuilder();
        char ch;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
            case 0:
                continue;
            case '\b':
                retval.append("\\b");
                continue;
            case '\t':
                retval.append("\\t");
                continue;
            case '\n':
                retval.append("\\n");
                continue;
            case '\f':
                retval.append("\\f");
                continue;
            case '\r':
                retval.append("\\r");
                continue;
            case '\"':
                retval.append("\\\"");
                continue;
            case '\'':
                retval.append("\\\'");
                continue;
            case '\\':
                retval.append("\\\\");
                continue;
            default:
                if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                    final String s = "0000" + Integer.toString(ch, 16);
                    retval.append("\\u" + s.substring(s.length() - 4));
                } else {
                    retval.append(ch);
                }
                continue;
            }
        }
        return retval.toString();
    }

    /**
     * Returns a detailed message for the Error when it is thrown by the token manager to indicate a lexical error.
     *
     * Note: You can customize the lexical error message by modifying this method.
     *
     * @param EOFSeen     indicates if EOF caused the lexicl error
     * @param lexState    lexical state in which this error occurred
     * @param errorLine   line number when the error occurred
     * @param errorColumn column number when the error occurred
     * @param errorAfter  prefix that was seen before this error occurred
     * @param curChar     the offending character
     * @return TODO
     */
    protected static String LexicalError(final boolean EOFSeen, final int lexState, final int errorLine, final int errorColumn, final String errorAfter,
            final char curChar) {
        return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: "
                + (EOFSeen ? "<EOF> " : "\"" + addEscapes(String.valueOf(curChar)) + "\" (" + (int) curChar + "), ") + "after : \""
                + addEscapes(errorAfter) + "\"";
    }

    /**
     * Indicates the reason why the exception is thrown. It will have one of the above 4 values.
     */
    int errorCode;
    private int position;
    /*
     * Constructors of various flavors follow.
     */
    private char character;

    public TokenMgrError() {
    }

    public TokenMgrError(final boolean EOFSeen, final int lexState, final int errorLine, final int errorColumn, final String errorAfter, final char curChar,
            final int reason) {
        this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
        // ADDED BY ME FROM THIS POINT TO THE EOF - DMITRI PLOTNIKOV
        position = errorColumn - 1;
        character = curChar;
    }

    public TokenMgrError(final String message, final int reason) {
        super(message);
        errorCode = reason;
    }

    public char getCharacter() {
        return character;
    }

    /**
     * You can also modify the body of this method to customize your error messages. For example, cases like LOOP_DETECTED and INVALID_LEXICAL_STATE are not of
     * end-users concern, so you can return something like :
     *
     * "Internal Error : Please file a bug report .... "
     *
     * from this method for such cases in the release version of your parser.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getPosition() {
        return position;
    }
}
