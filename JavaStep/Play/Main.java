package com.sablin.play;

public class Main {
    public static void main(String[] args) {
    
    }

    public static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder result = new StringBuilder();
        for (String role : roles) {
            result.append(role).append(":\n");
            for (int i = 0; i < textLines.length; i++) {
                if (textLines[i].startsWith(role + ":")) {
                    result.append(i + 1).append(")").append(textLines[i].substring(role.length() + 1)).append("\n");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

}
