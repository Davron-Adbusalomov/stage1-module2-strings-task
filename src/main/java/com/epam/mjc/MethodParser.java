package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer stringTokenizer = new StringTokenizer(signatureString, " (,)");

        String accessModifier = "";
        String returnType = "";
        String methodName = "";

        if (stringTokenizer.hasMoreTokens()){
            accessModifier = stringTokenizer.nextToken();
            if (accessModifier.startsWith("S")){
                returnType =accessModifier;
                accessModifier=null;

            }
        }

        if (stringTokenizer.hasMoreTokens()){
            returnType = stringTokenizer.nextToken();
        }

        if (stringTokenizer.hasMoreTokens()){
            methodName = stringTokenizer.nextToken();
        }

        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        if (stringTokenizer.hasMoreTokens()){
            MethodSignature.Argument argument = new MethodSignature.Argument(stringTokenizer.nextToken(), stringTokenizer.nextToken());
            argumentList.add(argument);
        }

        if (stringTokenizer.hasMoreTokens()){
            if (stringTokenizer.hasMoreTokens()) {
                MethodSignature.Argument argument = new MethodSignature.Argument(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                argumentList.add(argument);
            }
        }

        if (stringTokenizer.hasMoreTokens()){
            MethodSignature.Argument argument = new MethodSignature.Argument(stringTokenizer.nextToken(), stringTokenizer.nextToken());
            argumentList.add(argument);
        }

        MethodSignature methodSignature = new MethodSignature(methodName, argumentList);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;

        //throw new UnsupportedOperationException("You should implement this method.");
    }
}
